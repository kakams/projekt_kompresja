package pl.karbar.smskompresor.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.Adapter.MessagesAdapter;
import pl.karbar.smskompresor.DbPackage.DbMethods;
import pl.karbar.smskompresor.R;
import pl.karbar.smskompresor.Utils.Constant;


public class MessagesActivity extends Activity {
    Button newMessage;
    ListView messagesListView;
    private MessagesAdapter messagesAdapter;
    private ArrayList<HashMap<String, String>> conversation;
    private Button newMessageButton;
    private DbMethods db;
    String authorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_list);
        db = new DbMethods();
        newMessage = (Button)findViewById(R.id.messagesButton);

        if (savedInstanceState == null) {
            Intent i = getIntent();
            if(i.getExtras() == null) {
                authorId= null;
            } else {
                authorId= i.getStringExtra(Constant.CONTACT_ID);
            }
        } else {
            authorId= (String) savedInstanceState.getSerializable("-8");
        }

        initializeConversation();

    }
    private void initializeConversation(){
        messagesListView = (ListView) findViewById(R.id.messagesList);
        conversation = db.getConversation(authorId);
        messagesAdapter = new MessagesAdapter(this, conversation, messagesListView);
        messagesListView.setAdapter(messagesAdapter);

    }

}
