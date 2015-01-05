package pl.karbar.smskompresor.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.karbar.smskompresor.R;
import pl.karbar.smskompresor.SmsService.SmsSender;

public class NewMessageActivity extends Activity {
    Button sendMsg;
    EditText number, content;
    SmsSender smsSender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        smsSender = new SmsSender();
        sendMsg = (Button)findViewById(R.id.sendMsgButton);
        number =(EditText)findViewById(R.id.numberEditText);
        content =(EditText)findViewById(R.id.messageEditText);
        sendMsg.setOnClickListener(sendMsgOnClick);
    }

    View.OnClickListener sendMsgOnClick = new View.OnClickListener() {
         public void onClick(View v) {
             smsSender = new SmsSender();
             String phoneNumber, msg;
             phoneNumber = number.getText().toString();
             msg = content.getText().toString();
             Log.d("kkams", phoneNumber + " " + msg);
             if(phoneNumber!=null && !phoneNumber.equals("") && msg!=null) {
                 smsSender.sendSMS(phoneNumber, msg);
             }


        }
    };
}
