package com.pattern.sarketab.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Sarketab.helper_location;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pattern.sarketab.data.local.models.GetData.Abjad;

/**
 * Created by kavak ;)
 */
public class ActivityEghamat extends AppCompatActivity {
    CustomFontEditText eghamatName;
    CustomFontEditText eghamatMotherName;
    CustomFontEditText eghamatCity;
    RelativeLayout eghamatClick;
    ImageView eghamatBack;
    CustomFontTextView eghamatNameSum;
    CustomFontTextView eghamatCitySum;
    CustomFontTextView eghamatMotherNameSum;
    int m_num= 0;
    int name_sum = 0;
    int mother_name_sum = 0;
    int city_num =0 ;
    helper_location db_helper_location;
    String txt, txt1, code_d, lang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_eghamat);
        initView();

        db_helper_location = new helper_location(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db_helper_location.numberOfRows() > 0){
                    if (isNetworkConnected()) get_location();

                }else {
                    if (isNetworkConnected()) get_location();
                    else offline();
                }
            }
        }).start();

        eghamatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        eghamatClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_sum!=0 && mother_name_sum!=0 && city_num!=0) {
                    int num = (name_sum + mother_name_sum + city_num + 65) % 4;
                    if (num == 0){
                        num =4;
                    }
                    Intent intent = new Intent(ActivityEghamat.this, ActivityResult.class);
                    intent.putExtra("num", num);
                    intent.putExtra("activity", 3);
                    startActivity(intent);
                }else {
                    Toast.makeText(ActivityEghamat.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
                }
            }
        });
        eghamatName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eghamatNameSum.setText("0");

                if (eghamatName.length() == 15){
                    check_char(eghamatName.getText().toString() , eghamatName);
                }

                if (!s.equals("")) {
                    name_sum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            name_sum += Abjad.get(m1[i]);
                            m_num+=1;
                            eghamatNameSum.setText(Integer.toString(name_sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });




        eghamatMotherName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eghamatMotherNameSum.setText("0");

                if (eghamatMotherName.length() == 15){
                    check_char(eghamatMotherName.getText().toString() , eghamatMotherName);
                }

                if (!s.equals("")) {
                    mother_name_sum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            mother_name_sum += Abjad.get(m1[i]);
                            m_num+=1;
                            eghamatMotherNameSum.setText(Integer.toString(mother_name_sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });




        eghamatCity.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eghamatCitySum.setText("0");

                if (eghamatCity.length() == 15){
                    check_char(eghamatCity.getText().toString() , eghamatCity);
                }

                if (!s.equals("")) {
                    city_num = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            city_num += Abjad.get(m1[i]);
                            m_num+=1;
                            eghamatCitySum.setText(Integer.toString(city_num));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });



    }




    public void initView(){
        Abjad = GetData.getAbjadList();
        eghamatName = findViewById(R.id.eghamat_name);
        eghamatCity = findViewById(R.id.eghamat_city);
        eghamatMotherName = findViewById(R.id.eghamat_mother_name);
        eghamatClick = findViewById(R.id.eghamat_natijeh);
        eghamatBack = findViewById(R.id.eghamat_back);
        eghamatNameSum = findViewById(R.id.eghamat_name_sum);
        eghamatCitySum = findViewById(R.id.eghamat_city_sum);
        eghamatMotherNameSum = findViewById(R.id.eghamat_mother_name_sum);

    }


    public void get_location() {
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.location("location");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try {

                    for (int i = 0; i < response.body().size(); i++) {
                        txt = response.body().get(i).title;
                        txt1 = response.body().get(i).txt;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        db_helper_location.insertLocation(txt, txt1, code_d, lang);

                    }

                } catch (NullPointerException e) {
                    //Toast.makeText(ActivityHajat.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                    offline();
                }
            }

            @Override
            public void onFailure(Call<List<M_map>> call, Throwable t) {
                //Toast.makeText(ActivityHajat.this , t.toString()+"||EggE",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void offline(){
        db_helper_location.insertLocation("فیه متاعب کثیرة","الإقامة في هذا المکان ستجلب المتاعب","1","ab");


        db_helper_location.insertLocation("مخیّرة","الإقامة في هذا المکان مخیرة و لیس فیه ضرر أو ربح","2","ab");

        db_helper_location.insertLocation("رزق قلیل","سیکون الرزق و الأرباح محدودة في هذا المکان","3","ab");

        db_helper_location.insertLocation("الربح و السعادة","الإقامة في هذا المکان مربحة جداً و فیه السعادة و الرفاهیة","4","ab");

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