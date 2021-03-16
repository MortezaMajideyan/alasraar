package com.pattern.sarketab.ui.Sarketab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class helper_sarketab_day extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sarketab_day.db";
    public static final String TABLE_NAME = "sar_day";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "txt";
    public static final String COLUMN_DAY = "num_day";

    private HashMap hp;

    public helper_sarketab_day(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+TABLE_NAME +
                        "("+COLUMN_ID+" INTEGER primary key, "+COLUMN_TITLE+" TEXT,"+COLUMN_DAY+" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertSarketb_day (String txt, String num_day) {
        Cursor res = getData(Integer.parseInt(num_day));
        if (res.getCount() > 0){
            updateSarketb_day(txt,  num_day);
        }else{
            SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE , encode(txt));
        contentValues.put(COLUMN_DAY , num_day);
        db.insert(TABLE_NAME, null, contentValues);
        }
        return true;
    }

    public Cursor getData(int day) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_DAY+"='"+day+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateSarketb_day (String txt, String num_day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE , encode(txt));
        contentValues.put(COLUMN_DAY , num_day);
        db.update(TABLE_NAME, contentValues, COLUMN_DAY+" = ? ", new String[] { num_day } );
        return true;
    }

    public Integer deleteSarketab_day (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAlldarketab_day() {
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