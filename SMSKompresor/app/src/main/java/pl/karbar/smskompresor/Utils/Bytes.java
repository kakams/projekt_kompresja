package pl.karbar.smskompresor.Utils;

/**
 * Created by Dzuby on 2015-01-14.
 */
public class Bytes {

    public byte[] bytes;
    public String messageInBytes;

    public int lenghtBytes;
    public int lenghtMessage;

    public Bytes(byte[] bytes, String messageInBytes, int lenghtBytes, int lenghtMessage ){

        this.bytes = bytes;
        this.messageInBytes = messageInBytes;
        this.lenghtBytes = lenghtBytes;
        this.lenghtMessage = lenghtMessage;

    }

}