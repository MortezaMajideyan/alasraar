package com.pattern.sarketab.data.preferences;

import io.reactivex.Observable;

/**
 * Created by kavak ;)
 */
public interface LocalStorage {
    void writeMessage(String message,String key);
    String readMessage(String key);
}
