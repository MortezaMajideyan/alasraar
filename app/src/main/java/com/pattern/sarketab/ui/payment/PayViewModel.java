package com.pattern.sarketab.ui.payment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.data.remote.model.PayPackage;
import com.pattern.sarketab.data.remote.model.PayResult;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.network.pay.CheckPay;
import com.pattern.sarketab.ui.main.MainResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kavak ;)
 */
public class PayViewModel extends ViewModel {
   private final CheckPay checkPay;
    private static final String TAG = "PayViewModel";
    private MediatorLiveData<MainResource<PayResult>> payInfo = new MediatorLiveData<>();
    private MediatorLiveData<MainResource<PayPackage>> payPackage = new MediatorLiveData<>();

    @Inject
    public PayViewModel(CheckPay checkPay) {
        this.checkPay = checkPay;
    }


    public void payed(String  userId , String payId){
        payInfo.postValue(MainResource.loading((PayResult) null));
        final LiveData<MainResource<PayResult>> source = LiveDataReactiveStreams.fromPublisher(
                checkPay.checkPay(userId,payId)
                        .onErrorReturn(new Function<Throwable, PayResult>() {
                            @Override
                            public PayResult apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                PayResult update = new PayResult();
                                update.setStatus(-1);
                                return update;
                            }
                        })
                        .map(new Function<PayResult, MainResource<PayResult>>() {
                            @Override
                            public MainResource<PayResult> apply(PayResult update) throws Exception {
                                if (update.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else if (update.getStatus()==200) {
                                        return MainResource.update(update);
                                }else {
                                    return MainResource.error("Could not check for update 1212", null);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())

        );
        payInfo.addSource(source, new Observer<MainResource<PayResult>>() {
            @Override
            public void onChanged(MainResource<PayResult> update) {
                payInfo.setValue(update);
                payInfo.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<PayResult>> observerPay(){
        return payInfo;
    }

    public void getPackageInfo(String payTitle){
        payPackage.postValue(MainResource.loading((PayPackage) null));
        final LiveData<MainResource<PayPackage>> source = LiveDataReactiveStreams.fromPublisher(
                checkPay.getPayInfo(payTitle)
                        .onErrorReturn(new Function<Throwable, PayPackage>() {
                            @Override
                            public PayPackage apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                PayPackage update = new PayPackage();
                                update.setStatus(-1);
                                return update;
                            }
                        })
                        .map(new Function<PayPackage, MainResource<PayPackage>>() {
                            @Override
                            public MainResource<PayPackage> apply(PayPackage payPackage) throws Exception {
                                if (payPackage.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else {
                                    return MainResource.update(payPackage);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())

        );
        payPackage.addSource(source, new Observer<MainResource<PayPackage>>() {
            @Override
            public void onChanged(MainResource<PayPackage> Package) {
                payPackage.setValue(Package);
                payPackage.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<PayPackage>> observerPackage(){
        return payPackage;
    }
}
