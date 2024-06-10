package com.example.timxekhach;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timxekhach.DBLog.ArrayLog;
import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.DBUser.UserInformation;
import com.example.timxekhach.DBXe.ArrayXe;
import com.example.timxekhach.DBXe.Xe;
import com.example.timxekhach.Database.SQLUser;
import com.example.timxekhach.Database.SQLXe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static User thisUser;
    public static User getUser() {
        return thisUser;
    }
    public void setUser(User user) {
        this.thisUser = user;
    }

    public static String Tuyen;
    public static String getTuyen() {
        return Tuyen;
    }
    public static void setTuyen(String tuyen) {
        Tuyen = tuyen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        AutoCompleteTextView actStart = (AutoCompleteTextView) findViewById(R.id.edt_start);
        AutoCompleteTextView actEnd = (AutoCompleteTextView) findViewById(R.id.edt_end);

        actStart.setThreshold(1);
        actEnd.setThreshold(1);

        ArrayList<Xe> listAllXe = getSQLXe();
        String[] listTT = getResources().getStringArray(R.array.listdiadiem);
        String[] listTime = new String[listAllXe.size()];
        String[] listPass = new String[1000]; int i = 0, j = 0;
        for(Xe xe : listAllXe){
            listTime[j++] = xe.getThoiGian();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTT);
        ArrayAdapter<String> arrayAdapterPass = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listPass);
        ArrayAdapter<String> arrayAdapterTime = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTime);
        actStart.setAdapter(arrayAdapter);
        actEnd.setAdapter(arrayAdapter);



        setTuyen("no");

        ImageButton bt_reverse = (ImageButton) findViewById(R.id.ibt_reverse);
        bt_reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = actStart.getText().toString();
                actStart.setText(actEnd.getText().toString());
                actEnd.setText(s);
            }
        });

        Button bt_ds = (Button) findViewById(R.id.bt_ds);
        bt_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actStart.getText().toString().trim().equals("") || actEnd.getText().toString().trim().equals("")){
                    Toast.makeText( MainActivity.this, "Bạn chưa nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else{
                    setTuyen(actStart.getText().toString() + " - " + actEnd.getText().toString());
                    Intent intent = new Intent(MainActivity.this, ArrayXe.class);
                    startActivity(intent);
                }
            }
        });

    }

    private ArrayList<Xe> getSQLXe() {
        SQLXe sqlXe = new SQLXe(this);
        ArrayList<Xe> listxe = sqlXe.getAllXe();
        return listxe;
    }

    int clickout = 0;

    @Override
    public void onBackPressed() {

        clickout++;
        if(clickout==2){

            AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
            b.setTitle("Thoát ứng dụng");
            b.setMessage("Bạn muốn thoát ứng dụng?");
            b.setIcon(R.drawable.icon_delete);
            b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    onBackPressed();
                    finish();
                }});
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            b.create().show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.log_menu, menu);
        TextView ten = (TextView) findViewById(R.id.Text_Name);
        TextView email = (TextView) findViewById(R.id.Text_Gmail);
        TextView trangthai = (TextView) findViewById(R.id.Text_TrangThai);

        Intent intent = getIntent();
        final String tt_acc = intent.getStringExtra(LoginActivity.EXTRA_USER);
        final SQLUser sqlUser = new SQLUser(this);
        User s = sqlUser.getUser(tt_acc);

        ten.setText(s.getFullname());
        email.setText(s.getGmail());
        trangthai.setText(s.getStatus());
        this.setUser(s);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_allXe) {
            setTuyen("no");
            Intent intent = new Intent(this, ArrayXe.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_Log) {
            Intent intent = new Intent(this, ArrayLog.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, UserInformation.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ChangePassword.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
            b.setTitle("Đăng Xuất");
            b.setMessage("Bạn có muốn đăng xuất?");
            b.setIcon(R.drawable.icons_out);
            b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    OpenLogin();
                    finish();
                }});
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            b.create().show();
        } else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void OpenLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
