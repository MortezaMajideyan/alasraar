package com.pattern.sarketab.ui.Sarketab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.pattern.sarketab.ui.ActivityChange_name;
import com.pattern.sarketab.ui.ActivityResult;
import com.pattern.sarketab.ui.DatePicker.Listener;
import com.pattern.sarketab.ui.DatePicker.PersianCalendar;
import com.pattern.sarketab.ui.DatePicker.PersianDatePickerDialog;
import com.pattern.sarketab.ui.Modle.M_sarketab;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.ArrayList;
import java.util.Date;
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
public class ActivitySarketab extends DaggerAppCompatActivity {
    @Inject
    LocalStorage localStorage;
    private static final String TAG = "ActivitySarketab";
    RelativeLayout datePicker;
    PersianDatePickerDialog picker;
    ImageView image;
    int gender = 0;
    int mother_name_sum = 0;
    ImageView back;
    RelativeLayout male_layout;
    RelativeLayout female_layout;
    ImageView maleImage;
    ImageView femaleImage;
    CustomFontEditText dateText;
    CustomFontEditText nameEditText;
    CustomFontTextView nameNumberTextView;
    CustomFontEditText motherNameEditText;
    CustomFontTextView motherNameNumberTextView;
    RelativeLayout resultClick;
    Spinner spinner_month , spinner_day;
    private  HashMap<String,Integer> Abjad ;
    int m_num= 0;
    int name_sum = 0;
    int month = 0;

    String title , txt_women , txt_men , gender_d , cat , code  , state;

    helper_sarketab mydb_sarketab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sarketab);
        mydb_sarketab = new helper_sarketab(this);

        if (mydb_sarketab.numberOfRows() > 0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(isNetworkConnected()){
                        get_sarketab();
                    }
                }
            }).start();


        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(isNetworkConnected()){
                        get_sarketab();
                    }else{
                        insert_sarketab();
                    }
                }
            }).start();

        }





        initView();


        String[] day = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        String[] months = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> adapter_m = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , months);
        ArrayAdapter<String> adapter_d = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , day);
        spinner_month.setAdapter(adapter_m);
        spinner_day.setAdapter(adapter_d);

        spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ActivitySarketab.this, parent.getItemIdAtPosition(position)+":"+spinner_month.getSelectedItemId(), Toast.LENGTH_SHORT).show();
                month = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(ActivitySarketab.this, parent.getItemIdAtPosition(position)+":"+spinner_month.getSelectedItemId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resultClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_sum!=0 && mother_name_sum!=0 && month!=0) {
                    int result = (name_sum + mother_name_sum) % 12;
                    if (result == 0){
                        result = 12;
                    }
                    Intent intent = new Intent(ActivitySarketab.this, ActivityResult.class);
                    intent.putExtra("num", result);
                    intent.putExtra("activity", 4);
                    intent.putExtra("gender", gender);
                    intent.putExtra("month", month);
                    startActivity(intent);
                }else {
                    Toast.makeText(ActivitySarketab.this, getResources().getString(R.string.fillFields), Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // datePicker of app


        final Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();



        //EditText and getting Abjad from words and show

        nameEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameNumberTextView.setText("0");

                if (nameEditText.length() == 15){
                    check_char(nameEditText.getText().toString() , nameEditText);
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
                            nameNumberTextView.setText(Integer.toString(name_sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });



        motherNameEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                motherNameNumberTextView.setText("0");

                if (motherNameEditText.length() == 15){
                    check_char(motherNameEditText.getText().toString() , motherNameEditText);
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
                            motherNameNumberTextView.setText(Integer.toString(mother_name_sum));
                        } catch (Exception e) {
                        }
                    }

                }
            }
        });


        // gender choosen view
        male_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGender(gender,0);
            }
        });

        female_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGender(gender,1);
            }
        });

    }
    // this method change the images and make user change gender
    public void changeGender(int gender,int newgender){
        if (gender==0 && newgender==1){
            maleImage.setVisibility(View.INVISIBLE);
            femaleImage.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.ic_female);
            this.gender = newgender;
        }else if (gender==1 && newgender==0){
            maleImage.setVisibility(View.VISIBLE);
            femaleImage.setVisibility(View.INVISIBLE);
            image.setImageResource(R.drawable.ic_male);
            this.gender = newgender;
        }
    }


    public void initView(){
        Abjad = GetData.getAbjadList();
        resultClick = findViewById(R.id.sarketab_natijeh);
        spinner_month = findViewById(R.id.spinner_month);
        spinner_day = findViewById(R.id.spinner_day);
        back = findViewById(R.id.sarketab_back);
        motherNameEditText = findViewById(R.id.sarketab_mother_name);
        motherNameNumberTextView = findViewById(R.id.sarketab_mother_name_number);
        nameEditText = findViewById(R.id.sarketab_name);
        nameNumberTextView = findViewById(R.id.sarketab_name_number);
        male_layout = findViewById(R.id.male_layout);
        female_layout = findViewById(R.id.female_layout);
        maleImage = findViewById(R.id.male_image);
        femaleImage = findViewById(R.id.female_image);
        image = findViewById(R.id.image);
    }
