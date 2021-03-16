package com.pattern.sarketab.ui.Sarketab;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;

public class helper_sarketab extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "alasraar.db";
    public static final String TABLE_NAME = "sarketab";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_WOMEN = "txt_women";
    public static final String COLUMN_MEN = "txt_men";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_STATE = "state";
    private HashMap hp;

    public helper_sarketab(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+TABLE_NAME +
                        "("+COLUMN_ID+" INTEGER primary key, "+COLUMN_TITLE+" TEXT,"+COLUMN_WOMEN+" TEXT,"+COLUMN_MEN+" TEXT," +
                        COLUMN_GENDER+" TEXT,"+COLUMN_CODE+" TEXT,"+COLUMN_STATE+" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertSarketb (String title, String women, String men, String gender,String code , String state) {
        Cursor res = getData(Integer.parseInt(code));
        if (res.getCount() > 0){
            updateSarketb(title, women, men, gender,code ,state);
        }else{
            SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE , encode(title));
        contentValues.put(COLUMN_WOMEN , encode(women));
        contentValues.put(COLUMN_MEN , encode(men));
        contentValues.put(COLUMN_GENDER , encode(gender));
        contentValues.put(COLUMN_CODE , code);
        contentValues.put(COLUMN_STATE , state);
        db.insert(TABLE_NAME, null, contentValues);
        }
        return true;
    }

    public Cursor getData(int code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_CODE+"='"+code+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateSarketb (String title, String women, String men, String gender,String code , String state) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE , encode(title));
        contentValues.put(COLUMN_WOMEN , encode(women));
        contentValues.put(COLUMN_MEN , encode(men));
        contentValues.put(COLUMN_GENDER , encode(gender));
        contentValues.put(COLUMN_CODE , code);
        contentValues.put(COLUMN_STATE , state);
        db.update(TABLE_NAME, contentValues, COLUMN_CODE+" = ? ", new String[] { code } );
        return true;
    }

    public Integer deleteSarketab (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }

    private String encode(String text){
        //Log.e("md11" , text);
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        String decode = base64.replace("="," ");
        //Log.e("md1" , base64);
       // Log.e("md3" , decode);
        String salt = getSaltString();
        decode=salt+decode;
       //Log.e("md5" , decode+":");
        return decode;
    }

    public String decode(String text){
        String text_decode = text.replace(" " , "=");
        byte[] en = Base64.decode(text_decode.substring(2,text_decode.length()),Base64.NO_WRAP);
        String val2 = "";
        try {
            val2 = new String(en, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return val2;
    }


    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrst";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() <= 1) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}