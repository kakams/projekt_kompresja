package pl.karbar.smskompresor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.R;
import pl.karbar.smskompresor.util.Constant;
import pl.karbar.smskompresor.util.MessagesAdapter;
import pl.karbar.smskompresor.util.Mock;


public class MessagesActivity extends ActionBarActivity {
    Button newMessage;
    ListView messagesListView;
    private MessagesAdapter messagesAdapter;
    private ArrayList<HashMap<String, String>> conversation;
    private Button newMessageButton;
    private Mock mock = new Mock();
    String authorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_list);

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
        conversation = mock.getConversation(authorId);
        messagesAdapter = new MessagesAdapter(this, conversation, messagesListView);
        messagesListView.setAdapter(messagesAdapter);

    }
}
