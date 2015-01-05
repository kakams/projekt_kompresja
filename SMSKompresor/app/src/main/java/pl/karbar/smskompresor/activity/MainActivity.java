package pl.karbar.smskompresor.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.Adapter.ContactAdapter;
import pl.karbar.smskompresor.DbPackage.DbMethods;
import pl.karbar.smskompresor.R;
import pl.karbar.smskompresor.Utils.Constant;


public class MainActivity extends Activity {

    private ContactAdapter contactAdapter;
    private ArrayList<HashMap<String, String>> contactsList;
    private Button newMessageButton;
    private ListView contactsListView;
    private DbMethods db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newMessageButton = (Button)findViewById(R.id.newMessageButton);
        newMessageButton.setOnClickListener(newMessage);
        db = new DbMethods();
        initializeContacts();



    }
    private void initializeContacts(){
        contactsListView = (ListView) findViewById(R.id.contactNamesList);
        contactsList = db.getContactList();

        contactAdapter = new ContactAdapter(this, contactsList, contactsListView);
        contactsListView.setAdapter(contactAdapter);
        contactsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map =  (HashMap<String, String>)parent.getAdapter().getItem(position);

                Log.d("kkams",map.get(Constant.CONTACT_ID) + " " + map.get(Constant.CONTACT_NAME) + " " +map.get(Constant.CONTACT_PHONE));

                Intent messagesIntent = new Intent(MainActivity.this, MessagesActivity.class);
                messagesIntent.putExtra(Constant.CONTACT_ID, map.get(Constant.CONTACT_ID)  );
                MainActivity.this.startActivity(messagesIntent);

            }
        }
        );
    }
    private ArrayList<HashMap<String, String>> addContact(String name, int contactId, String phoneNumber, ArrayList<HashMap<String, String>> list) {
        if (list == null) {
            list = new ArrayList<HashMap<String, String>>();
        }
        HashMap<String, String> map = new HashMap<String, String>();

        map.put(Constant.CONTACT_NAME, name);
        map.put(Constant.CONTACT_ID, Integer.toString(contactId));
        map.put(Constant.CONTACT_PHONE, phoneNumber);

        list.add(map);
        return list;
    }

    View.OnClickListener newMessage = new View.OnClickListener() {
        public void onClick(View v) {
            Intent newmessageintent = new Intent(MainActivity.this, NewMessageActivity.class);
            MainActivity.this.startActivity(newmessageintent);
        }
    };

}
