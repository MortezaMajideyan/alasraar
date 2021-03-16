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
import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Sarketab.helper_sherakat;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kavak ;)
 */
public class ActivitySherakat extends AppCompatActivity {
    CustomFontEditText sherakatNameOne;
    CustomFontEditText sherakatNameTwo;
    CustomFontTextView  sherakatNameOneSum;
    CustomFontTextView  sherakatNameTwoSum;
    RelativeLayout sherakatNatijeh;
    ImageView back;
    int m_num= 0;
    int name1Sum = 0;
    int name2Sum = 0;
     HashMap<String,Integer> Abjad ;
     helper_sherakat db_sherakat;
    String txt, txt1, code_d, lang;
    boolean st;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sherakat);
        initView();

        db_sherakat = new helper_sherakat(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db_sherakat.numberOfRows() > 0){
                    if (isNetworkConnected()){
                        get_sherakat();
                    }
                }else{
                    if (isNetworkConnected()){
                        get_sherakat();
                    }else{
                        offline();
                    }
                }
            }
        }).start();




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        sherakatNatijeh.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (name2Sum!=0 && name1Sum!=0) {
                int num = (name1Sum + name2Sum) % 5;
                if (num == 0){
                    num = 5;
                }
                Intent intent = new Intent(ActivitySherakat.this, ActivityResult.class);
                intent.putExtra("num", num);
                intent.putExtra("activity", 2);
                startActivity(intent);
            }else {
                Toast.makeText(ActivitySherakat.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
            }
        }
        });

        sherakatNameOne.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sherakatNameOneSum.setText("0");


                if (sherakatNameOne.length() == 15){
                    check_char(sherakatNameOne.getText().toString() , sherakatNameOne);
                }
                if (!s.equals("")) {
                    name1Sum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            name1Sum += Abjad.get(m1[i]);
                            m_num+=1;
                            sherakatNameOneSum.setText(Integer.toString(name1Sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });

        sherakatNameTwo.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sherakatNameTwoSum.setText("0");


                if (sherakatNameTwo.length() == 15){
                    check_char(sherakatNameTwo.getText().toString() , sherakatNameTwo);
                }

                if (!s.equals("")) {
                    name2Sum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            name2Sum += Abjad.get(m1[i]);
                            m_num+=1;
                            sherakatNameTwoSum.setText(Integer.toString(name2Sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });

    }

    public void initView(){
        Abjad = GetData.getAbjadList();
        back = findViewById(R.id.sherakat_back);
        sherakatNameOne = findViewById(R.id.sherakat_name_one);
        sherakatNameTwo = findViewById(R.id.sherakat_name_two);
        sherakatNameOneSum = findViewById(R.id.sherakat_name_one_sum);
        sherakatNameTwoSum = findViewById(R.id.sherakat_name_two_sum);
        sherakatNatijeh = findViewById(R.id.sherakat_Natijeh);
    }

    public void get_sherakat() {
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.partnership("partnership");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try {

                    for (int i = 0; i < response.body().size(); i++) {
                        txt = response.body().get(i).title;
                        txt1 = response.body().get(i).txt;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        db_sherakat.insertSherakat(txt, txt1, code_d, lang);

                    }

                } catch (NullPointerException e) {
                    offline();
                    //Toast.makeText(ActivityAbjad.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<M_map>> call, Throwable t) {
                offline();
                //Toast.makeText(ActivityAbjad.this , t.toString()+"||EggE",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void offline(){
        db_sherakat.insertSherakat("جید جدا"  , "یمکن أن یکونا رفیقان حمیمان و سوف تکون شراکتهم المادیة جیدة. سوف یحصلان علی الربح الکبیر و سیحصلا علی النجاح. من الممکن بقائهم في الشراکة لمدة طویلة." , "1" , "ab");
        db_sherakat.insertSherakat("النتیجة سلبیة " , "الصداقة ستبدأ بالخیر و الشراکة التجاریة تبدأ بهدوء و تنتهي بالمشاکل و ستکون العلاقة مکدرة. " , "2" , "ab");
        db_sherakat.insertSherakat("جید" , "الصداقة بینکما جیدة لکن علیکم الاحتفاظ بخصوصیة حیاتکما. الشراکة التجاریة جیدة بینکما لکنها ستشهد التغییرات احیاناً. لو راعیتم قوانین العمل فسوف تشهدا نجاحاً کبیرا. " , "3" , "ab");
        db_sherakat.insertSherakat("النتیجة سلبیة" , "صداقتکما ستکون نسبیة و تحصل مشاکل کثیرة في حال الشراکة التجاریة. في البدایة تکون الأمور هادئة لکنها تنتهي بالمتاعب و لن تکون مربحة و فیها أضرار کثیرة. " , "4" , "ab");
        db_sherakat.insertSherakat("جید جدا" , "الصداقة بینکما ستکون مثالیة و لیس لها مثیل. لو کانت شراکة تجاریة بینکم سوف تکون ناجحة جداً و سوف تصلان إلی اعلی قمم النجاح. المشاکل التجاریة بینکما لیست ذات أهمیة و سوف تزول سریعا. " , "5" , "ab");
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