package com.pattern.sarketab.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;


import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.GetData;
import com.pattern.sarketab.data.local.models.Mounth_Data;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.ui.Sarketab.helper_abjad;
import com.pattern.sarketab.ui.Sarketab.helper_absent;
import com.pattern.sarketab.ui.Sarketab.helper_changename;
import com.pattern.sarketab.ui.Sarketab.helper_death;
import com.pattern.sarketab.ui.Sarketab.helper_ezdevaj;
import com.pattern.sarketab.ui.Sarketab.helper_hajat;
import com.pattern.sarketab.ui.Sarketab.helper_location;
import com.pattern.sarketab.ui.Sarketab.helper_sarketab;
import com.pattern.sarketab.ui.Sarketab.helper_sherakat;
import com.pattern.sarketab.ui.payment.ActivityPay;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import devliving.online.securedpreferencestore.SecuredPreferenceStore;

/**
 * Created by kavak ;)
 */
public class ActivityResult extends DaggerAppCompatActivity {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivityResult";

    public static HashMap<String,String> fall_explain;
    CustomFontTextView content;
    CustomFontTextView title;
    RelativeLayout ozviat;
    CardView ezdevajCard;
    CustomFontTextView ezdevajContent;
    CustomFontTextView ozviatText;
    CardView contentCard;
    helper_sarketab  mydb_sarketab;
    CustomFontTextView sarketabContent;
    Cursor mydb;
    String title_top;
    public static  HashMap<String, Mounth_Data> monthInfo;
    int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_result);
        initView();



        ozviat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        final int status = getIntent().getExtras().getInt("activity");

        ozviat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (!checkShterak().equals("")) {
                    if (status == 4) {
                        int number = getIntent().getExtras().getInt("num");
                        int gender = getIntent().getExtras().getInt("gender");
                        int day = getIntent().getExtras().getInt("month");
                        Intent intent = new Intent(ActivityResult.this, ShowMore.class);
                        intent.putExtra("num", number);
                        intent.putExtra("gender", gender);
                        intent.putExtra("day", day);
                        startActivity(intent);

                    }
                }else {
                    /*Intent intent = new Intent(ActivityResult.this, ActivityPay.class);
                    startActivity(intent);
                    int number = getIntent().getExtras().getInt("num");
                    int gender = getIntent().getExtras().getInt("gender");
                    int day = getIntent().getExtras().getInt("month");
                    Intent intent = new Intent(ActivityResult.this, ShowMore.class);
                    intent.putExtra("num", number);
                    intent.putExtra("gender", gender);
                    intent.putExtra("day", day);
                    startActivity(intent);

                }*/
                Toast.makeText(ActivityResult.this, "عمليات الدفع قيد التنفيذ", Toast.LENGTH_SHORT).show();
            }
        });
        switch (status){
            case 1:

                helper_abjad my_abjad_helper = new helper_abjad(this);
                String value = getIntent().getExtras().getString("abjad");

                fall_explain = GetData.getFall_explain(this);
                String[] val = value.split("");
                try{
                    value = val[1]+" "+val[2]+" "+val[3];
                }catch (Exception e){
                    value = val[0]+" "+val[1]+" "+val[2];
                }

                //Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
                Cursor con = my_abjad_helper.getabjad(value);
                if (con != null && con.getCount() > 0){
                    while (con.moveToNext()){
                        title_top = decode_abjad(con.getString(1));
                        if (title_top.length() > 2){
                            title.setText(value);
                        }
                        String te = decode(con.getString(2));
                        content.setText(te);
                    }

                }

                break;
            case 2:
            helper_sherakat db_sherakat = new helper_sherakat(this);
                String text_sher = "";
                String ti = "";
                result =  getIntent().getExtras().getInt("num");
                mydb = db_sherakat.getData(result+"");
                if (mydb.getCount() > 0 && mydb != null) {
                    while (mydb.moveToNext()) {
                        text_sher = decode(mydb.getString(2));
                        ti = decode(mydb.getString(1));
                        content.setText(text_sher);
                        title.setText(ti);
                    }
                }
                break;
            case 3:

                helper_location db_location = new helper_location(this);
                String text_location = "";
                result =  getIntent().getExtras().getInt("num");
                mydb = db_location.getData(result+"");
                if (mydb.getCount() > 0 && mydb != null) {
                    while (mydb.moveToNext()) {
                        title_top = decode(mydb.getString(1));
                        if (title_top.length() > 2){
                            title.setText(title_top);
                        }
                        text_location = decode(mydb.getString(2));
                        content.setText(text_location);
                    }
                }
                break;

            case 4:
                result =  getIntent().getExtras().getInt("num");
                int gender =  getIntent().getExtras().getInt("gender");
                String text_men ="";
                String text_women ="";
                mydb_sarketab =new  helper_sarketab(this);
                mydb = mydb_sarketab.getData(result);
                String titlee = "";
                if (mydb != null && mydb.getCount() > 0 ) {
                    while (mydb.moveToNext()) {
                        titlee = decode(mydb.getString(1));
                        text_men = decode(mydb.getString(3));
                        text_women = decode(mydb.getString(2));
                        title.setText(titlee);
                        if (gender==0){
                            if (text_men !=null)
                                content.setText(text_men+"\n\n\n"+getResources().getString(R.string.ozviat_explain));
                        }else if (gender==1){
                            if (text_women != null)
                                content.setText(text_women+"\n\n\n"+getResources().getString(R.string.ozviat_explain));
                        }
                    }
                }
                sarketabContent.setVisibility(View.GONE);
                ozviat.setVisibility(View.VISIBLE);

                if (!checkShterak().equals("")){
                    //ozviatText.setText("مشاهده بیشتر");
                    //content.setText(getResources().getString(R.string.forMoreResult));
                }else {
                    //content.setText(getResources().getString(R.string.ozviat_explain));
                }

                break;

            case 5:

                helper_ezdevaj db_helper_ezdevaj = new helper_ezdevaj(this);
                String ezdevj ="";

                result =  getIntent().getExtras().getInt("num");
                //Toast.makeText(this, ":"+result, Toast.LENGTH_SHORT).show();
                ezdevajCard.setVisibility(View.VISIBLE);
                if (result==0){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }
                    sarketabContent.setVisibility(View.VISIBLE);
                    contentCard.setVisibility(View.GONE);
                    sarketabContent.setText("بحث و جدل دلیل بر جدایی و .... نیست و باید توجه داشت بحث و جدل در اینجا بحث های کوچک نیز شامل آن میشود که راهکار هایی برای از بین بردن آن در همین محاسبات ابجد ذکر شده است");
                }else if (result==1){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }
                    contentCard.setVisibility(View.GONE);
                    //ezdevajContent.setText(" این دو به هم می رسند و ستاره آنها جفت می باشد وتا آخر با هم زندگی می کنند");
                }else if (result==2){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }
                    contentCard.setVisibility(View.GONE);

                }else if (result==3){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }
                    contentCard.setVisibility(View.GONE);
                }else if (result==4){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }
                    sarketabContent.setVisibility(View.VISIBLE);
                    if (!checkShterak().equals("")){
                        sarketabContent.setText(getResources().getString(R.string.solveEzdevajproblem));
                        contentCard.setVisibility(View.GONE);
                    }else {
                        sarketabContent.setText(getResources().getString(R.string.solveEzdevajproblem));
                        contentCard.setVisibility(View.GONE);
                    }
                }else if (result==5){
                    mydb = db_helper_ezdevaj.getData(result+"");
                    if (mydb.getCount() > 0 && mydb != null){
                        while (mydb.moveToNext()){
                            ezdevj = decode(mydb.getString(1));
                            ezdevajContent.setText(ezdevj);
                        }
                    }

                    ezdevajContent.setText( decode(mydb.getString(1)));
                    contentCard.setVisibility(View.GONE);
                }

                break;

            case 6:
                String month = getIntent().getExtras().getString("month");
                String content = getIntent().getExtras().getString("content");
                title.setText(month);
               this.content.setText(content);
                break;

            case 7:
                title.setText(R.string.show);
                helper_death db_death = new helper_death(this);
                ezdevajCard.setVisibility(View.VISIBLE);
                String text_death = "";
                result =  getIntent().getExtras().getInt("num");

                if (result % 2 == 0){
                    mydb = db_death.getData("0");
                    if (mydb.getCount() > 0 && mydb != null) {
                        while (mydb.moveToNext()) {
                            title_top = decode(mydb.getString(1));
                            if (title_top.length() > 2){
                                title.setText(title_top);
                            }
                            text_death = decode(mydb.getString(2));
                            ezdevajContent.setText(text_death);
                        }
                    }
                }else {
                    mydb = db_death.getData("1");
                    if (mydb.getCount() > 0 && mydb != null) {
                        while (mydb.moveToNext()) {
                            title_top = decode(mydb.getString(1));
                            if (title_top.length() > 2){
                                title.setText(title_top);
                            }
                            text_death = decode(mydb.getString(2));
                            ezdevajContent.setText(text_death);
                        }
                    }
                }

                contentCard.setVisibility(View.GONE);

                break;
            case 10:
                helper_absent db_helper_absent = new helper_absent(this);
                result =  getIntent().getExtras().getInt("num");
                ezdevajCard.setVisibility(View.VISIBLE);
                title.setText(R.string.show_absent_fal);
                mydb = db_helper_absent.getData(result + "");
                if (mydb.getCount() > 0 && mydb != null) {
                    while (mydb.moveToNext()) {
                        title_top =decode(mydb.getString(1));
                        if (title_top.length() > 2){
                            title.setText(title_top);
                        }
                        ezdevj = decode(mydb.getString(2));
                        ezdevajContent.setText(ezdevj);
                    }
                }
                contentCard.setVisibility(View.GONE);

                break;
            case 11:
                helper_hajat db_helper_hajat = new helper_hajat(this);
                result =  getIntent().getExtras().getInt("num");
                ezdevajCard.setVisibility(View.VISIBLE);
                mydb = db_helper_hajat.getData(result + "");
                if (mydb.getCount() > 0 && mydb != null) {
                    while (mydb.moveToNext()) {
                        title_top = decode(mydb.getString(1));
                        if (title_top.length() > 2){
                            title.setText(title_top);
                        }
                        ezdevj = decode(mydb.getString(2));
                        ezdevajContent.setText(ezdevj);
                    }
                }
                contentCard.setVisibility(View.GONE);

                break;
            case 12:
                helper_changename db_change_name = new helper_changename(this);
                result =  getIntent().getExtras().getInt("num");
                ezdevajCard.setVisibility(View.VISIBLE);
                mydb = db_change_name.getData(result + "");
                if (mydb.getCount() > 0 && mydb != null) {
                    while (mydb.moveToNext()) {
                        title_top = decode(mydb.getString(1));
                        if (title_top.length() > 2){
                            title.setText(title_top);
                        }
                        ezdevj = decode(mydb.getString(2));
                        ezdevajContent.setText(ezdevj);
                    }
                }
                contentCard.setVisibility(View.GONE);

                break;
        }
    }
    public void initView(){
        ozviat = findViewById(R.id.result_ozviat);
        title = findViewById(R.id.show_titleText);
        content = findViewById(R.id.show_titleContent);
        sarketabContent = findViewById(R.id.show_Content);
        ezdevajCard = findViewById(R.id.ezdevaj_card);
        ezdevajContent = findViewById(R.id.show_ezdevaj);
        contentCard = findViewById(R.id.content_card);
        ozviatText = findViewById(R.id.ozviat_txt);

    }
    public String checkShterak(){
        if (localStorage.readMessage("payApp").equals("true")){
            return "true";
        }else {
            try {
                SecuredPreferenceStore prefStore2 = SecuredPreferenceStore.getSharedInstance();
                return prefStore2.getString("pay", "");
            }catch (Exception e){
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                return pref.getString("pay", "");
            }
        }

    }

    public String decode(String text){

        String val2 = "";
        try {
            String text_decode = text.replace(" " , "=");
            byte[] en = Base64.decode(text_decode.substring(2,text_decode.length()),Base64.NO_WRAP);
            val2 = new String(en, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("name3e" , val2+e.toString());
        }
        Log.e("name3" , val2);
        return val2;
    }

    public String decodee(String text){


        String val2 = "";

        try {
            byte[] en = Base64.decode(text,Base64.NO_WRAP);
            val2 = new String(en, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("name2e" , val2+e.toString());
        }
        Log.e("name2" , val2+"mm");
        return val2;
    }

    private String decode_abjad(String text){
        //Log.e("md11" , text);
            String val1 = "";
        try {
            text = text.replace(" " , "=");
            byte[] en = Base64.decode(text,Base64.NO_WRAP);
            val1 = new String(en , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return val1;
    }
}
