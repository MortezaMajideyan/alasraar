package com.pattern.sarketab.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern.alasraar.R;
import com.pattern.sarketab.ui.adaptor.RVAdapter;
import com.pattern.sarketab.data.local.models.Details;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.data.local.models.Mounth_Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kavak ;)
 */
public class ShowMore extends AppCompatActivity {

    TextView title;
    TextView sangInfo;
    public static HashMap<String, Mounth_Data> monthInfo;
    RecyclerView recyclerView;
    RVAdapter adapter;
    List<Details> details = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_result);
        initView();
        int result =  getIntent().getExtras().getInt("num");
        Mounth_Data monthData = monthInfo.get(Integer.toString(result));
        sangInfo.setText(monthData.getSang_khoshyomn());
        details.add(new Details(1,monthData.getOnsor()));
        details.add(new Details(2,monthData.getSambol()));
        details.add(new Details(3,monthData.getRoz_eghbal()));
        details.add(new Details(4,monthData.getAddad_shans()));
        details.add(new Details(5,monthData.getRang()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RVAdapter(details);
        recyclerView.setAdapter(adapter);
    }

    public void initView(){
        title = findViewById(R.id.show_titleText);
        sangInfo = findViewById(R.id.show_titleContent);
        recyclerView = findViewById(R.id.rv);
        title.setVisibility(View.GONE);
        monthInfo = GetData.getMonthInfo(this);
    }
}
