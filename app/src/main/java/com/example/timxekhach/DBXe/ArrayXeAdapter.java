package com.example.timxekhach.DBXe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timxekhach.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArrayXeAdapter extends RecyclerView.Adapter<ArrayXeAdapter.XeViewHolder> implements Filterable {

    private Context mContext;
    private List<Xe> arrListXe;
    private List<Xe> arrListXeOld;

    public ArrayXeAdapter(Context context, List<Xe> arrListXe) {
        this.mContext = context;
        this.arrListXe = arrListXe;
        this.arrListXeOld = arrListXe;
    }

    @NonNull
    @Override
    public XeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_xe, viewGroup, false);
        return new XeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XeViewHolder xeViewHolder, int i) {
        Xe xe = arrListXe.get(i);
        if(xe == null){
            return;
        }
        xeViewHolder.anhXe.setImageResource(xe.getAnhXe());
        xeViewHolder.tenXe.setText(xe.getTenXe());
        xeViewHolder.loaiXe.setText(xe.getLoaiXe() + " - " + xe.getSoGhe() + " chỗ");
        xeViewHolder.tuyen.setText(("Tuyến: " + xe.getTuyen()));
        xeViewHolder.thoiGian.setText("Thời gian: " + xe.getThoiGian());
        xeViewHolder.loItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToXeInformation(xe);
            }
        });
    }

    private void onClickGoToXeInformation(Xe xe) {
        Intent intent = new Intent(mContext, XeInformation.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("idxe", xe.getIdXe());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(arrListXe != null){
            return arrListXe.size();
        }
        return 0;
    }

    public class XeViewHolder extends RecyclerView.ViewHolder{

        private ImageView anhXe;
        private TextView tenXe, loaiXe, tuyen, thoiGian;
        private RelativeLayout loItem;

        public XeViewHolder(@NonNull View itemView) {
            super(itemView);
            loItem = itemView.findViewById(R.id.item_layout);
            tenXe = itemView.findViewById(R.id.tenXe);
            loaiXe  = itemView.findViewById(R.id.loaiXe);
            tuyen =  itemView.findViewById(R.id.tuyen);
            thoiGian = itemView.findViewById(R.id.thoiGian);
            anhXe =  itemView.findViewById(R.id.anhXe);
        }
    }

    public static String removeAccent(String s) { String temp = Normalizer.normalize(s, Normalizer.Form.NFD); Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d"); }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    arrListXe = arrListXeOld;
                }
                else{
                    List<Xe> list = new ArrayList<>();
                    for(Xe xe : arrListXeOld){
                        if(removeAccent(xe.getTenXe().toLowerCase()).contains(removeAccent(strSearch.toLowerCase()))){
                            list.add(xe);
                        }
                    }
                    arrListXe = list;
                }

                FilterResults ftr = new FilterResults();
                ftr.values = arrListXe;

                return ftr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrListXe = (List<Xe>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

}
