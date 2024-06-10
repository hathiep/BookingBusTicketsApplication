package com.example.timxekhach.DBLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timxekhach.DBXe.Xe;
import com.example.timxekhach.R;

import java.util.List;

public class ArrayLogAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Xe> list;

    public ArrayLogAdapter(Context context, int layout, List<Xe> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewAnhXa{
        TextView tenXe, loaiXe, soGhe, tuyen, thoiGian;
        ImageView anhXe;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewAnhXa viewAnhXa;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewAnhXa = new ViewAnhXa();
            //Ánh Xạ View
            viewAnhXa.tenXe= (TextView) convertView.findViewById(R.id.tenXe);
            viewAnhXa.loaiXe = (TextView) convertView.findViewById(R.id.loaiXe);
            viewAnhXa.tuyen = (TextView) convertView.findViewById(R.id.tuyen);
            viewAnhXa.thoiGian = (TextView) convertView.findViewById(R.id.thoiGian);
            viewAnhXa.soGhe = (TextView) convertView.findViewById(R.id.soGhe);
            viewAnhXa.anhXe = (ImageView) convertView.findViewById(R.id.anhXe);
            convertView.setTag(viewAnhXa);
        }else{
            viewAnhXa = (ViewAnhXa) convertView.getTag();
        }

        Xe xe = list.get(position);

        viewAnhXa.tenXe.setText(xe.getTenXe());
        viewAnhXa.loaiXe.setText(xe.getLoaiXe());
        viewAnhXa.tuyen.setText("Tuyến: " + xe.getTuyen());
        viewAnhXa.thoiGian.setText("Thời gian: " + xe.getThoiGian());
        viewAnhXa.soGhe.setText(" - "+ xe.getSoGhe() + " chỗ");
        viewAnhXa.anhXe.setImageResource(xe.getAnhXe());

        return convertView;
    }
}
