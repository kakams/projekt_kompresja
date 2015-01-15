package pl.karbar.smskompresor.Utils;

import java.util.ArrayList;

/**
 * Created by Dzuby on 2015-01-14.
 */
public class Bytes {

    public ArrayList<byte[]> bytesArray = new ArrayList<byte[]>();
    public String messageInBytes;

    public int lenghtBytes;
    public int lenghtMessage;

    public Bytes( String messageInBytes, int lenghtBytes, int lenghtMessage ){
        this.messageInBytes = messageInBytes;
        this.lenghtBytes = lenghtBytes;
        this.lenghtMessage = lenghtMessage;

    }

    public void addbytes(byte[] bytes){
        bytesArray.add(bytes);
    }

}