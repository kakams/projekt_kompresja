package pl.karbar.smskompresor.SmsService;

import android.util.Log;
import android.telephony.SmsManager;

/**
 * Created by Karol on 2015-01-05.
 */
public class SmsSender {
    SmsManager smsManager = SmsManager.getDefault();
    public boolean sendSMS(String number, String msg){

        Log.d("kkams", "Sending message to: " + number + ". Message:"+ msg);


        //TODO Kompresja wiadomości

        //TODO Wysłanie skompresowanej wiadomości
        //TODO jeśli sukces, to dodanie wiadomości do bazy
        smsManager.sendTextMessage(number, null, msg, null, null);
        return true;//true - udało się wysłać

    }
}
