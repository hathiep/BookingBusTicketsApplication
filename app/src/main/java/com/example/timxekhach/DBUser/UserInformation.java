package com.example.timxekhach.DBUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.MainActivity;
import com.example.timxekhach.R;

public class UserInformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        final ImageView anhUser = (ImageView) findViewById(R.id.anhUser);
        final TextView logUsername = (TextView) findViewById(R.id.log_username);
        final TextView inforFullname = (TextView) findViewById(R.id.infor_fullname);
        final TextView inforSdt = (TextView) findViewById(R.id.infor_sdt);
        final TextView inforEmail = (TextView) findViewById(R.id.infor_email);
        final TextView inforDiachi = (TextView) findViewById(R.id.infor_diachi);
        final TextView inforGhichu = (TextView) findViewById(R.id.infor_ghichu);

        anhUser.setImageResource(R.drawable.user_icon);
        User s = MainActivity.getUser();
        logUsername.setText(s.getAccount());
        inforFullname.setText(s.getFullname());
        inforEmail.setText(s.getGmail());
    }
}
