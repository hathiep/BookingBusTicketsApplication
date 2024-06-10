package com.example.timxekhach.DBXe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timxekhach.DBLog.Log;
import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.Database.SQLXe;
import com.example.timxekhach.Database.SQLLog;
import com.example.timxekhach.MainActivity;
import com.example.timxekhach.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XeInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_information);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin xe");

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        final int idxe = (int) bundle.get("idxe");

        ImageView tt_anhXe = (ImageView) findViewById(R.id.tt_anhXe);
        TextView tt_tenXe = (TextView) findViewById(R.id.tt_tenXe);
        TextView tt_bienSo = (TextView) findViewById(R.id.tt_bienSo);
        TextView tt_loaiXe = (TextView) findViewById(R.id.tt_loaiXe);
        TextView tt_mauXe = (TextView) findViewById(R.id.tt_mauXe);
        TextView tt_sdt = (TextView) findViewById(R.id.tt_sdt);
        TextView tt_tuyen = (TextView) findViewById(R.id.tt_tuyen);
        TextView tt_giaVe = (TextView) findViewById(R.id.tt_giaVe);
        TextView tt_lichTrinh = (TextView) findViewById(R.id.tt_lichTrinh);
        TextView tt_soGhe = (TextView) findViewById(R.id.tt_soGhe);
        Button bt_them = (Button) findViewById(R.id.tt_btThem);

        final SQLXe sqlXe = new SQLXe(this);
        final Xe xe = sqlXe.getXe(idxe);

        tt_anhXe.setImageResource(xe.getAnhXe());
        tt_tenXe.setText("Nhà Xe " + xe.getTenXe());
        tt_bienSo.setText("Biển số: " + xe.getBienSo());
        tt_loaiXe.setText("Loại Xe: " + xe.getLoaiXe());
        tt_mauXe.setText("Màu Xe: " + xe.getMauXe());
        tt_sdt.setText("SĐT: " + xe.getSdt());
        tt_tuyen.setText("Tuyến: " + xe.getTuyen());
        tt_giaVe.setText("Giá vé: " + xe.getGiaVe());
        tt_lichTrinh.setText("Lịch trình:\n\n" + xe.getLichTrinh());
        tt_soGhe.setText(" - " + xe.getSoGhe() + "chỗ");

        final SQLLog sqlLog = new SQLLog(this);
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(XeInformation.this);
                b.setTitle("Thêm vào yêu thích");
                b.setMessage("Bạn có muốn thêm xe vào yêu thích?");
                b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        User s = MainActivity.getUser();
                        Log log = new Log(s.getAccount(), xe.getIdXe(), xe.getTenXe());
                        sqlLog.AddLog(log);
                        Toast.makeText(XeInformation.this,  "Đã thêm vào yêu thích" , Toast.LENGTH_SHORT).show();
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
        });
    }


}
