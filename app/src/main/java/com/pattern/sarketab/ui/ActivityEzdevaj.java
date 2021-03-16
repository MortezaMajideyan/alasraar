package com.pattern.sarketab.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Modle.M_sarketab;
import com.pattern.sarketab.ui.Sarketab.helper_ezdevaj;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.pattern.sarketab.util.IabHelper;
import com.pattern.sarketab.util.IabResult;
import com.pattern.sarketab.util.Inventory;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by kavak ;)
 */
public class ActivityEzdevaj extends DaggerAppCompatActivity {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivityEzdevaj";

    CustomFontEditText ezdevajManName;
    CustomFontEditText ezdevajManMotherName;
    CustomFontEditText ezdevajWomanName;
    CustomFontEditText ezdevajWomanMotherName;
    CustomFontTextView ezdevajManNameSum;
    CustomFontTextView ezdevajManMotherNameSum;
    CustomFontTextView ezdevajWomanNameSum;
    CustomFontTextView ezdevajWomanMotherNameSum;
    RelativeLayout ezdevajNatijeh;
    ImageView back;
    int m_num= 0;
    int manNameSum;
    int womanNameSum;
    int manMotherNameSum;
    int womanMotherNameSum;
    private HashMap<String,Integer> Abjad ;

    helper_ezdevaj my_helper_ezdevaj;

    String txt ,txt1,code_d,lang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ezdevaj);
        initView();
        my_helper_ezdevaj = new helper_ezdevaj(this);
        if (my_helper_ezdevaj.numberOfRows() > 0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (isNetworkConnected()){
                        get_ezdevaj();
                    }
                }
            }).start();

        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (isNetworkConnected()){
                        get_ezdevaj();
                    }
                }
            }).start();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ezdevajNatijeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manNameSum!=0 && womanNameSum!=0 && manMotherNameSum!=0 && womanMotherNameSum!=0) {
                    int num = (manNameSum + womanNameSum + manMotherNameSum + womanMotherNameSum) % 5;
                    Intent intent = new Intent(ActivityEzdevaj.this, ActivityResult.class);
                    intent.putExtra("num", num);
                    intent.putExtra("activity", 5);
                    startActivity(intent);
                }else {
                    Toast.makeText(ActivityEzdevaj.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
                }
            }
        });

        ezdevajManName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ezdevajManNameSum.setText("0");

                if (ezdevajManName.length() == 15){
                    check_char(ezdevajManName.getText().toString() , ezdevajManName);
                }

                if (!s.equals("")) {
                    manNameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            manNameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            ezdevajManNameSum.setText(Integer.toString(manNameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });



        ezdevajManMotherName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ezdevajManMotherNameSum.setText("0");

                if (ezdevajManMotherName.length() == 15){
                    check_char(ezdevajManMotherName.getText().toString() , ezdevajManMotherName);
                }

                if (!s.equals("")) {
                    manMotherNameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            manMotherNameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            ezdevajManMotherNameSum.setText(Integer.toString(manMotherNameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });


        ezdevajWomanName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ezdevajWomanNameSum.setText("0");

                if (ezdevajWomanName.length() == 15){
                    check_char(ezdevajWomanName.getText().toString() , ezdevajWomanName);
                }

                if (!s.equals("")) {
                    womanNameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            womanNameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            ezdevajWomanNameSum.setText(Integer.toString(womanNameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });


        ezdevajWomanMotherName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ezdevajWomanMotherNameSum.setText("0");

                if (ezdevajWomanMotherName.length() == 15){
                    check_char(ezdevajWomanMotherName.getText().toString() , ezdevajWomanMotherName);
                }

                if (!s.equals("")) {
                    womanMotherNameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            womanMotherNameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            ezdevajWomanMotherNameSum.setText(Integer.toString(womanMotherNameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });




    }


    public void initView(){
        Abjad = GetData.getAbjadList();
        back = findViewById(R.id.back);
        ezdevajNatijeh = findViewById(R.id.ezdevaj_natijeh);
        ezdevajManMotherName = findViewById(R.id.ezdevaj_man_mother_name);
        ezdevajManMotherNameSum = findViewById(R.id.ezdevaj_man_mother_name_sum);
        ezdevajManName = findViewById(R.id.ezdevaj_man_name);
        ezdevajManNameSum = findViewById(R.id.ezdevaj_man_name_sum);
        ezdevajWomanName = findViewById(R.id.ezdevaj_woman_name);
        ezdevajWomanNameSum = findViewById(R.id.ezdevaj_woman_name_sum);
        ezdevajWomanMotherName = findViewById(R.id.ezdevaj_woman_mother_name);
        ezdevajWomanMotherNameSum = findViewById(R.id.ezdevaj_woman_mother_name_sum);
    }
    public void get_ezdevaj(){
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.married("marriage");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try{

                    for (int i =0; i < response.body().size() ; i++){
                        txt = response.body().get(i).txt;
                        txt1 = response.body().get(i).txt1;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        my_helper_ezdevaj.insertEzdevaj(txt , txt1 , code_d , lang );

                    }

                }catch (NullPointerException e){
                    //Toast.makeText(ActivityEzdevaj.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<M_map>> call, Throwable t) {
                //Toast.makeText(ActivityEzdevaj.this , t.toString()+"||EggE",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void  check_char(String text , CustomFontEditText edit){
        String ch = "";

        int f = 0;
        int c = 0;
        for (int i=0 ; i<text.length()-1 ; i++) {
            int b = i;
            if (b >= 0 && b < text.length()) {
                ch = text.substring(i, (i + 1));
            }
            //Toast.makeText(this, b+":"+b++, Toast.LENGTH_SHORT).show();


            if (ch.equals("ط") || ch.equals("ظ") || ch.equals(" ")){
                f++;
                if(f > 7){
                    edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
                }else{
                    edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
                }
            }else{
                if(f > 7){
                    edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
                }else{
                    edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
                }
            }


            if (ch.equals("ص") || ch.equals(" ")){
                c++;
                edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(17)});
            }
        }
    }

}