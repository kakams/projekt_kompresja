package pl.karbar.smskompresor.SmsService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.text.format.Time;
import android.util.Log;

import java.util.Date;
import java.util.HashMap;

import pl.karbar.smskompresor.Activity.MessagesActivity;
import pl.karbar.smskompresor.DbPackage.Database;
import pl.karbar.smskompresor.R;
import pl.karbar.smskompresor.Utils.Bytes;
import pl.karbar.smskompresor.Utils.Compress;
import pl.karbar.smskompresor.Utils.Constant;
import pl.karbar.smskompresor.Utils.Mock;

/**
 * Created by Karol on 2015-01-03.
 */
public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("kkams","onRecive()");
        if(intent.getAction().equals("android.intent.action.DATA_SMS_RECEIVED")){
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
                    byte[] data = null;
                    String msgBodyBytes = "";

                    Compress compress = new Compress();

                    Bytes b;

                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        //msgBody = msgs[i].getMessageBody();
                        data = msgs[i].getUserData();
                        msgBodyBytes += "" + data;
                        b = compress.convertMorseToString(data);

                        msgBody += b.messageInBytes;

                    }
                    Log.d("kkams", "New message: " +msgs.toString() +" From: "+ msg_from);
                    //TODO po otrzymaniu wiadomości dopisać ją do konwersacji, przenieść osobę która otrzymała sms na początek listy i zmienić jakąś flagę która da znać, że jest nowa wiadomość

                    //flagi zadnej nie trzeba zmieniac - wystarczy wyswietlic powiadomienie,
                    //po kliknieciu przenosi do activity z konwersacja z dana osoba
                    //natomiast jeśli aktywność listy konwersacji będzie na wierzchu w momencie
                    //przyjścia wiadomości, to trzeba będzie jakoś przeładować listę i zaznaczyć nową wiadomość

                    //sortowanie można robić po dacie, datę zapisuję w bazie, mogę Ci ją jakoś zwrócić,
                    //lub mogę posortować listę przed przekazaniem, ale to w każdym wypadku i tak trzeba zrobić sortowanie.

                    //String msg = mock.convertByteToString(msgBody);
                    System.out.println("New message: " +msgs.toString() +" From: "+ msg_from);
                    if(msg_from.startsWith("+48")){
                        msg_from = msg_from.replace("+48", "");
                    }

                    String name = "Kontakt o numerze " + msg_from;

                    Date date = new Date();
                    Long now = date.getTime();
                    String nowString = now.toString();
                    String lenght = "" + msgBody.length();
                    String lenght_byte = "" + msgBodyBytes.length();

                    Database db;
                    db = new Database(context);
                    db.open();
                    db.insertMessage(msg_from,name,msgBody,msgBodyBytes,nowString,"1",lenght,lenght_byte);

                    long [] tab = {311, 323};
                    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                    HashMap<String, String> map = db.getContact(db.findConversationId(msg_from));

                    Intent messagesIntent = new Intent(context, MessagesActivity.class);
                    messagesIntent.putExtra(Constant.CONTACT_ID,map.get(Constant.CONTACT_ID));

                    PendingIntent resultPendingIntent =
                            PendingIntent.getActivity(
                                    context,
                                    0,
                                    messagesIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.drawable.ico2)
                                    .setContentTitle("Nowa wiadomość od: " +msg_from)
                                    //.setOnlyAlertOnce(true)
                                    .setSound(uri)
                                    .setVibrate(tab)
                                    .setContentIntent(resultPendingIntent)
                                    .setAutoCancel(true)
                                    .setContentText(msgBody);
                    // Sets an ID for the notification
                    int mNotificationId = 001;
// Gets an instance of the NotificationManager service
                    NotificationManager mNotifyMgr =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());

                }catch(Exception e){
                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}