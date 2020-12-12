package com.example.find_my_phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class LockScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        Intent intent1 = getIntent();

        TextView tx1 = (TextView)findViewById(R.id.textView1); /*TextView선언*/
        TextView tx2 = (TextView)findViewById(R.id.textView2);

        String phone = intent1.getExtras().getString("phone");
        String message = intent1.getExtras().getString("message");
        tx1.setText(phone);
        tx2.setText(message);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

    }
// 나머지는 알아서 구성하기!
}
