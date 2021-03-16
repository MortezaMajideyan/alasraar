package com.pattern.sarketab.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Sarketab.helper_abjad;
import com.pattern.sarketab.ui.main.MainActivity;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kavak ;)
 */
public class ActivityAbjad extends AppCompatActivity {
    CustomFontTextView abjadName1;
    CustomFontTextView abjadName2;
    CustomFontTextView abjadName3;
    RelativeLayout estekharehGeneration;
    RelativeLayout estekharehNatijeh;
    CustomFontTextView clickText;
    int generation = 0;
    HashMap<String, String> Abjad;
    HashMap<String, String> send_to_database;
    helper_abjad mydb_abjad_helper;
    String txt, txt1, code_d, lang;
    ImageView back_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_estekhareh);
        mydb_abjad_helper = new helper_abjad(this);
        initView();
        if (mydb_abjad_helper.numberOfRows() > 0) {
           // Toast.makeText(this, "dfsdd", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (isNetworkConnected()){
                        get_abjad();
                    }
                }
            }).start();
        } else {
           // Toast.makeText(this, "dfs", Toast.LENGTH_SHORT).show();
            if (isNetworkConnected()){
                get_abjad();
            }else{
              insert_abjad_off();
            }
        }


        estekharehNatijeh.setVisibility(View.INVISIBLE);
        estekharehGeneration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generation += 1;
                int num = GeneratInt();
                if (generation == 1) {
                    abjadName1.setText(Abjad.get(Integer.toString(num)));
                    clickText.setText(R.string.click2);
                } else if (generation == 2) {
                    abjadName2.setText(Abjad.get(Integer.toString(num)));
                    clickText.setText(R.string.click3);
                } else if (generation == 3) {
                    estekharehGeneration.setVisibility(View.INVISIBLE);
                    estekharehNatijeh.setVisibility(View.VISIBLE);
                    clickText.setText(R.string.click1);
                    abjadName3.setText(Abjad.get(Integer.toString(num)));
                }
            }
        });
        estekharehNatijeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAbjad.this, ActivityResult.class);
                intent.putExtra("abjad", abjadName1.getText().toString() + abjadName2.getText().toString() + abjadName3.getText().toString());
                intent.putExtra("activity", 1);
                startActivity(intent);
                abjadName1.setText("");
                abjadName2.setText("");
                abjadName3.setText("");
                estekharehNatijeh.setVisibility(View.INVISIBLE);
                estekharehGeneration.setVisibility(View.VISIBLE);
                generation = 0;
            }
        });


        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        get_lang();
    }


    public void initView() {
        Abjad = GetData.getAbjad_alpha();
        send_to_database = GetData.getFall_explain(this);
        abjadName1 = findViewById(R.id.estekhareh_Name1);
        abjadName2 = findViewById(R.id.estekhareh_Name2);
        abjadName3 = findViewById(R.id.estekhareh_Name3);
        clickText = findViewById(R.id.clickText);
        estekharehGeneration = findViewById(R.id.estekhareh_generate);
        estekharehNatijeh = findViewById(R.id.estekhareh_Natijeh);
        back_image = findViewById(R.id.back);
    }

    public int GeneratInt() {
        return new Random().nextInt(4) + 1;
    }

    public void get_abjad() {
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.abjad_istikharah("abjad_istikharah");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try {

                    for (int i = 0; i < response.body().size(); i++) {
                        txt = response.body().get(i).title;
                        txt1 = response.body().get(i).txt;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        mydb_abjad_helper.insertAbjad(txt, txt1, code_d, lang);

                    }

                } catch (NullPointerException e) {
                   // Toast.makeText(ActivityAbjad.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                    insert_abjad_off();
                }
            }

            @Override
            public void onFailure(Call<List<M_map>> call, Throwable t) {
                insert_abjad_off();
               // Toast.makeText(ActivityAbjad.this , t.toString()+"||EggE",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void insert_abjad_off(){
        Iterator<Map.Entry<String, String>> it = send_to_database.entrySet().iterator();
        int i = 0;
        //Toast.makeText(this, "ds", Toast.LENGTH_SHORT).show();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
            Log.e("morteza", pair.getKey() + " = " + pair.getValue());
            mydb_abjad_helper.insertAbjad(pair.getKey(), pair.getValue(), "0","1");
            i++;
        }
    }


    public void get_lang(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        String localeString = ims.getLocale();
        Locale locale = new Locale(localeString);
        String currentLanguage = locale.getDisplayLanguage();


        //Toast.makeText(this, currentLanguage.toString(), Toast.LENGTH_SHORT).show();
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
