package com.pattern.sarketab.data.preferences;

import android.content.Context;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Created by kavak ;)
 */
public class SharedPrefStorage implements LocalStorage {
    private Context context;
    public SharedPrefStorage(Context context) {
        this.context = context;
    }
    @Override
    public void writeMessage(String message ,String key) {
        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
                .edit().putString(key, message).apply();
    }
    @Override
    public String readMessage(final String key) {
      return context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
                .getString(key, "");
    }
}