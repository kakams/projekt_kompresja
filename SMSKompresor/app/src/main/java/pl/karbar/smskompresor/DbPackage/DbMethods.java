package pl.karbar.smskompresor.DbPackage;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.Utils.Mock;

/**
 * Created by Karol on 2015-01-05.
 */
public class DbMethods {
    Mock mock = new Mock();
    public ArrayList<HashMap<String, String>>  getContactList(){
        //TODO pobranie listy kontaktów z bazy
        //Chwilowo zastąpione zaslepką
        return mock.getContactList();
    }
    public ArrayList<HashMap<String, String>>  getConversation(String id){
        //TODO pobranie konwersacji dla usera o danym id
        //Chwilowo zastąpione zaslepką
        return mock.getConversation(id);
    }
}
