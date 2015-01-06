package pl.karbar.smskompresor.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import pl.karbar.smskompresor.R;

public class StartActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.start_layout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newmessageintent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(newmessageintent);
            }
        });
        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent newmessageintent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(newmessageintent);
            }
        }, 3000);
    }

}
