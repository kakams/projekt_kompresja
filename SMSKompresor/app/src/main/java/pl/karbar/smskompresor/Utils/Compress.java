package pl.karbar.smskompresor.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzuby on 2015-01-14.
 */
public class Compress {

    private HashMap<String,Byte> morse;

    private void inicjalizeMap(){
        //35 znakow alfabetu
        morse.put("a", new Byte("1"));//
        morse.put("b", new Byte("21"));
        morse.put("c", new Byte("11"));
        morse.put("d", new Byte("14"));
        morse.put("e", new Byte("4"));//
        morse.put("f", new Byte("30"));
        morse.put("g", new Byte("22"));
        morse.put("h", new Byte("24"));
        morse.put("i", new Byte("2"));//
        morse.put("j", new Byte("18"));
        morse.put("k", new Byte("13"));
        morse.put("l", new Byte("19"));
        morse.put("m", new Byte("16"));
        morse.put("n", new Byte("6"));//
        morse.put("o", new Byte("3"));
        morse.put("p", new Byte("15"));
        morse.put("q", new Byte("33"));
        morse.put("r", new Byte("7"));//
        morse.put("s", new Byte("9"));
        morse.put("t", new Byte("10"));
        morse.put("u", new Byte("17"));
        morse.put("v", new Byte("34"));
        morse.put("w", new Byte("8"));//
        morse.put("x", new Byte("35"));
        morse.put("y", new Byte("12"));
        morse.put("z", new Byte("5"));
        morse.put("ą", new Byte("25"));
        morse.put("ć", new Byte("29"));
        morse.put("ę", new Byte("23"));
        morse.put("ł", new Byte("20"));
        morse.put("ń", new Byte("31"));
        morse.put("ó", new Byte("26"));
        morse.put("ś", new Byte("28"));
        morse.put("ż", new Byte("27"));
        morse.put("ź", new Byte("32"));
        //cyfry
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
        //znaki specjalne
        morse.put(".", new Byte("36"));
        morse.put(",", new Byte("37"));
        morse.put("'", new Byte("50"));
        morse.put("\"", new Byte("51"));
        morse.put(" ", new Byte("0"));
        morse.put("_", new Byte("52"));
        morse.put(":", new Byte("53"));
        morse.put(";", new Byte("54"));
        morse.put("?", new Byte("38"));
        morse.put("!", new Byte("39"));
        morse.put("-", new Byte("55"));
        morse.put("+", new Byte("56"));
        morse.put("/", new Byte("57"));
        morse.put("(", new Byte("58"));
        morse.put(")", new Byte("59"));
        morse.put("=", new Byte("60"));
        morse.put("@", new Byte("61"));
        morse.put("~", new Byte("62"));
        //dwuznaki
        morse.put("ch", new Byte("63"));
        morse.put("cz", new Byte("64"));
        morse.put("dz", new Byte("65"));
        morse.put("dź", new Byte("66"));
        morse.put("dż", new Byte("67"));
        morse.put("rz", new Byte("68"));
        morse.put("sz", new Byte("69"));
        //pary
        morse.put("ba", new Byte("70"));
        morse.put("ca", new Byte("71"));
        morse.put("da", new Byte("72"));
        morse.put("fa", new Byte("73"));
        morse.put("ga", new Byte("74"));
        morse.put("ha", new Byte("75"));
        morse.put("ja", new Byte("76"));
        morse.put("ka", new Byte("77"));
        morse.put("la", new Byte("78"));
        morse.put("ma", new Byte("79"));
        morse.put("na", new Byte("80"));
        morse.put("pa", new Byte("81"));
        morse.put("ra", new Byte("82"));
        morse.put("sa", new Byte("83"));
        morse.put("ta", new Byte("84"));
        morse.put("wa", new Byte("85"));
        morse.put("za", new Byte("86"));

        morse.put("be", new Byte("87"));
        morse.put("ce", new Byte("88"));
        morse.put("de", new Byte("89"));
        morse.put("fe", new Byte("90"));
        morse.put("ge", new Byte("91"));
        morse.put("he", new Byte("92"));
        morse.put("je", new Byte("93"));
        morse.put("ke", new Byte("94"));
        morse.put("le", new Byte("95"));
        morse.put("me", new Byte("96"));
        morse.put("ne", new Byte("97"));
        morse.put("pe", new Byte("98"));
        morse.put("re", new Byte("99"));
        morse.put("se", new Byte("100"));
        morse.put("te", new Byte("101"));
        morse.put("we", new Byte("102"));
        morse.put("ze", new Byte("103"));

        morse.put("bi", new Byte("104"));
        morse.put("ci", new Byte("105"));
        morse.put("di", new Byte("106"));
        morse.put("fi", new Byte("107"));
        morse.put("gi", new Byte("108"));
        morse.put("hi", new Byte("109"));
        morse.put("ji", new Byte("110"));
        morse.put("ki", new Byte("111"));
        morse.put("li", new Byte("112"));
        morse.put("mi", new Byte("113"));
        morse.put("ni", new Byte("114"));
        morse.put("pi", new Byte("115"));
        morse.put("ri", new Byte("116"));
        morse.put("si", new Byte("117"));
        morse.put("ti", new Byte("118"));
        morse.put("wi", new Byte("119"));
        morse.put("zi", new Byte("120"));

        morse.put("bo", new Byte("121"));
        morse.put("co", new Byte("122"));
        morse.put("do", new Byte("123"));
        morse.put("fo", new Byte("124"));
        morse.put("go", new Byte("125"));
        morse.put("ho", new Byte("126"));
        morse.put("jo", new Byte("-1"));
        morse.put("ko", new Byte("-2"));
        morse.put("lo", new Byte("-3"));
        morse.put("mo", new Byte("-4"));
        morse.put("no", new Byte("-5"));
        morse.put("po", new Byte("-6"));
        morse.put("ro", new Byte("-7"));
        morse.put("so", new Byte("-8"));
        morse.put("to", new Byte("-9"));
        morse.put("wo", new Byte("-10"));
        morse.put("zo", new Byte("-11"));

        morse.put("bu", new Byte("-12"));
        morse.put("cu", new Byte("-13"));
        morse.put("du", new Byte("-14"));
        morse.put("fu", new Byte("-15"));
        morse.put("gu", new Byte("-16"));
        morse.put("hu", new Byte("-17"));
        morse.put("ju", new Byte("-18"));
        morse.put("ku", new Byte("-19"));
        morse.put("lu", new Byte("-20"));
        morse.put("mu", new Byte("-21"));
        morse.put("nu", new Byte("-22"));
        morse.put("pu", new Byte("-23"));
        morse.put("ru", new Byte("-24"));
        morse.put("su", new Byte("-25"));
        morse.put("tu", new Byte("-26"));
        morse.put("wu", new Byte("-27"));
        morse.put("zu", new Byte("-28"));

        morse.put("ab", new Byte("-29"));
        morse.put("ac", new Byte("-30"));
        morse.put("ad", new Byte("-31"));
        morse.put("af", new Byte("-32"));
        morse.put("ag", new Byte("-33"));
        morse.put("ah", new Byte("-34"));
        morse.put("aj", new Byte("-35"));
        morse.put("ak", new Byte("-36"));
        morse.put("al", new Byte("-37"));
        morse.put("am", new Byte("-38"));
        morse.put("an", new Byte("-39"));
        morse.put("ap", new Byte("-40"));
        morse.put("ar", new Byte("-41"));
        morse.put("as", new Byte("-42"));
        morse.put("at", new Byte("-43"));
        morse.put("aw", new Byte("-44"));
        morse.put("az", new Byte("-45"));

        morse.put("eb", new Byte("-46"));
        morse.put("ec", new Byte("-47"));
        morse.put("ed", new Byte("-48"));
        morse.put("ef", new Byte("-49"));
        morse.put("eg", new Byte("-50"));
        morse.put("eh", new Byte("-51"));
        morse.put("ej", new Byte("-52"));
        morse.put("ek", new Byte("-53"));
        morse.put("el", new Byte("-54"));
        morse.put("em", new Byte("-55"));
        morse.put("en", new Byte("-56"));
        morse.put("ep", new Byte("-57"));
        morse.put("er", new Byte("-58"));
        morse.put("es", new Byte("-59"));
        morse.put("et", new Byte("-60"));
        morse.put("ew", new Byte("-61"));
        morse.put("ez", new Byte("-62"));

        morse.put("ib", new Byte("-63"));
        morse.put("ic", new Byte("-64"));
        morse.put("id", new Byte("-65"));
        morse.put("if", new Byte("-66"));
        morse.put("ig", new Byte("-67"));
        morse.put("ih", new Byte("-68"));
        morse.put("ij", new Byte("-69"));
        morse.put("ik", new Byte("-70"));
        morse.put("il", new Byte("-71"));
        morse.put("im", new Byte("-72"));
        morse.put("in", new Byte("-73"));
        morse.put("ip", new Byte("-74"));
        morse.put("ir", new Byte("-75"));
        morse.put("is", new Byte("-76"));
        morse.put("it", new Byte("-77"));
        morse.put("iw", new Byte("-78"));
        morse.put("iz", new Byte("-79"));

        morse.put("ob", new Byte("-80"));
        morse.put("oc", new Byte("-81"));
        morse.put("od", new Byte("-82"));
        morse.put("of", new Byte("-83"));
        morse.put("og", new Byte("-84"));
        morse.put("oh", new Byte("-85"));
        morse.put("oj", new Byte("-86"));
        morse.put("ok", new Byte("-87"));
        morse.put("ol", new Byte("-88"));
        morse.put("om", new Byte("-89"));
        morse.put("on", new Byte("-90"));
        morse.put("op", new Byte("-91"));
        morse.put("or", new Byte("-92"));
        morse.put("os", new Byte("-93"));
        morse.put("ot", new Byte("-94"));
        morse.put("ow", new Byte("-95"));
        morse.put("oz", new Byte("-96"));

        morse.put("ub", new Byte("-97"));
        morse.put("uc", new Byte("-98"));
        morse.put("ud", new Byte("-99"));
        morse.put("uf", new Byte("-100"));
        morse.put("ug", new Byte("-101"));
        morse.put("uh", new Byte("-102"));
        morse.put("uj", new Byte("-103"));
        morse.put("uk", new Byte("-104"));
        morse.put("ul", new Byte("-105"));
        morse.put("um", new Byte("-106"));
        morse.put("un", new Byte("-107"));
        morse.put("up", new Byte("-108"));
        morse.put("ur", new Byte("-109"));
        morse.put("us", new Byte("-110"));
        morse.put("ut", new Byte("-111"));
        morse.put("uw", new Byte("-112"));
        morse.put("uz", new Byte("-113"));

        morse.put("io", new Byte("-114"));
        morse.put("oi", new Byte("-115"));
        morse.put("ia", new Byte("-116"));
        morse.put("ai", new Byte("-117"));
        morse.put("ie", new Byte("-118"));
        morse.put("ei", new Byte("-119"));
        morse.put("iu", new Byte("-120"));
        morse.put("ui", new Byte("-121"));
        morse.put("ua", new Byte("-122"));
        morse.put("au", new Byte("-123"));
        morse.put("eu", new Byte("-124"));
        morse.put("ue", new Byte("-125"));
        morse.put("ae", new Byte("-126"));
        morse.put("ea", new Byte("-127"));
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
        //d;ugosc jednego sms 130 znakow albo 134 - kodowanie 8 bitowe, gdzies czytalem
        //gdy j = 132, trzeba dzielic
        int lenM = message.length();

        message = message.toLowerCase();//zeby nie robic kodów dla dużej i małej litery

        char [] chars = message.toCharArray();

        int lenC = chars.length;
        int lenB = lenC + 1;
        char [] chaars = Arrays.copyOf(chars, lenB);
        chaars[lenB-1] = ' '; //to na wypadek gdyby tekst mial dlugosc nieparzysta
        byte [] bytes = new byte[1];
        //tak zaczyna sie wiadomosc
        bytes[0]=new Byte(Constant.MESSAGE_START);
        String messageInBytes = "" + bytes[0];
        Bytes bytesObj = new Bytes(messageInBytes, lenB, lenM);
        boolean tetet = true;
        for(int i =0, j=1; i<lenB-1; i=i+2){
            System.out.println("i " + i + " j " + j + " chaar[i] " + chaars[i]+ " chaar[i+1] " + chaars[i+1]);
            if(morse.containsKey(""+chaars[i]+chaars[i+1])) {//jesli jest para
                System.out.println("mamy pare " + chaars[i]+chaars[i+1]);
                bytes = Arrays.copyOf(bytes, bytes.length +1);
                bytes[j] = new Byte(morse.get("" + chaars[i]+chaars[i+1]));
                System.out.println("byte " + bytes[j]);
                messageInBytes += "|" + bytes[j];
                j = j + 1;
            }
            else {
                if (morse.containsKey("" + chaars[i])) {
                    bytes = Arrays.copyOf(bytes, bytes.length +1);
                    bytes[j] = new Byte(morse.get("" + chaars[i]));
                    System.out.println("byte " + bytes[j]);
                    messageInBytes += "|" + bytes[j];
                    j = j + 1;
                } else {
                    bytes = Arrays.copyOf(bytes, bytes.length +1);
                    bytes[j] = new Byte(morse.get("~"));
                    System.out.println("byte else " + bytes[j]);
                    messageInBytes += "|" + bytes[j];
                    j = j + 1;
                }

                if (morse.containsKey("" + chaars[i+1])) {
                    bytes = Arrays.copyOf(bytes, bytes.length +1);
                    bytes[j] = new Byte(morse.get("" + chaars[i+1]));
                    System.out.println("byte " + bytes[j]);
                    messageInBytes += "|" + bytes[j];
                    j = j + 1;
                } else {
                    bytes = Arrays.copyOf(bytes, bytes.length +1);
                    bytes[j] = new Byte(morse.get("~"));
                    System.out.println("byte else " + bytes[j]);
                    messageInBytes += "|" + bytes[j];
                    j = j + 1;
                }
            }

            if(j >= 131){
                tetet = false;
                bytesObj.addbytes(bytes);
                //sprawdzenie, czy i+2 nie jest wieksze od lenght
                int start = i + 2;
                if(start>lenB)
                    start = i + 1;
                String mess = new String(chaars, start, lenB-start);
                Bytes byyt = convertStringToMorse(mess);
                ArrayList<byte []> list = byyt.bytesArray;
                for(byte[] b: list){
                    bytesObj.addbytes(b);
                }
                bytesObj.messageInBytes = messageInBytes + byyt.messageInBytes;
                break;
            }

        }
        if(tetet) {
            bytesObj.addbytes(bytes);
            bytesObj.messageInBytes = messageInBytes;
        }
        return bytesObj;
    }

    public Bytes convertMorseToString(byte [] bytes){

        int lenB = bytes.length;

        int lenC = lenB - 1;

        String chars [] = new String[lenC];

        String messageFromBytes = "";

        for(int i =0, j=1; i<lenC; i++, j++){
            chars[i] = getKeyByValue(bytes[j]);
            messageFromBytes += "" + chars[i];
        }

        int lenM = messageFromBytes.length();

        Bytes bytesObj = new Bytes(messageFromBytes, lenB, lenM);
        bytesObj.addbytes(bytes);

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


