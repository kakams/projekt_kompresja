package pl.karbar.smskompresor.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Karol on 2015-01-03.
 */
public class Mock {
    private ArrayList<HashMap<String, String>> contactsList;
    private ArrayList<ArrayList<HashMap<String, String>>> conversationsList;

    public Mock(){
        conversationsList = new  ArrayList<ArrayList<HashMap<String, String>>>();
        ArrayList<HashMap<String, String>> conversation = new ArrayList<HashMap<String, String>>();
        contactsList = new ArrayList< HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();

        for(int i =0; i<20; i++){
            map = new HashMap<String, String>();
            map.put(Constant.CONTACT_NAME, "Name"+i);
            map.put(Constant.CONTACT_ID, Integer.toString(i));
            map.put(Constant.CONTACT_PHONE, Integer.toString(600700800 + i));
            contactsList.add(map);
        }
        for(int j = 0; j<20 ;j++) {
            conversation = new ArrayList<HashMap<String, String>>();
            for (int k = 0; k < 20; k++) {
                map2 = new HashMap<String, String>();
                if (k % 2 == 0) {
                    map2.put(Constant.CONTACT_ID, Integer.toString(j));
                    map2.put(Constant.CONTACT_NAME, getNameById(Integer.toString(j)));
                    map2.put(Constant.CONTACT_PHONE, getPhoneById(Integer.toString(j)));
                    map2.put(Constant.MESSAGE_TEXT, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum." + k);
                } else {
                    map2.put(Constant.CONTACT_ID, "-1");
                    map2.put(Constant.CONTACT_NAME, "Ja");
                    map2.put(Constant.CONTACT_PHONE, "0700880774");
                    map2.put(Constant.MESSAGE_TEXT, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam." + k);
                }
                conversation.add(map2);
            }
            conversationsList.add(conversation);
        }
    }

    public ArrayList<HashMap<String, String>> getContactList(){
        return contactsList;
    }

    public ArrayList<HashMap<String, String>> getConversation(String id){
        ArrayList<HashMap<String, String>> conversation =new ArrayList<HashMap<String, String>>();
        conversation = conversationsList.get(Integer.parseInt(id));

        return conversation;
    }
    public String getNameById(String id){
        return "Name"+id;
    }

    public String getPhoneById(String id){
        return Integer.toString(600700800+Integer.parseInt(id));
    }
}
