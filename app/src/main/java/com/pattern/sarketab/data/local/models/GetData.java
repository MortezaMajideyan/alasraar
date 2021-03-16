package com.pattern.sarketab.data.local.models;

import android.content.Context;


import com.pattern.alasraar.R;

import java.util.HashMap;

/**
 * Created by kavak ;)
 */
public  class GetData {

    public static  HashMap<String,Integer> Abjad ;
    public static HashMap<String,String> fall_explain;
    public static  HashMap<String,String> abjad_alpha ;
    public static  HashMap<String,Mounth_Data> monthInfo;
    public static  HashMap<String,String> job ;
    public static  Mounth_Data mounth_data1;

    public static  HashMap<String,Mounth_Data> getMonthInfo(Context context){
        monthInfo = new HashMap<>();
         mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month1_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month1_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month1_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month1_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month1_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month1_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month1_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month1_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month1_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month1_explain));
        monthInfo.put("1",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month2_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month2_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month2_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month2_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month2_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month2_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month2_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month2_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month2_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month2_explain));
        monthInfo.put("2",mounth_data1);


        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month3_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month3_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month3_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month3_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month3_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month3_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month3_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month3_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month3_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month3_explain));
        monthInfo.put("3",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month4_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month4_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month4_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month4_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month4_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month4_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month4_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month4_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month4_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month4_explain));
        monthInfo.put("4",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month5_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month5_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month5_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month5_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month5_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month5_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month5_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month5_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month5_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month5_explain));
        monthInfo.put("5",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month6_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month6_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month6_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month6_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month6_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month6_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month6_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month6_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month6_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month6_explain));
        monthInfo.put("6",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month7_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month7_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month7_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month7_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month7_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month7_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month7_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month7_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month7_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month7_explain));
        monthInfo.put("7",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month8_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month8_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month8_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month8_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month8_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month8_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month8_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month8_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month8_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month8_explain));
        monthInfo.put("8",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month9_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month9_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month9_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month9_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month9_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month9_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month9_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month9_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month9_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month9_explain));
        monthInfo.put("9",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month10_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month10_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month10_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month10_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month10_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month10_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month10_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month10_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month10_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month10_explain));
        monthInfo.put("10",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month11_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month11_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month11_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month11_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month11_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month11_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month11_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month11_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month11_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month11_explain));
        monthInfo.put("11",mounth_data1);

        mounth_data1 = new Mounth_Data();
        mounth_data1.setMan(context.getResources().getString(R.string.month12_man));
        mounth_data1.setWoman(context.getResources().getString(R.string.month12_woman));
        mounth_data1.setSambol(context.getResources().getString(R.string.month12_sambol));
        mounth_data1.setOnsor(context.getResources().getString(R.string.month12_onsor));
        mounth_data1.setSayareh(context.getResources().getString(R.string.month12_sayareh));
        mounth_data1.setAsib_pazir(context.getResources().getString(R.string.month12_asybpazir));
        mounth_data1.setRoz_eghbal(context.getResources().getString(R.string.month12_rozeghbal));
        mounth_data1.setSang_khoshyomn(context.getResources().getString(R.string.month12_sang));
        mounth_data1.setRang(context.getResources().getString(R.string.month12_rang));
        mounth_data1.setDescript_more(context.getResources().getString(R.string.month12_explain));
        monthInfo.put("12",mounth_data1);

        return monthInfo;
    }


    public static  HashMap<String,String> getJobs(Context context){
        job = new HashMap<>();
        job.put("1",context.getResources().getString(R.string.job1));
        job.put("2",context.getResources().getString(R.string.job2));
        job.put("3",context.getResources().getString(R.string.job3));
        job.put("4",context.getResources().getString(R.string.job4));
        job.put("5",context.getResources().getString(R.string.job5));
        job.put("6",context.getResources().getString(R.string.job6));
        job.put("7",context.getResources().getString(R.string.job7));
        job.put("8",context.getResources().getString(R.string.job8));
        job.put("9",context.getResources().getString(R.string.job9));
        job.put("10",context.getResources().getString(R.string.job10));
        job.put("11",context.getResources().getString(R.string.job11));
        job.put("12",context.getResources().getString(R.string.job12));
        job.put("13",context.getResources().getString(R.string.job13));
        job.put("14",context.getResources().getString(R.string.job14));
        job.put("15",context.getResources().getString(R.string.job15));
        job.put("16",context.getResources().getString(R.string.job16));
        job.put("17",context.getResources().getString(R.string.job17));
        job.put("18",context.getResources().getString(R.string.job18));
        job.put("19",context.getResources().getString(R.string.job19));
        job.put("20",context.getResources().getString(R.string.job20));
        job.put("21",context.getResources().getString(R.string.job21));
        job.put("22",context.getResources().getString(R.string.job22));
        job.put("23",context.getResources().getString(R.string.job23));
        job.put("24",context.getResources().getString(R.string.job24));
        job.put("25",context.getResources().getString(R.string.job25));
        job.put("26",context.getResources().getString(R.string.job26));
        job.put("27",context.getResources().getString(R.string.job27));
        job.put("28",context.getResources().getString(R.string.job28));
        job.put("29",context.getResources().getString(R.string.job29));
        job.put("30",context.getResources().getString(R.string.job30));

        return job;
    }
    public static HashMap<String,Integer> getAbjadList(){
        Abjad = new HashMap();
        Abjad.put("ا",1);
        Abjad.put("آ",1);
        Abjad.put("ب",2);
        Abjad.put("ج",3);
        Abjad.put("د",4);
        Abjad.put("ه",5);
        Abjad.put("و",6);
        Abjad.put("ز",7);
        Abjad.put("ح",8);
        Abjad.put("ط",9);
        Abjad.put("ی",10);
        Abjad.put("ک",20);
        Abjad.put("ل",30);
        Abjad.put("م",40);
        Abjad.put("ن",50);
        Abjad.put("س",60);
        Abjad.put("ع",70);
        Abjad.put("ف",80);
        Abjad.put("ص",90);
        Abjad.put("ق",100);
        Abjad.put("ر",200);
        Abjad.put("ش",300);
        Abjad.put("ت",400);
        Abjad.put("ث",500);
        Abjad.put("خ",600);
        Abjad.put("ذ",700);
        Abjad.put("ض",800);
        Abjad.put("ظ",900);
        Abjad.put("غ",1000);
        Abjad.put("گ",20);
        Abjad.put("ژ",7);
        Abjad.put("پ",2);
        Abjad.put("چ",3);
        Abjad.put("a",0);
        Abjad.put("b",0);
        Abjad.put("c",0);

        return Abjad;
    }

    public static  HashMap<String, String> getAbjad_alpha() {
        abjad_alpha = new HashMap<>();
        abjad_alpha.put("1","ا");
        abjad_alpha.put("2","ب");
        abjad_alpha.put("3","ج");
        abjad_alpha.put("4","د");
        return abjad_alpha;
    }

    public static  HashMap<String, String> getFall_explain(Context context) {
        fall_explain = new HashMap<>();
        fall_explain.put("ا ا ج",context.getResources().getString(R.string.aaj));
        fall_explain.put("ا ج ا",context.getResources().getString(R.string.aja));
        fall_explain.put("ا ب ب",context.getResources().getString(R.string.abb));
        fall_explain.put("ا ج د",context.getResources().getString(R.string.ajd));
        fall_explain.put("ا ج ج",context.getResources().getString(R.string.ajj));
        fall_explain.put("ب ب ا",context.getResources().getString(R.string.bba));
        fall_explain.put("ب ا ب",context.getResources().getString(R.string.bab));
        fall_explain.put("ب ا د",context.getResources().getString(R.string.bad));
        fall_explain.put("ب ا ج",context.getResources().getString(R.string.baj));
        fall_explain.put("ب ج ب",context.getResources().getString(R.string.bjb));
        fall_explain.put("ج د ب",context.getResources().getString(R.string.jdb));
        fall_explain.put("ج ج ب",context.getResources().getString(R.string.jjj));
        fall_explain.put("ج اج",context.getResources().getString(R.string.jaj));
        fall_explain.put("ج ب د",context.getResources().getString(R.string.jbd));
        fall_explain.put("ج دج",context.getResources().getString(R.string.jdj));
        fall_explain.put("د ا ا",context.getResources().getString(R.string.daa));
        fall_explain.put("د د ا",context.getResources().getString(R.string.dda));
        fall_explain.put("د ب ج",context.getResources().getString(R.string.dbj));
        fall_explain.put("د ب ب",context.getResources().getString(R.string.dbb));
        fall_explain.put("د ج د",context.getResources().getString(R.string.djd));
        fall_explain.put("د ا د",context.getResources().getString(R.string.dad));
        fall_explain.put("ا ا ب",context.getResources().getString(R.string.aab));
        fall_explain.put("ا ب ا",context.getResources().getString(R.string.aba));
        fall_explain.put("ا ب ج",context.getResources().getString(R.string.abj));
        fall_explain.put("ا د ب",context.getResources().getString(R.string.adb));
        fall_explain.put("ا ج ب",context.getResources().getString(R.string.ajb));
        fall_explain.put("ب ب ب",context.getResources().getString(R.string.bbb));
        fall_explain.put("ب ب د",context.getResources().getString(R.string.bbd));
        fall_explain.put("ب د ا",context.getResources().getString(R.string.bda));
        fall_explain.put("ب د ب",context.getResources().getString(R.string.bdb));
        fall_explain.put("ب ج د",context.getResources().getString(R.string.bjd));
        fall_explain.put("ج ج ج",context.getResources().getString(R.string.jjj));
        fall_explain.put("ج ج ا",context.getResources().getString(R.string.jja));
        fall_explain.put("ج د ا",context.getResources().getString(R.string.jda));
        fall_explain.put("ج ا ب",context.getResources().getString(R.string.jab));
        fall_explain.put("ج ب ا",context.getResources().getString(R.string.jba));
        fall_explain.put("ج ا ا",context.getResources().getString(R.string.jaa));
        fall_explain.put("د ب د",context.getResources().getString(R.string.dbd));
        fall_explain.put("د د د",context.getResources().getString(R.string.ddd));
        fall_explain.put("د د ب",context.getResources().getString(R.string.ddb));
        fall_explain.put("د ج ب",context.getResources().getString(R.string.djb));
        fall_explain.put("د ب ا",context.getResources().getString(R.string.dba));
        fall_explain.put("ا ا ا",context.getResources().getString(R.string.aaa));
        fall_explain.put("ا ا د",context.getResources().getString(R.string.aad));
        fall_explain.put("ا د ا",context.getResources().getString(R.string.ada));
        fall_explain.put("ا ب د",context.getResources().getString(R.string.abd));
        fall_explain.put("ا د ج",context.getResources().getString(R.string.adj));
        fall_explain.put("ا د د",context.getResources().getString(R.string.add));
        fall_explain.put("ب ب ج",context.getResources().getString(R.string.bbj));
        fall_explain.put("ب ا ا",context.getResources().getString(R.string.baa));
        fall_explain.put("ب د د",context.getResources().getString(R.string.bdd));
        fall_explain.put("ب ج ا",context.getResources().getString(R.string.bja));
        fall_explain.put("ب ج ج",context.getResources().getString(R.string.bjj));
        fall_explain.put("ج ب ب",context.getResources().getString(R.string.jbb));
        fall_explain.put("ج ج د",context.getResources().getString(R.string.jjd));
        fall_explain.put("ج ب ج",context.getResources().getString(R.string.jbj));
        fall_explain.put("ج د د",context.getResources().getString(R.string.jdd));
        fall_explain.put("ج ا د",context.getResources().getString(R.string.jad));
        fall_explain.put("د ا ج",context.getResources().getString(R.string.daj));
        fall_explain.put("د د ج",context.getResources().getString(R.string.ddj));
        fall_explain.put("د ج ج",context.getResources().getString(R.string.djj));
        fall_explain.put("د ج ا",context.getResources().getString(R.string.dja));
        fall_explain.put("د ا ب",context.getResources().getString(R.string.dab));
        return fall_explain;
    }


    public static HashMap<String, String> getJob(Context context) {
        job.put("1",context.getResources().getString(R.string.job1));
        job.put("2",context.getResources().getString(R.string.job2));
        job.put("3",context.getResources().getString(R.string.job3));
        job.put("4",context.getResources().getString(R.string.job4));
        job.put("5",context.getResources().getString(R.string.job5));
        job.put("6",context.getResources().getString(R.string.job6));
        job.put("7",context.getResources().getString(R.string.job7));
        job.put("8",context.getResources().getString(R.string.job8));
        job.put("9",context.getResources().getString(R.string.job9));
        job.put("10",context.getResources().getString(R.string.job10));
        job.put("11",context.getResources().getString(R.string.job11));
        job.put("12",context.getResources().getString(R.string.job12));
        job.put("13",context.getResources().getString(R.string.job13));
        job.put("14",context.getResources().getString(R.string.job14));
        job.put("15",context.getResources().getString(R.string.job15));
        job.put("16",context.getResources().getString(R.string.job16));
        job.put("17",context.getResources().getString(R.string.job17));
        job.put("18",context.getResources().getString(R.string.job18));
        job.put("19",context.getResources().getString(R.string.job19));
        job.put("20",context.getResources().getString(R.string.job20));
        job.put("21",context.getResources().getString(R.string.job21));
        job.put("22",context.getResources().getString(R.string.job22));
        job.put("23",context.getResources().getString(R.string.job23));
        job.put("24",context.getResources().getString(R.string.job24));
        job.put("25",context.getResources().getString(R.string.job25));
        job.put("26",context.getResources().getString(R.string.job26));
        job.put("27",context.getResources().getString(R.string.job27));
        job.put("28",context.getResources().getString(R.string.job28));
        job.put("29",context.getResources().getString(R.string.job29));
        job.put("30",context.getResources().getString(R.string.job30));

        return job;
    }
}