private void insert_sarketab(){
        String title = "یک متن وب";
        String women = "\n" +
                "Creating a random string with A-Z and 0-9 in Java [duplicate]\n" +
                "Ask Question\n" +
                "Asked 6 years, 5 months ago\n" +
                "Active 2 years, 9 months ago\n" +
                "Viewed 184k times\n" +
                "63\n" +
                "This question already has answers here:\n" +
                "How to generate a random alpha-numeric string? (42 answers)\n" +
                "Closed 6 years ago.\n" +
                "\n" +
                "As the title suggest I need to create a random, 17 characters long, ID. Something like \"AJB53JHS232ERO0H1\". The order of letters and numbers is also random. I thought of creating an array with letters A-Z and a 'check' variable that randoms to 1-2. And in a loop;\n" +
                "\n" +
                "Randomize 'check' to 1-2.\n" +
                "If (check == 1) then the character is a letter.\n" +
                "Pick a random index from the letters array.\n" +
                "else\n" +
                "Pick a random number.\n" +
                "\n" +
                "But I feel like there is an easier way of doing this. Is there?\n" +
                "java random\n" +
                "shareimprove this question\n" +
                "edited Dec 12 '13 at 6:34\n" +
                "Ruchira Gayan Ranaweera\n" +
                "30.4k1616 gold badges6363 silver badges103103 bronze badges\n" +
                "asked Dec 12 '13 at 6:33\n" +
                "cbt\n" +
                "1,13111 gold badge99 silver badges1616 bronze badges\n" +
                "\n" +
                "    2\n" +
                "    stackoverflow.com/questions/41107/… – aquaraga Dec 12 '13 at 6:36\n" +
                "    you can put your letters and digits into an array and then randomly choose elements from it until you reach your desired size. – wxyz Dec 12 '13 at 6:37 \n" +
                "\n" +
                "add a comment\n" +
                "4 Answers\n" +
                "Active\n" +
                "Oldest\n" +
                "Votes\n" +
                "102\n" +
                "\n" +
                "Here you can use my method for generating Random String\n" +
                "\n" +
                "protected String getSaltString() {\n" +
                "        String SALTCHARS = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\";\n" +
                "        StringBuilder salt = new StringBuilder();\n" +
                "        Random rnd = new Random();\n" +
                "        while (salt.length() < 18) { // length of the random string.\n" +
                "            int index = (int) (rnd.nextFloat() * SALTCHARS.length());\n" +
                "            salt.append(SALTCHARS.charAt(index));\n" +
                "        }\n" +
                "        String saltStr = salt.toString();\n" +
                "        return saltStr;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "The above method from my bag using to generate a salt string for login purpose.\n" +
                "shareimprove this answer\n" +
                "edited Apr 4 '17 at 2:13\n" +
                "answered Dec 12 '13 at 6:36\n" +
                "Suresh Atta\n" +
                "112k3232 gold badges160160 silver badges259259 bronze badges\n" +
                "\n" +
                "    1\n" +
                "    May I suggest to replace StringBuffer by a StringBuilder, no need to have a thread-safe impl. here – user180100 Dec 12 '13 at 6:42\n" +
                "    7\n" +
                "    Or just create a char[] given that you know exactly how long it will be. No need to append anything. I'd also use Random.nextInt rather than calling nextFloat and multiplying it by the length. – Jon Skeet Dec 12 '13 at 6:44\n" +
                "    1\n" +
                "    Note that the average amount of numbers won't be the same as the amount of letters. – Martijn Courteaux Dec 12 '13 at 6:44\n" +
                "    @RC. Yes. I'm using it in a servlet environment. So need it. Otherwise a StringBuilder – Suresh Atta Dec 12 '13 at 6:47\n" +
                "    One thing to keep in mind if you use this is that, the Random class is sudo random and not a true random. Tried to use this to print 400 Strings to a file and found that it wasn't as random as I wanted. :( – SharpIncTechAndProgramming Dec 26 '18 at 1:46\n" +
                "\n" +
                "add a comment\n" +
                "81\n" +
                "\n" +
                "RandomStringUtils from Apache commons-lang might help:\n" +
                "\n" +
                "RandomStringUtils.randomAlphanumeric(17).toUpperCase()";
        String men = "مدیرکل کمیته امداد استان تهران ارزش ریالی غذاهای توزیع شده";
        String gender = "1";
        String code= "9";
        String state= "3";
        mydb_sarketab.insertSarketb(title , women , men , gender , code , state);
}


public void get_sarketab(){
    Method_Conection service = App.getApplication().create(Method_Conection.class);
    Call<List<M_sarketab>> call = service.sarketab("sarketab");
    call.enqueue(new Callback<List<M_sarketab>>() {
        @Override
        public void onResponse(Call<List<M_sarketab>> call, Response<List<M_sarketab>> response) {
            try{

                for (int i =0; i < response.body().size() ; i++){
                    title = response.body().get(i).title;
                    txt_women = response.body().get(i).txt_women;
                    txt_men = response.body().get(i).txt_men;
                    gender_d = response.body().get(i).gender;
                    code = response.body().get(i).code;
                    state = response.body().get(i).state;


                    mydb_sarketab.insertSarketb(title , txt_women , txt_men , gender_d , code , state);
                }

            }catch (NullPointerException e){

            }
        }

        @Override
        public void onFailure(Call<List<M_sarketab>> call, Throwable t) {
            //Toast.makeText(ActivitySarketab.this, t.toString(), Toast.LENGTH_SHORT).show();
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