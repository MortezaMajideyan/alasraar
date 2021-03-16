package com.pattern.sarketab.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Sarketab.helper_absent;
import com.pattern.sarketab.ui.Sarketab.helper_hajat;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
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
public class ActivityGhaeb extends DaggerAppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivityEzdevaj";
    CustomFontEditText ghaebName;
    CustomFontEditText ghaebMotherName;
    CustomFontTextView ghaebNameSum;
    CustomFontTextView ghaebMotherNameSum;
    RelativeLayout ezdevajNatijeh;
    ImageView back;
    Spinner spinner;
    int m_num= 0;
    int manNameSum;
    int manMotherNameSum;
    int sum_day;
    private HashMap<String,Integer> Abjad ;
    private  String[] day_week ;
    helper_absent db_helper_absent;
    String txt, txt1, code_d, lang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ghaeb);

        day_week =new String[]{
                getResources().getString(R.string.Saturday),
                getResources().getString(R.string.Sunday),
                getResources().getString(R.string.Monday),
                getResources().getString(R.string.Tuesday),
                getResources().getString(R.string.Wednesday),
                getResources().getString(R.string.Thursday),
                getResources().getString(R.string.Friday),
        };




        initView();

        db_helper_absent = new helper_absent(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db_helper_absent.numberOfRows() > 12){
                    if (isNetworkConnected()){
                        get_absent();
                    }
                }else{
                    if (isNetworkConnected()){
                        get_absent();
                    }else{
                        offline();
                    }
                }
            }
        }).start();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityGhaeb.this,
                android.R.layout.simple_spinner_item,day_week);

        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);






        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ezdevajNatijeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manNameSum!=0 && manMotherNameSum!=0 ) {
                    int num = (manNameSum + manMotherNameSum + sum_day) % 12;
                    if (num == 0){
                        num = 12;
                    }
                    Intent intent = new Intent(ActivityGhaeb.this, ActivityResult.class);
                    intent.putExtra("num", num);
                    intent.putExtra("activity", 10);
                    startActivity(intent);
                }else {
                    Toast.makeText(ActivityGhaeb.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
                }
            }
        });

        ghaebName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ghaebNameSum.setText("0");

                if (ghaebName.length() == 15){
                    check_char(ghaebName.getText().toString() , ghaebName);
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
                            ghaebNameSum.setText(Integer.toString(manNameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });



        ghaebMotherName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ghaebMotherNameSum.setText("0");

                if (ghaebMotherName.length() == 15){
                    check_char(ghaebMotherName.getText().toString() , ghaebMotherName);
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
                            ghaebMotherNameSum.setText(Integer.toString(manMotherNameSum));
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
        ezdevajNatijeh = findViewById(R.id.ghaeb_natijeh);
        ghaebMotherName = findViewById(R.id.ghaeb_mother_name);
        ghaebMotherNameSum = findViewById(R.id.ghaeb_mother_name_sum);
        ghaebName = findViewById(R.id.ghaeb_name);
        ghaebNameSum = findViewById(R.id.ghaeb_name_sum);
        back = findViewById(R.id.ghaeb_back);
        spinner = findViewById(R.id.spinner);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sum_day = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void get_absent() {
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.absent_status("absent_status");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try {

                    for (int i = 0; i < response.body().size(); i++) {
                        txt = response.body().get(i).title;
                        txt1 = response.body().get(i).txt;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        db_helper_absent.insertAbsent(txt, txt1, code_d, lang);

                    }

                } catch (NullPointerException e) {
                    //Toast.makeText(ActivityGhaeb.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                    offline();
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
        db_helper_absent.insertAbsent("","الشخص الغائب سیرجع بأموال کثیرة." ,"1","0");
        db_helper_absent.insertAbsent("","الفرد الغائب مشغول بوظیفة حکومیة." ,"2","0");
        db_helper_absent.insertAbsent("","الشخص الغائب یتأخر بالمجئ." ,"3","0");
        db_helper_absent.insertAbsent("","الشخص الغائب مکانه آمن." ,"4","0");
        db_helper_absent.insertAbsent("","الشخص الغائب متعب." ,"5","0");
        db_helper_absent.insertAbsent("","الشخص الغائب بتمام صحته و سلامته." ,"6","0");
        db_helper_absent.insertAbsent("","الشخص الغائب بتمام صحته و سلامته ایضاً." ,"7","0");
        db_helper_absent.insertAbsent("","الشخص الغائب سیبقی في مکانه." ,"8","0");
        db_helper_absent.insertAbsent("","الشخص الغائب مصاب بالمرض." ,"9","0");
        db_helper_absent.insertAbsent("","الشخص الغائب في طریقه للرجوع." ,"10","0");
        db_helper_absent.insertAbsent("","الشخص الغائب إنتقل إلی مکان آخر. " ,"11","0");
        db_helper_absent.insertAbsent("","وضع الشخص الغائب في إبهام و مصیره لیس واضح." ,"12","0");
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



