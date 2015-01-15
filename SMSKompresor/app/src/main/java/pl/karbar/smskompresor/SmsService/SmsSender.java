package pl.karbar.smskompresor.SmsService;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.Date;

import pl.karbar.smskompresor.DbPackage.Database;
import pl.karbar.smskompresor.Utils.Bytes;
import pl.karbar.smskompresor.Utils.Compress;
import pl.karbar.smskompresor.Utils.Constant;
import pl.karbar.smskompresor.Utils.Mock;

/**
 * Created by Karol on 2015-01-05.
 */
public class SmsSender {
    SmsManager smsManager = SmsManager.getDefault();
    private Database db;
    private Mock mock = new Mock();

    public boolean sendSMS(String number, String msg, Context c){

        Log.d("kkams", "Sending message to: " + number + ". Message:"+ msg);

        if(number.startsWith("+48")){
            number = number.replace("+48", "");
        }

        String name = "Kontakt o numerze " + number;
        Date date = new Date();
        Long now = date.getTime();
        String nowString = now.toString();
        String lenght = "" + msg.length();

        String msg_byte = mock.convertStringToByte(msg);//do zmiany po implementacji
        String lenght_byte = "" + msg_byte.length();

        Compress compress = new Compress();

        Bytes b = compress.convertStringToMorse(msg);

        //TODO Kompresja wiadomości

        //TODO Wysłanie skompresowanej wiadomości
        //TODO jeśli sukces, to dodanie wiadomości do bazy
        boolean success = true;
        //smsManager.sendTextMessage(number, null, msg, null, null);
        ArrayList<byte[]> list = b.bytesArray;

        for(byte[] bytes :  list){
            smsManager.sendDataMessage(number, null, Constant.SMS_PORT, bytes, null, null );
        }



        //smsManager.sendDataMessage();
        if(success){
                db = new Database (c);
                db.open();
                db.insertMessage(number,name,msg,b.messageInBytes,nowString,"0",lenght,""+b.lenghtBytes);
        }
        return true;//true - udało się wysłać

    }
}
