package com.pattern.sarketab.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by kavak ;)
 */
public class MainResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public MainResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public static <T> MainResource<T> update (@Nullable T data) {
        return new MainResource<>(AuthStatus.Update, data, null);
    }
    public static <T> MainResource<T> error(@NonNull String msg, @Nullable T data) {
        return new MainResource<>(AuthStatus.ERROR, data, msg);
    }
    public static <T> MainResource<T> loading(@Nullable T data) {
        return new MainResource<>(AuthStatus.LOADING, data, null);
    }
    public static <T> MainResource<T> updated (@NonNull String msg, @Nullable T data) {
        return new MainResource<>(AuthStatus.Updated, data, msg);
    }

    public enum AuthStatus { Update, ERROR, LOADING, Updated}

}
