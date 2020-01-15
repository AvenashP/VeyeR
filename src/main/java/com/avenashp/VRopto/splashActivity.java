package com.avenashp.VRopto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashActivity extends AppCompatActivity {
    public FirebaseAuth firebaseAuth;
    public int TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null){
            new Handler().postDelayed(() -> {
                startActivity(new Intent(splashActivity.this, homeActivity.class));
                finish();
            }, TIME_OUT);
        }else{
            new Handler().postDelayed(() -> {
                startActivity(new Intent(splashActivity.this, userselectionActivity.class));
                finish();
            }, TIME_OUT);
        }

    }
}