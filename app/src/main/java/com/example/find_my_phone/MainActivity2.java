package com.example.find_my_phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    private TextView tv_result;
    private ImageView iv_profile;
    private Button signOut;
    private GoogleApiClient mGoogleApiClient;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signOut= (Button)findViewById(R.id.signOut);
        setContentView(R.layout.activity_main2);

//        Intent intent = getIntent();
//        String nickname = intent.getStringExtra("nickname");
//        String photourl = intent.getStringExtra("photourl");

//        tv_result= findViewById(R.id.tv_result);
//        tv_result.setText(nickname);
//        iv_profile =findViewById(R.id.iv_profile);
//        Glide.with(this).load(photourl).into(iv_profile);

    }

    public void signOUT(View v){
        FirebaseAuth.getInstance().signOut();
     //   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Toast.makeText(getApplicationContext(),"로그아웃 성공",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
   }
    public void GOGO(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://localhost:8080/homepage/"));
        startActivity(intent);
    }
    public void Lock(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
        startActivity(intent);
    }
    public void myLoc(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity6.class);
        startActivity(intent);
    }
}