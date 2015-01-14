package pl.karbar.smskompresor.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzuby on 2015-01-14.
 */
public class Compress {

    private HashMap<String,Byte> morse;

    private void inicjalizeMap(){
        //. to 0, - to 1
        morse.put("a", new Byte("1"));
        morse.put("b", new Byte("8"));
        morse.put("c", new Byte("10"));
        morse.put("d", new Byte("4"));
        morse.put("e", new Byte("0"));
        morse.put("f", new Byte("2"));
        morse.put("g", new Byte("6"));
        morse.put("h", new Byte("-1"));
        morse.put("i", new Byte("-2"));
        morse.put("j", new Byte("7"));
        morse.put("k", new Byte("5"));
        morse.put("l", new Byte("-3"));
        morse.put("m", new Byte("3"));
        morse.put("n", new Byte("-4"));
        morse.put("o", new Byte("-5"));
        morse.put("p", new Byte("-6"));
        morse.put("q", new Byte("13"));
        morse.put("r", new Byte("-7"));
        morse.put("s", new Byte("-8"));
        morse.put("t", new Byte("-9"));
        morse.put("u", new Byte("-10"));
        morse.put("v", new Byte("-11"));
        morse.put("w", new Byte("-12"));
        morse.put("x", new Byte("9"));
        morse.put("y", new Byte("11"));
        morse.put("z", new Byte("12"));
        morse.put("ą", new Byte("-13"));
        morse.put("ć", new Byte("20"));
        morse.put("ę", new Byte("-14"));
        morse.put("ł", new Byte("-15"));
        morse.put("ń", new Byte("27"));
        morse.put("ó", new Byte("14"));
        morse.put("ś", new Byte("-16"));
        morse.put("ż", new Byte("26"));
        morse.put("ź", new Byte("25"));
        //. to 0, - to 1
        morse.put("1", new Byte("41"));
        morse.put("2", new Byte("42"));
        morse.put("3", new Byte("43"));
        morse.put("4", new Byte("44"));
        morse.put("5", new Byte("45"));
        morse.put("6", new Byte("46"));
        morse.put("7", new Byte("47"));
        morse.put("8", new Byte("48"));
        morse.put("9", new Byte("49"));
        morse.put("0", new Byte("40"));
        //. to 0, - to 1
        morse.put(".", new Byte("21"));
        morse.put(",", new Byte("51"));
        morse.put("'", new Byte("30"));
        morse.put("\"", new Byte("18"));
        morse.put(" ", new Byte("15"));//zamiast ch
        morse.put("_", new Byte("-17"));
        morse.put(":", new Byte("56"));
        morse.put(";", new Byte("52"));
        morse.put("?", new Byte("-18"));
        morse.put("!", new Byte("53"));
        morse.put("-", new Byte("33"));
        morse.put("+", new Byte("-19"));
        morse.put("/", new Byte("-20"));
        morse.put("(", new Byte("22"));
        morse.put(")", new Byte("55"));
        morse.put("=", new Byte("17"));
        morse.put("@", new Byte("-21"));
        morse.put("~", new Byte("-22"));
        //. to 0, - to 1
    }

    private String getKeyByValue(Byte value) {
        for (Map.Entry<String,Byte> entry : morse.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    public Bytes convertStringToMorse(String message){

        int lenM = message.length();

        message = message.toLowerCase();//zeby nie robic kodów dla dużej i małej litery

        char [] chars = message.toCharArray();

        int lenC = chars.length;
        int lenB = lenC + 1;

        byte [] bytes = new byte[lenB];
        //tak zaczyna sie wiadomosc
        bytes[0]=new Byte(Constant.MESSAGE_START);
        String messageInBytes = "" + bytes[0];

        for(int i =0, j=1; i<lenC; i++, j++){
            System.out.println("i " + i + " j " + j + " char[i] " + chars[i]);

            if(morse.containsKey(""+chars[i])){
                bytes[j] = new Byte(morse.get(""+chars[i]));
                System.out.println("byte " + bytes[j]);
                messageInBytes += "|" + bytes[j];
            }
            else {
                bytes[j] = new Byte(morse.get("~"));
                System.out.println("byte else " + bytes[j]);
                messageInBytes += "|" + bytes[j];
            }
        }

        Bytes bytesObj = new Bytes(bytes, messageInBytes, lenB, lenM);

        return bytesObj;
    }

    public Bytes convertMorseToString(byte [] bytes){

        int lenB = bytes.length;

        int lenC = lenB - 1;

        char chars [] = new char[lenC];

        String messageFromBytes = "";

        for(int i =0, j=1; i<lenC; i++, j++){
            chars[i] = getKeyByValue(bytes[j]).charAt(0);
            messageFromBytes += "" + chars[i];
        }

        int lenM = messageFromBytes.length();

        Bytes bytesObj = new Bytes(bytes, messageFromBytes, lenB, lenM);

        return bytesObj;
    }

    public Compress(){
        morse = new HashMap<String,Byte>();
        inicjalizeMap();
    }

    /*public static void main(String[] args) {
        // TODO code application logic here

        inicjalizeMap();

        String mess = "Tekstowa&^ wiadomość#";

        Bytes b1 = convertStringToMorse(mess);

        System.out.println(b1.messageInBytes);

        Bytes b2 = convertMorseToString(b1.bytes);

        System.out.println(b2.messageInBytes);
    }*/


}


