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
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.annotation.Nullable;

        import com.pattern.alasraar.R;
        import com.pattern.sarketab.data.local.models.GetData;
        import com.pattern.sarketab.data.preferences.LocalStorage;
        import com.pattern.sarketab.ui.Modle.M_map;
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
public class ActivityHajat extends DaggerAppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivityEzdevaj";
    CustomFontEditText hajatName;
    CustomFontEditText hajat_target_name;
    CustomFontEditText hajat_detail;
    CustomFontTextView hajatNameSum;
    CustomFontTextView hajat_target_name_sum;
    CustomFontTextView hajat_detail_sum;
    RelativeLayout ezdevajNatijeh;
    ImageView back;
    Spinner spinner;
    int m_num= 0;
    int NameSum;
    int targetSum;
    int detail_sum;
    private HashMap<String,Integer> Abjad ;
    helper_hajat db_helper_hajat;
    String txt, txt1, code_d, lang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hajat);
        initView();

        db_helper_hajat = new helper_hajat(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db_helper_hajat.numberOfRows() > 0){
                    if (isNetworkConnected()){
                        get_hajat();
                    }
                }else{
                    if (isNetworkConnected()){
                        get_hajat();
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


        ezdevajNatijeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NameSum !=0 && targetSum !=0 && detail_sum!=0 ) {
                    int num = (NameSum + targetSum + detail_sum) % 3;
                    Intent intent = new Intent(ActivityHajat.this, ActivityResult.class);
                    intent.putExtra("num", num);
                    intent.putExtra("activity", 11);
                    startActivity(intent);
                }else {
                    Toast.makeText(ActivityHajat.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
                }
            }
        });
        hajatName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hajatNameSum.setText("0");

                if (hajatName.length() == 15){
                    check_char(hajatName.getText().toString() , hajatName);
                }

                if (!s.equals("")) {
                    NameSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            NameSum += Abjad.get(m1[i]);
                            m_num+=1;
                            hajatNameSum.setText(Integer.toString(NameSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });



        hajat_target_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hajat_target_name_sum.setText("0");

                if (hajat_target_name.length() == 15){
                    check_char(hajat_target_name.getText().toString() , hajat_target_name);
                }

                if (!s.equals("")) {
                    targetSum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            targetSum += Abjad.get(m1[i]);
                            m_num+=1;
                            hajat_target_name_sum.setText(Integer.toString(targetSum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });


        hajat_detail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hajat_detail_sum.setText("0");

                if (hajat_detail.length() == 15){
                    check_char(hajat_detail.getText().toString() , hajat_detail);
                }

                if (!s.equals("")) {
                    detail_sum = 0;
                    String m =  s.toString();
                    m+=1;
                    String[] m1 = m.split("");
                    for (int i = 0; i < m1.length; i++) {
                        try {
                            detail_sum += Abjad.get(m1[i]);
                            m_num+=1;
                            hajat_detail_sum.setText(Integer.toString(detail_sum));
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
        hajatName = findViewById(R.id.hajat_name);
        hajatNameSum = findViewById(R.id.hajat_name_sum);

        hajat_target_name = findViewById(R.id.hajat_target_name);
        hajat_target_name_sum = findViewById(R.id.hajat_target_name_sum);

        hajat_detail = findViewById(R.id.hajat_detail);
        hajat_detail_sum = findViewById(R.id.hajat_detail_sum);

        back = findViewById(R.id.ghaeb_back);
        spinner = findViewById(R.id.spinner);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void get_hajat() {
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        Call<List<M_map>> call = service.need("need");
        call.enqueue(new Callback<List<M_map>>() {
            @Override
            public void onResponse(Call<List<M_map>> call, Response<List<M_map>> response) {
                try {

                    for (int i = 0; i < response.body().size(); i++) {
                        txt = response.body().get(i).title;
                        txt1 = response.body().get(i).txt;
                        code_d = response.body().get(i).code;
                        lang = response.body().get(i).lang;
                        db_helper_hajat.insertHajat(txt, txt1, code_d, lang);

                    }

                } catch (NullPointerException e) {
                    offline();
                    //Toast.makeText(ActivityHajat.this , e.toString()+"||EE",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<M_map>> call, Throwable t) {
                offline();
                //Toast.makeText(ActivityHajat.this , t.toString()+"||EggE",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void offline(){
            db_helper_hajat.insertHajat("" ,"یعني أن هذا الشخص لن یلبي طلبک و لن تنال المراد.","1","0");
            db_helper_hajat.insertHajat("" ,"یعني أن هذا الشخص سوف یلبّي طلبک بصعوبة.","2","0");
            db_helper_hajat.insertHajat("" ,"یعني أن هذا الشخص سوف یلبّي طلبک بنجاح.","0","0");

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

