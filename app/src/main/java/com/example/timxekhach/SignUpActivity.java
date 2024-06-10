package com.example.timxekhach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.Database.SQLUser;

import java.util.ArrayList;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        final SQLUser sqlUser = new SQLUser(this);

        final EditText fullname = (EditText) findViewById(R.id.fullname);
        final EditText account = (EditText) findViewById(R.id.Account);
        final EditText gmail = (EditText) findViewById(R.id.gmail);
        final EditText password1 = (EditText) findViewById(R.id.pass);
        final EditText password2 = (EditText) findViewById(R.id.pass2);
        Button signup = (Button) findViewById(R.id.SignUp);

        final SQLUser sql_user = new SQLUser(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String acc= account.getText().toString();
                final String ten= fullname.getText().toString();
                final String Gmail = gmail.getText().toString();
                final String mk1= password1.getText().toString();
                final String mk2= password2.getText().toString();
                if(acc.equals("") || ten.equals("") || Gmail.equals("") || mk1.equals("") || mk2.equals("")){
                    Toast.makeText(SignUpActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                    return;
                }
                String words[] = ten.trim().split("\\s+");
                if(words.length<2){
                    Toast.makeText(SignUpActivity.this, "Vui lòng điền đầy đủ họ và tên!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(Gmail.length()<11 || !Gmail.substring(Gmail.length()-10).equals("@gmail.com")){
                    Toast.makeText(SignUpActivity.this, "Email không đúng\nVui lòng nhập lại!", Toast.LENGTH_LONG).show();
                    return;
                }
                String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$"; ;
                if(!mk1.matches(pattern)){
                    Toast.makeText(SignUpActivity.this, "Mật khẩu dài từ 6 - 15 kí tự, có đầy đủ kí tự thường, kí tự viết hoa, chữ số và kí tự đặc biệt!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!mk2.equals(mk1)){
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp\nVui lòng nhập lại!", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<User> users= sqlUser.getArrayUser();
                if(users != null){
                    boolean kiemtra = true;
                    for(User x : users){
                        if(x.getAccount().equals(acc)){
                            kiemtra = false;
                            break;
                        }
                    }
                    if(kiemtra == false){
                        Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        User s = new User(acc, ten, Gmail, mk1, "Hành Khách");
                        sqlUser.AddUser(s);
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                        account.setText("");
                        fullname.setText("");
                        gmail.setText("");
                        password1.setText("");
                        password2.setText("");
                        OpenLogin();
                    }
                }
            }
        });
    }
    public void OpenLogin(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}

