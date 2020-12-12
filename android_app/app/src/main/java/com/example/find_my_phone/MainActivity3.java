package com.example.find_my_phone;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = getIntent();

        String phone = intent1.getExtras().getString("phone");
        String message = intent1.getExtras().getString("message");

        Intent intent3 = new Intent(getApplicationContext(), LockScreenActivity.class);
        intent1.putExtra("phone", phone); /*송신*/
        intent1.putExtra("message",message);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent3);

       // Intent intent2 = new Intent(getApplicationContext(), LockScreenActivity.class);

       // startActivity(intent2);


    }
}