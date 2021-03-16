package com.pattern.sarketab.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.ui.Sarketab.helper_hajat;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
/**
 * Created by kavak ;)
 */
public class ActivityHamzad extends DaggerAppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivityEzdevaj";
    CustomFontEditText hamzad_name;
    CustomFontTextView hamzad_name_sum;
    TextView text_freshteh , text_jenn;
    ImageView back;
    Spinner spinner;
    int NameSum;
    int m_num , thousand , sad , dah , yek;
    private HashMap<String,Integer> Abjad ;
    helper_hajat db_helper_hajat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hamzad);
        initView();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        hamzad_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    sum_slice(0);
                }

                if (hamzad_name.length() == 15){
                    check_char(hamzad_name.getText().toString() , hamzad_name);
                }

                hamzad_name_sum.setText("0");
                if (!s.equals("")) {
                    NameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            NameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            hamzad_name_sum.setText(Integer.toString(NameSum));
                            if(s.length() == 0){
                                sum_slice(0);
                            }else{
                                sum_slice(NameSum);
                            }
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
        hamzad_name = findViewById(R.id.hamzad_name);
        hamzad_name_sum = findViewById(R.id.hamzad_name_sum);
        text_freshteh = findViewById(R.id.text_freshteh);
        text_jenn= findViewById(R.id.text_jen);

        back = findViewById(R.id.ghaeb_back);
        spinner = findViewById(R.id.spinner);
    }

    @SuppressLint("SetTextI18n")
    public void sum_slice(int i){
      if (i == 0){
          text_jenn.setText(R.string.not_found);
          text_freshteh.setText(R.string.not_found);
      }else if (i > 2000){
          text_jenn.setText(R.string.not_found);
          text_freshteh.setText(R.string.not_found);
      }else{
          String t_0 = "000";
          String s_0 = "00";
          String d_0 = "0";
          String len = String.valueOf(i);
          int lenn = len.length();
          if (lenn > 3 ){
              thousand = Integer.parseInt(len.substring(0,1)+t_0);
              sad = Integer.parseInt(len.substring(1,2)+s_0);
              dah = Integer.parseInt(len.substring(2,3)+d_0);
              yek = Integer.parseInt(len.substring(3));

          }else if (lenn > 2){
              thousand = 0;
              sad = Integer.parseInt(len.substring(0,1)+s_0);
              dah = Integer.parseInt(len.substring(1,2)+d_0);
              yek = Integer.parseInt(len.substring(2));
          } else if (lenn > 1){
              thousand = 0;
              sad = 0;
              dah = Integer.parseInt(len.substring(0,1)+d_0);
              yek = Integer.parseInt(len.substring(1));
          }
          else if (lenn >0){
              thousand = 0;
              sad = 0;
              dah = 0;
              yek = Integer.parseInt(len.substring(1));
          }
          String a="";
          String b="";
          String c="";
          String d="";
          if (thousand != 0){
              a = get_key(thousand);
          }
          if (sad != 0){
              b = get_key(sad);
          }
          if (dah != 0){
              c = get_key(dah);
          }
          if (yek != 0){
              d = get_key(yek);
          }
          String text_fer = a+b+c+d;
          String text_jen = d+c+b+a;
          if (i > 0){
              text_freshteh.setText(text_fer+"ائیل");
          }else{
              text_freshteh.setText(R.string.not_found);
          }

          if (i > 0){
              text_jenn.setText(text_jen+"وش");
          }else{
              text_jenn.setText(R.string.not_found);
          }
          //Toast.makeText(this, i+"p", Toast.LENGTH_SHORT).show();
      }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public String get_key(int i){
        String key ="" ;
        Iterator<Map.Entry<String, Integer>> it = Abjad.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            if (pair.getValue() == i){
                key = pair.getKey();
            }
        }
        return key;
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


















