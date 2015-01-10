package pl.karbar.smskompresor.SmsService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.format.Time;
import android.util.Log;

import java.util.Date;

import pl.karbar.smskompresor.DbPackage.Database;
import pl.karbar.smskompresor.Utils.Mock;

/**
 * Created by Karol on 2015-01-03.
 */
public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("kkams","onRecive()");
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from = null;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    String msgBody = "";
                    Mock mock = new Mock();
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();
                    }
                    Log.d("kkams", "New message: " +msgs.toString() +" From: "+ msg_from);
                    //TODO po otrzymaniu wiadomości dopisać ją do konwersacji, przenieść osobę która otrzymała sms na początek listy i zmienić jakąś flagę która da znać, że jest nowa wiadomość

                    //flagi zadnej nie trzeba zmieniac - wystarczy wyswietlic powiadomienie,
                    //po kliknieciu przenosi do activity z konwersacja z dana osoba
                    //natomiast jeśli aktywność listy konwersacji będzie na wierzchu w momencie
                    //przyjścia wiadomości, to trzeba będzie jakoś przeładować listę i zaznaczyć nową wiadomość

                    //sortowanie można robić po dacie, datę zapisuję w bazie, mogę Ci ją jakoś zwrócić,
                    //lub mogę posortować listę przed przekazaniem, ale to w każdym wypadku i tak trzeba zrobić sortowanie.

                    String msg = mock.convertByteToString(msgBody);
                    System.out.println("New message: " +msgs.toString() +" From: "+ msg_from);
                    if(msg_from.startsWith("+48")){
                        msg_from = msg_from.replace("+48", "");
                    }

                    String name = "Kontakt o numerze " + msg_from;

                    Date date = new Date();
                    Long now = date.getTime();
                    String nowString = now.toString();
                    String lenght = "" + msg.length();
                    String lenght_byte = "" + msgBody.length();

                    Database db;
                    db = new Database(context);
                    db.open();
                    db.insertMessage(msg_from,name,msg,msgBody,nowString,"1",lenght,lenght_byte);

                }catch(Exception e){
                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}