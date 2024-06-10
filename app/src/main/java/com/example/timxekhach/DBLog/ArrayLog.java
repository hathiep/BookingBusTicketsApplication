package com.example.timxekhach.DBLog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timxekhach.DBXe.Xe;
import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.Database.SQLXe;
import com.example.timxekhach.Database.SQLLog;
import com.example.timxekhach.MainActivity;
import com.example.timxekhach.R;

import java.util.ArrayList;

public class ArrayLog extends AppCompatActivity {
    private ArrayLogAdapter adapter;
    public static Xe xe_log;

    public static int LogID;
    public static int getLogID() {
        return LogID;
    }
    public static void setLogID(int logID) {
        LogID = logID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_log);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chuyến xe yêu thích");

        ListView lvlog = (ListView) findViewById(R.id.arraylog);

        final SQLLog sqlLog = new SQLLog(this);
        final SQLXe sqlXe = new SQLXe(this);
        User s = MainActivity.getUser();
        final ArrayList<Log> listLog = sqlLog.getAllLogs();
        if(listLog != null){
            ArrayList<Xe> booklog = new ArrayList<>();
            for(Log x: listLog){
                if(x.getAccount().equals(s.getAccount())){
                    Xe getbook= sqlXe.getXe(x.getIdXe());
                    booklog.add(getbook);
                }
            }
            if(booklog != null){
                adapter = new ArrayLogAdapter(this, R.layout.item_xelog, booklog);
                lvlog.setAdapter(adapter);
            }else{
                Toast.makeText(ArrayLog.this, "Bạn chưa có chuyến xe yêu thích!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ArrayLog.this, "Bạn chưa có chuyến xe yêu thích!", Toast.LENGTH_SHORT).show();
        }
        lvlog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder b=new AlertDialog.Builder(ArrayLog.this);
                b.setTitle("XOÁ KHỎI YÊU THÍCH");
                b.setMessage("Bạn có muốn Xóa xe \"" + listLog.get(position).getTenXe() + "\" khỏi chuyến xe yêu thích không?");
                b.setIcon(R.drawable.icon_delete);
                b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sqlLog.DeleteLog(listLog.get(position));
                        Toast.makeText(ArrayLog.this, "Đã xoá khỏi Xe yêu thích", Toast.LENGTH_SHORT).show();
                        ResetSach();
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
                return true;
            }
        });
        lvlog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayLog.setLogID(listLog.get(position).getLogID());
                OpenThongTinSach();
            }
        });

    }

    private void OpenThongTinSach() {
        Intent intent = new Intent(ArrayLog.this, LogInformation.class);
        startActivity(intent);

    }

    private void ResetSach(){
        Intent intent = new Intent(ArrayLog.this, ArrayLog.class);
        startActivity(intent);

    }


}
