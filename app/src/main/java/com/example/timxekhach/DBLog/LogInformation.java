package com.example.timxekhach.DBLog;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timxekhach.DBXe.Xe;
import com.example.timxekhach.Database.SQLLog;
import com.example.timxekhach.Database.SQLXe;
import com.example.timxekhach.R;

import java.util.ArrayList;

public class LogInformation extends AppCompatActivity {

    public static int Xoa = 0;
    public static int getXoa() {
        return Xoa;
    }

    public static void setXoa(int xoa) {
        Xoa = xoa;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_information);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin xe");

        ImageView tt_anhXe = (ImageView) findViewById(R.id.tt_anhXeLog);
        TextView tt_tenXe = (TextView) findViewById(R.id.tt_tenXeLog);
        TextView tt_bienSo = (TextView) findViewById(R.id.tt_bienSoLog);
        TextView tt_loaiXe = (TextView) findViewById(R.id.tt_loaiXeLog);
        TextView tt_mauXe = (TextView) findViewById(R.id.tt_mauXeLog);
        TextView tt_sdt = (TextView) findViewById(R.id.tt_sdtLog);
        TextView tt_tuyen = (TextView) findViewById(R.id.tt_tuyenLog);
        TextView tt_giaVe = (TextView) findViewById(R.id.tt_giaVeLog);
        TextView tt_lichTrinh = (TextView) findViewById(R.id.tt_lichTrinhLog);
        TextView tt_soGhe = (TextView) findViewById(R.id.tt_soGheLog);

        SQLXe sqlXe = new SQLXe(this);
        int LogID = ArrayLog.getLogID();
        final SQLLog sqlLog = new SQLLog(this);
        final ArrayList<Log> arrayListLog = sqlLog.getAllLogs();
        Log log = null;
        for(Log x : arrayListLog){
            if(x.getLogID()==LogID){
                log = x;
                break;
            }
        }
        ArrayList<Xe> listXe = sqlXe.getAllXe();
        Xe xe = new Xe();
        for (Xe x : listXe){
            if(x.getIdXe() == log.getIdXe()){
                xe = x;
            }
        }

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

    }

}
