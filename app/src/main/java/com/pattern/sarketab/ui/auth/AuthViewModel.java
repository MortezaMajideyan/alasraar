package com.pattern.sarketab.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.data.remote.model.PayResult;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;
import com.pattern.sarketab.network.auth.RegisterApi;
import com.pattern.sarketab.ui.main.MainResource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kavak ;)
 */
public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private MediatorLiveData<MainResource<User>> authUser = new MediatorLiveData<>();
    private MediatorLiveData<MainResource<User>> authCheck = new MediatorLiveData<>();
    private MediatorLiveData<MainResource<PayResult>> payInfo = new MediatorLiveData<>();


    private final RegisterApi registerApi;
    @Inject
    public AuthViewModel(RegisterApi registerApi) {
        this.registerApi = registerApi;
    }

    // send Sms
    public void register(String  phoneNumber,String force){
        authUser.setValue(MainResource.loading((User) null));
        final LiveData<MainResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                registerApi.registerUser(phoneNumber,force)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                User user = new User();
                                user.setStatus(-1);
                                return user;
                            }
                        })
                        .map(new Function<User, MainResource<User>>() {
                            @Override
                            public MainResource<User> apply(User user) throws Exception {
                                if (user.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else {
                                    return MainResource.update(user);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, new Observer<MainResource<User>>() {
            @Override
            public void onChanged(MainResource<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<User>> checkUserRegister(){
        return authUser;
    }


    //check code

    // send Sms
    public void confirmCode(String  phoneNumber,String confirmCode){
        authCheck.setValue(MainResource.loading((User) null));
        final LiveData<MainResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                registerApi.confirmUser(phoneNumber,confirmCode)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                User user = new User();
                                user.setStatus(-1);
                                return user;
                            }
                        })
                        .map(new Function<User, MainResource<User>>() {
                            @Override
                            public MainResource<User> apply(User user) throws Exception {
                                if (user.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else {
                                    return MainResource.update(user);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        authCheck.addSource(source, new Observer<MainResource<User>>() {
            @Override
            public void onChanged(MainResource<User> user) {
                authCheck.setValue(user);
                authCheck.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<User>> checkUserConfirm(){
        return authCheck;
    }


    public void setPayed(String  userId , String payId){
        payInfo.setValue(MainResource.loading((PayResult) null));
        final LiveData<MainResource<PayResult>> source = LiveDataReactiveStreams.fromPublisher(
                registerApi.payDone(userId,payId)
                        .onErrorReturn(new Function<Throwable, PayResult>() {
                            @Override
                            public PayResult apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "payed: "+throwable );
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
}
