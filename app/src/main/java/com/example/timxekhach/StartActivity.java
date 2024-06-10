package com.example.timxekhach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class StartActivity extends Activity {

    private static int SPLASH_TIME_OUT= 4500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        TextView welcome = (TextView) findViewById(R.id.title);
        ImageView logo = (ImageView) findViewById(R.id.anhUser);
        ImageView loading = findViewById(R.id.loading);
        Glide.with(this).load(R.drawable.loading_bar3).into(loading);
        logo.setImageResource(R.drawable.logo);
        welcome.setText("\"Đồng hành cùng bạn\ntrên mọi cung đường\"");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OpenMainActivity();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    public void OpenMainActivity(){
        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
