package pl.karbar.smskompresor.DbPackage;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.Utils.Mock;

/**
 * Created by Karol on 2015-01-05.
 */
public class DbMethods {

    private Context context;
    private Database db;

    public DbMethods(Context c){
        context = c;
        db = new Database (context);
        db.open();
    }

    Mock mock = new Mock();
    public ArrayList<HashMap<String, String>>  getContactList(){
        //TODO pobranie listy kontaktów z bazy
        //Niepotrzebna juz zaslepka, jest gotowa metoda
        return db.getContactList();
    }
    public ArrayList<HashMap<String, String>>  getConversation(String id){
        //TODO pobranie konwersacji dla usera o danym id
        //Niepotrzebna juz zaslepka, jest gotowa metoda
        System.out.println("Id usera to: " + id);
        return db.getConversation(id);
    }
}
