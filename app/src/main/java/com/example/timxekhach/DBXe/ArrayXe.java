package com.example.timxekhach.DBXe;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import com.example.timxekhach.Database.SQLXe;
import com.example.timxekhach.MainActivity;
import com.example.timxekhach.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArrayXe extends AppCompatActivity {

    private RecyclerView rcvXe;
    private ArrayXeAdapter arrayXeAdapter;
    private SearchView searchView;

    public static int idXe;
    public static int getIdXe() {
        return idXe;
    }
    public static void setIdXe(int idXe) {
        ArrayXe.idXe = idXe;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_xe);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tất cả Xe");

        rcvXe = findViewById(R.id.rcv_xe);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcvXe.setLayoutManager(llm);

        ArrayList<Xe> listxe = (ArrayList<Xe>) getListXe();
        if(listxe.size()==0){
            Toast.makeText( this, "Không có chuyến xe nào phù hợp!", Toast.LENGTH_SHORT).show();
        }
        else{
            arrayXeAdapter = new ArrayXeAdapter(this, listxe);
            rcvXe.setAdapter(arrayXeAdapter);

            RecyclerView.ItemDecoration itd = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL );
            rcvXe.addItemDecoration(itd);
        }

    }

    public void OpenThongTinXe(){
        Intent intent = new Intent(ArrayXe.this, XeInformation.class);
        startActivity(intent);
    }

    private List<Xe> getListXe() {
        SQLXe sqlXe = new SQLXe(this);
        ArrayList<Xe> listAllxe = sqlXe.getAllXe();
        String tuyen = removeAccent(MainActivity.getTuyen().trim().toLowerCase());
        if(!tuyen.equals("no")){
            ArrayList<Xe> listxe = new ArrayList<>();
            for(Xe xe : listAllxe){
                if(removeAccent(xe.getTuyen().trim().toLowerCase()).equals(tuyen)){
                    listxe.add(xe);
                }
            }
            return listxe;
        }
        else return sqlXe.getAllXe();
    }

    public static String removeAccent(String s) { String temp = Normalizer.normalize(s, Normalizer.Form.NFD); Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d"); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.arrayxe_menu, menu);

        SearchManager srm = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(srm.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                arrayXeAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayXeAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}