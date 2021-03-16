package com.pattern.sarketab.ahmadpay.network;

/**
 * Created by kavak ;)
 */
public interface PayListener {
    public void PayDone(String json, String paygiri, String payId);
    public void PayError(String error);
}
