package pl.karbar.smskompresor.SmsService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

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
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                    }
                    Log.d("kkams", "New message: " +msgs.toString() +" From: "+ msg_from);
                    //TODO po otrzymaniu wiadomości dopisać ją do konwersacji, przenieść osobę która otrzymała sms na początek listy i zmienić jakąś flagę która da znać, że jest nowa wiadomość
                }catch(Exception e){
                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}