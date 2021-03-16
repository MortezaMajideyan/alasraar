package com.pattern.sarketab.ui.month;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/**
 * Created by kavak ;)
 */
public class MonthResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public MonthResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public static <T> MonthResource<T> receive (@Nullable T data) {
        return new MonthResource<>(AuthStatus.Receive, data, null);
    }
    public static <T> MonthResource<T> error(@NonNull String msg, @Nullable T data) {
        return new MonthResource<>(AuthStatus.ERROR, data, msg);
    }
    public static <T> MonthResource<T> loading(@Nullable T data) {
        return new MonthResource<>(AuthStatus.LOADING, data, null);
    }
    public enum AuthStatus { Receive, ERROR, LOADING}

}
