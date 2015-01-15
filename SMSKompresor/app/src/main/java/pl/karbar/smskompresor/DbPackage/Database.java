package pl.karbar.smskompresor.DbPackage;

/**
 * Created by Dzuby on 2015-01-08.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.format.Time;
import android.util.Log;

import pl.karbar.smskompresor.Utils.Constant;
import pl.karbar.smskompresor.Utils.Mock;

public class Database {

    private static final String DEBUG_TAG = "SMSKompresorDatabase";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "smskompresordatabase.db";
    private static final String DB_MESSAGES_TABLE = "messages";
    private static final String DB_IDD_TABLE = "idd";
    private static final String DB_CONVERSATION_TABLE = "conversation";

    public static final String MESSAGES_KEY_ID = "_id_message";
    public static final String MESSAGES_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int MESSAGES_ID_COLUMN = 0;

    public static final String MESSAGES_KEY_CONVERSATION_ID = "id_conversation";
    public static final String MESSAGES_CONVERSATION_ID_OPTIONS = "INTEGER NOT NULL";
    public static final int MESSAGES_CONVERSATION_ID_COLUMN = 1;

    public static final String MESSAGES_KEY_PHONE_NUMBER = "phone_number";
    public static final String MESSAGES_PHONE_NUMBER_OPTIONS = "TEXT";
    public static final int MESSAGES_PHONE_NUMBER_COLUMN = 2;

    public static final String MESSAGES_KEY_CONTACT_NAME = "contact_name";
    public static final String MESSAGES_CONTACT_NAME_OPTIONS = "TEXT";
    public static final int MESSAGES_CONTACT_NAME_COLUMN = 3;

    public static final String MESSAGES_KEY_CONTENT = "content";
    public static final String MESSAGES_CONTENT_OPTIONS = "TEXT";
    public static final int MESSAGES_CONTENT_COLUMN = 4;

    public static final String MESSAGES_KEY_CONTENT_BYTE = "content_byte";
    public static final String MESSAGES_CONTENT_BYTE_OPTIONS = "TEXT";
    public static final int MESSAGES_CONTENT_BYTE_COLUMN = 5;

    public static final String MESSAGES_KEY_TIME = "time";
    public static final String MESSAGES_TIME_OPTIONS = "TEXT";
    public static final int MESSAGES_TIME_COLUMN = 6;

    public static final String MESSAGES_KEY_SEND_OR_RECEIVE = "send_or_receive";
    public static final String MESSAGES_SEND_OR_RECEIVE_OPTIONS = "TEXT NOT NULL"; //0 - send, 1 - receive
    public static final int MESSAGES_SEND_OR_RECEIVE_COLUMN = 7;

    public static final String MESSAGES_KEY_IS_READ = "is_read";
    public static final String MESSAGES_IS_READ_OPTIONS = "TEXT NOT NULL"; //0 - read, 1 - not read
    public static final int MESSAGES_IS_READ_COLUMN = 8;

    public static final String MESSAGES_KEY_CONTENT_SIZE = "content_size";
    public static final String MESSAGES_CONTENT_SIZE_OPTIONS = "TEXT";
    public static final int MESSAGES_CONTENT_SIZE_COLUMN = 9;

    public static final String MESSAGES_KEY_CONTENT_BYTE_SIZE = "content_byte_size";
    public static final String MESSAGES_CONTENT_BYTE_SIZE_OPTIONS = "TEXT";
    public static final int MESSAGES_CONTENT_BYTE_SIZE_COLUMN = 10;

    private static final String DB_CREATE_MESSAGES_TABLE =
            "CREATE TABLE" + " " + DB_MESSAGES_TABLE + "( " +
                    MESSAGES_KEY_ID + " " + MESSAGES_ID_OPTIONS + ", " +
                    MESSAGES_KEY_CONVERSATION_ID + " " + MESSAGES_CONVERSATION_ID_OPTIONS + ", " +
                    MESSAGES_KEY_PHONE_NUMBER + " " + MESSAGES_PHONE_NUMBER_OPTIONS + ", " +
                    MESSAGES_KEY_CONTACT_NAME + " " + MESSAGES_CONTACT_NAME_OPTIONS + ", " +
                    MESSAGES_KEY_CONTENT + " " + MESSAGES_CONTENT_OPTIONS + ", " +
                    MESSAGES_KEY_CONTENT_BYTE + " " + MESSAGES_CONTENT_BYTE_OPTIONS + ", " +
                    MESSAGES_KEY_TIME + " " + MESSAGES_TIME_OPTIONS + ", " +
                    MESSAGES_KEY_SEND_OR_RECEIVE + " " + MESSAGES_SEND_OR_RECEIVE_OPTIONS + ", " +
                    MESSAGES_KEY_IS_READ + " " + MESSAGES_IS_READ_OPTIONS + ", " +
                    MESSAGES_KEY_CONTENT_SIZE + " " + MESSAGES_CONTENT_SIZE_OPTIONS + ", " +
                    MESSAGES_KEY_CONTENT_BYTE_SIZE + " " + MESSAGES_CONTENT_BYTE_SIZE_OPTIONS + " );";

    private static final String DROP_MESSAGES_TABLE =
            "DROP TABLE IF EXISTS" + " " + DB_MESSAGES_TABLE;

    public static final String[] column_keys_messages = {MESSAGES_KEY_ID, MESSAGES_KEY_CONVERSATION_ID,
                    MESSAGES_KEY_PHONE_NUMBER, MESSAGES_KEY_CONTACT_NAME, MESSAGES_KEY_CONTENT,
                    MESSAGES_KEY_CONTENT_BYTE, MESSAGES_KEY_TIME, MESSAGES_KEY_SEND_OR_RECEIVE, MESSAGES_KEY_IS_READ,
                    MESSAGES_KEY_CONTENT_SIZE, MESSAGES_KEY_CONTENT_BYTE_SIZE};

    public static final String IDD_KEY_ID = "_id_";
    public static final String IDD_ID_OPTIONS = "INTEGER";
    public static final int IDD_ID_COLUMN = 0;

    private static final String DB_CREATE_IDD_TABLE =
            "CREATE TABLE" + " " + DB_IDD_TABLE + "( " +
                    IDD_KEY_ID + " " + IDD_ID_OPTIONS + " );";

    private static final String DROP_IDD_TABLE =
            "DROP TABLE IF EXISTS" + " " + DB_IDD_TABLE;

    public static final String[] column_keys_idd = {IDD_KEY_ID};

    public static final String CONVERSATION_KEY_ID = "_id";
    public static final String CONVERSATION_ID_OPTIONS = "INTEGER";
    public static final int CONVERSATION_ID_COLUMN = 0;

    public static final String CONVERSATION_KEY_LAST_DATE = "last_date";
    public static final String CONVERSATION_LAST_DATE_OPTIONS = "TEXT";
    public static final int CONVERSATION_LAST_DATE_COLUMN = 1;

    public static final String CONVERSATION_KEY_PHONE_NUMBER = "phone_number";
    public static final String CONVERSATION_PHONE_NUMBER_OPTIONS = "TEXT";
    public static final int CONVERSATION_PHONE_NUMBER_COLUMN = 2;

    public static final String CONVERSATION_KEY_CONTACT_NAME = "contact_name";
    public static final String CONVERSATION_CONTACT_NAME_OPTIONS = "TEXT";
    public static final int CONVERSATION_CONTACT_NAME_COLUMN = 3;

    public static final String CONVERSATION_KEY_COUNT_NOT_READ = "count_not_read";
    public static final String CONVERSATION_COUNT_NOT_READ_OPTIONS = "INTEGER";
    public static final int CONVERSATION_COUNT_NOT_READ_COLUMN = 4;

    private static final String DB_CREATE_CONVERSATION_TABLE =
            "CREATE TABLE" + " " + DB_CONVERSATION_TABLE + "( " +
                    CONVERSATION_KEY_ID + " " + CONVERSATION_ID_OPTIONS + ", " +
                    CONVERSATION_KEY_LAST_DATE + " " + CONVERSATION_LAST_DATE_OPTIONS + ", " +
                    CONVERSATION_KEY_PHONE_NUMBER + " " + CONVERSATION_PHONE_NUMBER_OPTIONS + ", " +
                    CONVERSATION_KEY_CONTACT_NAME + " " + CONVERSATION_CONTACT_NAME_OPTIONS + ", " +
                    CONVERSATION_KEY_COUNT_NOT_READ + " " + CONVERSATION_COUNT_NOT_READ_OPTIONS + " );";

    private static final String DROP_CONVERSATION_TABLE =
            "DROP TABLE IF EXISTS" + " " + DB_CONVERSATION_TABLE;

    public static final String[] column_keys_conversation = {CONVERSATION_KEY_ID, CONVERSATION_KEY_LAST_DATE,
            CONVERSATION_KEY_PHONE_NUMBER, CONVERSATION_KEY_CONTACT_NAME, CONVERSATION_KEY_COUNT_NOT_READ};

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    Mock mock = new Mock();

    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            System.out.println("jestem w on create database");
            db.execSQL(DB_CREATE_MESSAGES_TABLE);
            db.execSQL(DB_CREATE_IDD_TABLE);
            db.execSQL(DB_CREATE_CONVERSATION_TABLE);
            String sql = "INSERT or replace INTO " + DB_IDD_TABLE + "(" + IDD_KEY_ID + ") VALUES(1)" ;
            db.execSQL(sql);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Tables " + DB_CREATE_MESSAGES_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL(DROP_MESSAGES_TABLE);
            db.execSQL(DROP_IDD_TABLE);
            db.execSQL(DROP_CONVERSATION_TABLE);
            onCreate(db);
        }
    }

    public Database(Context c){
        context = c;
    }

    public void clearDatabase(){

        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);
    }

    public Database open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try{
            Log.d(DEBUG_TAG, "Trying to get writable database "+ DB_NAME);
            System.out.println("próbuje sie dostać do bazy");
            db = dbHelper.getWritableDatabase();
            System.out.println("udało sie dostać do bazy");
            Log.d(DEBUG_TAG, "Getting writable database "+ DB_NAME + " ended with success");
        } catch(SQLException e){
            Log.d(DEBUG_TAG, "Getting writable database "+ DB_NAME + " ended with failure.");
            Log.d(DEBUG_TAG, "Exceprion: " + e.toString());
            Log.d(DEBUG_TAG, "Trying to get readable database");
            System.out.println("NIE udało sie dostać do bazywritable szykam readable");
            db = dbHelper.getReadableDatabase();
            System.out.println("udało sie dostać do bazy ale tylko readable");
            Log.d(DEBUG_TAG, "Getting readable database "+ DB_NAME + " ended with success");

        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        return this;
    }

    public void close(){
        dbHelper.close();
    }

    /*public long insertTask(String name, String description){

        ContentValues newValues = new ContentValues();

        newValues.put(MESSAGES_KEY_DESCRIPTION, "" + name);
        newValues.put(MESSAGES_KEY_NAME_MESSAGES, "" + description);

        System.out.println("jestem, zyje");
        long id  = db.insert(DB_MESSAGES_TABLE, null, newValues);
        if(id == -1){
            System.out.println("Blad przy tworzeniu zadania");
            return -1;
        }
        //task.setId(id);
        return id;

    }*/

    public long insertMessage(String phone_number, String contact_name, String content, String content_byte,
                              String time, String send_or_receive, String content_size, String content_byte_size){

        ContentValues newValues = new ContentValues();
        System.out.println("cos tamm  przed " + phone_number);
        if(phone_number.startsWith("+48")){
            phone_number = phone_number.replace("+48", "");
        }
        System.out.println("cos tamm  po " + phone_number);
        newValues.put(MESSAGES_KEY_PHONE_NUMBER, "" + phone_number);
        //to numer nadawcy - jesli dostalismy sms lub odbiorcy - jesli to my wyslalismy sms
        newValues.put(MESSAGES_KEY_CONTACT_NAME, "" + contact_name);
        newValues.put(MESSAGES_KEY_CONTENT, "" + content);
        newValues.put(MESSAGES_KEY_SEND_OR_RECEIVE, "" + send_or_receive);
        newValues.put(MESSAGES_KEY_CONTENT_BYTE, "" + content_byte);
        newValues.put(MESSAGES_KEY_TIME, "" + time);
        newValues.put(MESSAGES_KEY_CONVERSATION_ID, "" + getConversationId(phone_number, contact_name, time));
        newValues.put(MESSAGES_KEY_CONTENT_SIZE, "" + content_size);
        newValues.put(MESSAGES_KEY_CONTENT_BYTE_SIZE, "" + content_byte_size);

        if(send_or_receive.equals("0")) {//0 - send, 1 - receive
            newValues.put(MESSAGES_KEY_IS_READ, "0");//0 - read, 1 - not read
            setLastDate(findConversationId(phone_number), time);
        }
        else{
            newValues.put(MESSAGES_KEY_IS_READ, "1");//0 - read, 1 - not read
            incrementCountNotReadAndSetLastDate(findConversationId(phone_number), time);
        }

        long id  = db.insert(DB_MESSAGES_TABLE, null, newValues);
        if(id == -1){
            System.out.println("Blad przy zapisywaniu wiadomosci!");
            return -1;
        }
        return id;
    }

    private void setLastDate(int id, String date){
        String where = CONVERSATION_KEY_ID + " = " + id;
        ContentValues updateValues = new ContentValues();
        updateValues.put(CONVERSATION_KEY_LAST_DATE, date);

        if (db.update(DB_CONVERSATION_TABLE, updateValues, where, null) > 0){
            System.out.println("Zmieniono date w konwersacji " + id);
        }
        else{
            System.out.println("Blad przy zmianie daty w konwersacji " + id);
        }
    }

    private void setReadMessages(int id){
        String where = MESSAGES_KEY_CONVERSATION_ID + " = " + id + " AND " + MESSAGES_KEY_IS_READ + " like '1'";
        Cursor cursor = db.query(DB_MESSAGES_TABLE, column_keys_messages, where, null, null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                do {
                    
                    String idd = cursor.getString(MESSAGES_ID_COLUMN);
                    
                    ContentValues updateValues = new ContentValues();
                    updateValues.put(MESSAGES_KEY_IS_READ, "0");
                    String where2 = MESSAGES_KEY_ID + " = " + idd;

                    if (db.update(DB_MESSAGES_TABLE, updateValues, where2, null) > 0){
                        System.out.println("Ustawiono jako przeczytana wiadomosc " + idd +  " dla konwersacji " + id);
                    }
                    else{
                        System.out.println("Blad przy ustawianiu jako przeczytana wiadomosc " + idd +  " dla konwersacji " + id);
                    }
                } while (cursor.moveToNext());
            }
        }


    }

    private void incrementCountNotReadAndSetLastDate(int id, String time){
        String where = CONVERSATION_KEY_ID + " = " + id;
        Cursor cursor = db.query( DB_CONVERSATION_TABLE, column_keys_conversation, where, null, null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                String counte = cursor.getString(CONVERSATION_COUNT_NOT_READ_COLUMN);
                System.out.println("licnznik 1 " + counte);
                int count = Integer.valueOf(counte);
                System.out.println("licnznik 2 " + count);
                count++; //zwiększenie licznika
                System.out.println("licnznik 3 " + count);

                ContentValues updateValues = new ContentValues();
                updateValues.put(CONVERSATION_KEY_COUNT_NOT_READ, count);
                updateValues.put(CONVERSATION_KEY_LAST_DATE, time);

                if (db.update(DB_CONVERSATION_TABLE, updateValues, where, null) > 0){
                    System.out.println("Zmieniono licznik wiadomosci i date w konwersacji " + id);
                }
                else{
                    System.out.println("Blad przy zmianie licznika wiadomosci i daty w konwersacji " + id);
                }
            }
        }
    }

    private void setCountNotReadToZero(int id){
        String where = CONVERSATION_KEY_ID + " = " + id;
        ContentValues updateValues = new ContentValues();
        updateValues.put(CONVERSATION_KEY_COUNT_NOT_READ, 0);

        if (db.update(DB_CONVERSATION_TABLE, updateValues, where, null) > 0){
            System.out.println("Zmieniono licznik wiadomosci na 0 w konwersacji " + id);
        }
        else{
            System.out.println("Blad przy zmianie licznika wiadomosci na 0 w konwersacji " + id);
        }
    }

    private int getConversationId(String phone_number, String contact_name, String time){
        int retId = -1;
        if(phone_number.startsWith("+48")){
            phone_number = phone_number.replace("+48", "");
        }
        String phone = "'%" + phone_number + "%'";
        System.out.println("cos tamm" + phone);
        String where = CONVERSATION_KEY_PHONE_NUMBER + " LIKE " + phone; //tu moga byc problemy z tym, ze raz numer moze byc 0667686 a raz +48565656
        Cursor cursor = db.query( DB_CONVERSATION_TABLE, column_keys_conversation, where, null, null, null, null);
        if(cursor != null && cursor.moveToFirst() && (cursor.getCount() > 0)) {
            retId = cursor.getInt(CONVERSATION_ID_COLUMN);

        }
        else{//nie było wczesniej konwersacji z tym numerem
            retId = getConversationIdFromDatabase();
            insertConversation(retId, phone_number, contact_name, time);
        }

        return retId;
    }

    public void insertConversation(int id, String phone_number, String contact_name, String time){
        ContentValues newValues = new ContentValues();
        System.out.println("cos tamm  przed " + phone_number);
        if(phone_number.startsWith("+48")){
            phone_number = phone_number.replace("+48", "");
        }
        System.out.println("cos tamm  po " + phone_number);
        newValues.put(CONVERSATION_KEY_PHONE_NUMBER, "" + phone_number);
        //to numer nadawcy - jesli dostalismy sms lub odbiorcy - jesli to my wyslalismy sms
        newValues.put(CONVERSATION_KEY_CONTACT_NAME, "" + contact_name);
        newValues.put(CONVERSATION_KEY_LAST_DATE, "" + time);
        newValues.put(CONVERSATION_KEY_ID, "" + id);
        newValues.put(CONVERSATION_KEY_COUNT_NOT_READ, "0");

        long idd  = db.insert(DB_CONVERSATION_TABLE, null, newValues);
        if(id == -1){
            System.out.println("Blad przy zapisywaniu konwersacji!");
        }
    }

    public int findConversationId(String phone_number){
        int retId = -1;
        if(phone_number.startsWith("+48")){
            phone_number = phone_number.replace("+48", "");
        }
        phone_number = "'%" + phone_number + "%'";
        System.out.println("cos tamm" + phone_number);
        String where = CONVERSATION_KEY_PHONE_NUMBER + " LIKE " + phone_number; //tu moga byc problemy z tym, ze raz numer moze byc 0667686 a raz +48565656
        Cursor cursor = db.query( DB_CONVERSATION_TABLE, column_keys_conversation, where, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            retId = cursor.getInt(CONVERSATION_ID_COLUMN);
        }
        return retId;
    }

    public int getConversationIdFromDatabase(){
        Cursor cursor = db.query( DB_IDD_TABLE, column_keys_idd, null, null, null, null, null);
        int ret = -1;

        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                String id = cursor.getString(IDD_ID_COLUMN);
                int idd = Integer.valueOf(id);
                ret = idd;
                idd++; //zwiększenie licznika

                ContentValues updateValues = new ContentValues();
                updateValues.put(IDD_KEY_ID, idd);
                if (db.update(DB_IDD_TABLE, updateValues, null, null) > 0){
                    return ret;
                }
                else{
                    return -2;
                }
            }
        }

        return ret;
    }

    public ArrayList<HashMap<String, String>> getContactList(){
        ArrayList<HashMap<String, String>> conversations = new ArrayList<HashMap<String,String>>();
        Cursor cursor = db.query( DB_CONVERSATION_TABLE, column_keys_conversation, null, null, null, null, null);
        HashMap<String, String> map;
        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0)
                do{
                    String name = cursor.getString(CONVERSATION_CONTACT_NAME_COLUMN);
                    String id = cursor.getString(CONVERSATION_ID_COLUMN);//do sprawdzenia czy to to
                    String phone = cursor.getString(CONVERSATION_PHONE_NUMBER_COLUMN);
                    String last_date = cursor.getString(CONVERSATION_LAST_DATE_COLUMN);
                    String count = cursor.getString(CONVERSATION_COUNT_NOT_READ_COLUMN);

                    Date d = new Date(Long.valueOf(last_date));
                    String last_date_formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(d);

                    map = new HashMap<String, String>();
                    map.put(Constant.CONTACT_NAME, name);
                    map.put(Constant.CONTACT_ID, id);
                    map.put(Constant.CONTACT_PHONE, phone);
                    map.put(Constant.CONTACT_DATE, last_date);
                    map.put(Constant.CONTACT_DATE_FORMATTED, last_date_formatted);
                    map.put(Constant.CONTACT_COUNT, count);

                    if(conversations.contains(map)){
                        System.out.println("Duplikat");
                    }
                    else {
                        conversations.add(map);
                    }

                } while (cursor.moveToNext());
        }

        class MapComparator implements Comparator<Map<String, String>>
        {
            private final String key;
            private final int type;

            public MapComparator(String key, int type)
            {
                this.key = key;
                this.type = type;
            }

            public int compare(Map<String, String> first,
                               Map<String, String> second)
            {
                // TODO: Null checking, both for maps and values
                if (first == null || second == null)
                    return 0;
                String firstDate = first.get(key);
                String secondDate = second.get(key);

                if(firstDate == null || secondDate == null)
                    return 0;

                Date datefirst = new Date(Long.valueOf(firstDate));
                Date datesecond = new Date(Long.valueOf(secondDate));

                if(type == 1){//chronologicznie
                    return datefirst.compareTo(datesecond);
                }
                else if(type == 2){//achronologicznie
                    return datesecond.compareTo(datefirst);
                }
                else{
                    return datefirst.compareTo(datesecond);
                }
            }
        }

        Collections.sort(conversations, new MapComparator(Constant.CONTACT_DATE, 2));

        return conversations;
    }

    public ArrayList<HashMap<String, String>> getConversation(String id){
        System.out.println(id);
        System.out.println(Integer.valueOf(id));

        setCountNotReadToZero(Integer.valueOf(id));
        setReadMessages(Integer.valueOf(id));

        String where = MESSAGES_KEY_CONVERSATION_ID + " = " + id;
        ArrayList<HashMap<String, String>> conversation = new ArrayList<HashMap<String,String>>();
        Cursor cursor = db.query( DB_MESSAGES_TABLE, column_keys_messages, where, null, null, null, null);
        HashMap<String, String> map;
        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0)
                do{
                    String name = cursor.getString(MESSAGES_CONTACT_NAME_COLUMN);
                    //String id = cursor.getString(MESSAGES_CONVERSATION_ID_COLUMN);//do sprawdzenia czy to to
                    String phone = cursor.getString(MESSAGES_PHONE_NUMBER_COLUMN);
                    String text = cursor.getString(MESSAGES_CONTENT_COLUMN);
                    String send_or_receive = cursor.getString(MESSAGES_SEND_OR_RECEIVE_COLUMN);
                    String date = cursor.getString(MESSAGES_TIME_COLUMN);
                    String len = cursor.getString(MESSAGES_CONTENT_SIZE_COLUMN);
                    String len_byte = cursor.getString(MESSAGES_CONTENT_BYTE_SIZE_COLUMN);

                    Date d = new Date(Long.valueOf(date));
                    String date_formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(d);

                    map = new HashMap<String, String>();
                    map.put(Constant.MESSAGE_TEXT, text);
                    map.put(Constant.MESSAGE_DATE, date);
                    map.put(Constant.MESSAGE_LENGTH, len);
                    map.put(Constant.MESSAGE_LENGTH_BYTES, len_byte);
                    map.put(Constant.MESSAGE_DATE_FORMATTED, date_formatted);
                    if(send_or_receive.equals("0")) {//0 - send, 1 - receive
                        map.put(Constant.CONTACT_NAME, "Ja");
                        map.put(Constant.CONTACT_ID, "-1");
                        map.put(Constant.CONTACT_PHONE, "0123456789");//to można pobrać jakoś
                    }
                    else{
                        map.put(Constant.CONTACT_NAME, name);
                        map.put(Constant.CONTACT_ID, id);
                        map.put(Constant.CONTACT_PHONE, phone);
                    }
                    conversation.add(map);

                } while (cursor.moveToNext());
        }
        return conversation;
    }

    public HashMap<String, String> getContact(int conv_id){

        String where = CONVERSATION_KEY_ID + " = " + conv_id;
        Cursor cursor = db.query( DB_CONVERSATION_TABLE, column_keys_conversation, where, null, null, null, null);
        HashMap<String, String> map = null;
        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            if(cursor.getCount() > 0) {
                String name = cursor.getString(CONVERSATION_CONTACT_NAME_COLUMN);
                String id = cursor.getString(CONVERSATION_ID_COLUMN);//do sprawdzenia czy to to
                String phone = cursor.getString(CONVERSATION_PHONE_NUMBER_COLUMN);
                String last_date = cursor.getString(CONVERSATION_LAST_DATE_COLUMN);
                String count = cursor.getString(CONVERSATION_COUNT_NOT_READ_COLUMN);

                Date d = new Date(Long.valueOf(last_date));
                String last_date_formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(d);

                map = new HashMap<String, String>();
                map.put(Constant.CONTACT_NAME, name);
                map.put(Constant.CONTACT_ID, id);
                map.put(Constant.CONTACT_PHONE, phone);
                map.put(Constant.CONTACT_DATE, last_date);
                map.put(Constant.CONTACT_DATE_FORMATTED, last_date_formatted);
                map.put(Constant.CONTACT_COUNT, count);
            }
        }



        return map;
    }
}
