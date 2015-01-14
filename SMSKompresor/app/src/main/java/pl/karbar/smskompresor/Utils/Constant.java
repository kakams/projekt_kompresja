package pl.karbar.smskompresor.Utils;

/**
 * Created by Karol on 2015-01-02.
 */
public class Constant {
    public static final String CONTACT_NAME = "contact_name"; // nazwa kontaktu
    public static final String CONTACT_ID = "contact_id"; // id_kontaktu
    public static final String CONTACT_PHONE = "contact_phone"; // telefon_kontaktu
    public static final String CONTACT_COUNT = "contact_count"; // licznik nieprzeczytanych wiadomości
    public static final String CONTACT_DATE = "contact_date"; // data ostatniej wiadomosci - potrzebne do sortowania
    public static final String CONTACT_DATE_FORMATTED = "contact_date_formatted"; //sformatowana data w postaci yyyy-MM-dd HH:mm:SS

    public static final String MESSAGE_TEXT = "message_text"; // tresc wiadomosci
    public static final String MESSAGE_DATE = "message_date"; // data wiadomości
    public static final String MESSAGE_DATE_FORMATTED = "message_date_formatted"; //sformatowana data w postaci yyyy-MM-dd HH:mm:SS
    public static final byte MESSAGE_START = 127; //od tego zaczyna się każda wiadomość
    public static final short SMS_PORT = 8901; //port na któym wysyłamy sms
}
