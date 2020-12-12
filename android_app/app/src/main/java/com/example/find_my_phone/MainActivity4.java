package com.example.find_my_phone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    private Button onBtn, offBtn,saveBtn;
    EditText phone;
    EditText message;
    String shared1 = "file";
    String shared2 = "file";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //사용자 지정 잠금화면을 실행시키는 버튼
        onBtn= (Button)findViewById(R.id.button);
        //사용자 지정 잠금화면을 종료하는 버튼
        offBtn= (Button)findViewById(R.id.button2);

        saveBtn= (Button)findViewById(R.id.button3);

        phone = (EditText)findViewById(R.id.phone);
        message = (EditText)findViewById(R.id.message);

        SharedPreferences sharedPreferences1 = getSharedPreferences(shared1,0);
        String value1 = sharedPreferences1.getString("phone","");
        phone.setText(value1);
        SharedPreferences sharedPreferences2 = getSharedPreferences(shared2,0);
        String value2 = sharedPreferences2.getString("message", "");
        message.setText(value2);


        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = getSharedPreferences(shared1,0);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                String value1 = phone.getText().toString();
                editor1.putString("phone",value1);
                editor1.commit();

                SharedPreferences sharedPreferences2 = getSharedPreferences(shared2,0);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                String value2 = message.getText().toString();
                editor2.putString("message",value2);
                editor2.commit();
            }
        });

        onBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                    startService(intent);

                    Intent intent1 = new Intent(getApplicationContext(), LockScreenActivity.class);
                    intent1.putExtra("phone", value1); /*송신*/
                    intent1.putExtra("message",value2);
                    startActivity(intent1);

            }

        });
        offBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                stopService(intent);
                Intent intent1 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }


        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences1 = getSharedPreferences(shared1,0);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        String value1 = phone.getText().toString();
        editor1.putString("phone",value1);
        editor1.commit();

        SharedPreferences sharedPreferences2 = getSharedPreferences(shared2,0);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        String value2 = message.getText().toString();
        editor2.putString("message",value2);
        editor2.commit();
    }
}