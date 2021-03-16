package com.pattern.sarketab.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;
import com.pattern.sarketab.network.auth.CheckUpdateApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kavak ;)
 */
public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    private MediatorLiveData<MainResource<Update>> authUser = new MediatorLiveData<>();
    private MediatorLiveData<MainResource<User>> payUser = new MediatorLiveData<>();
   private final CheckUpdateApi checkUpdateApi;
    @Inject
     MainViewModel(CheckUpdateApi checkUpdateApi){
        this.checkUpdateApi = checkUpdateApi;
    }

    public void checkUpdate(String  version , String os){
        authUser.setValue(MainResource.loading((Update) null));
        final LiveData<MainResource<Update>> source = LiveDataReactiveStreams.fromPublisher(
                checkUpdateApi.getUser(version,os)
                        .onErrorReturn(new Function<Throwable, Update>() {
                            @Override
                            public Update apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                              Update update = new Update();
                              update.setStatus(-1);
                                return update;
                            }
                        })
                        .map(new Function<Update, MainResource<Update>>() {
                            @Override
                            public MainResource<Update> apply(Update update) throws Exception {
                                if (update.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else if (update.getStatus()==200) {
                                    if (update.getResults() != null) {
                                        return MainResource.update(update);
                                    } else {
                                        return MainResource.updated("App is Update", null);
                                    }
                                }else {
                                    return MainResource.error("Could not check for update 1212", null);
                                }
                            }
                        })
                .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, new Observer<MainResource<Update>>() {
            @Override
            public void onChanged(MainResource<Update> update) {
                authUser.setValue(update);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<Update>> observerUpdate(){
        return authUser;
    }



    public void checkUserPay(String  userId ){
        payUser.setValue(MainResource.loading((User) null));
        final LiveData<MainResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                checkUpdateApi.checkPayment(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                User update = new User();
                                update.setStatus(-1);
                                return update;
                            }
                        })
                        .map(new Function<User, MainResource<User>>() {
                            @Override
                            public MainResource<User> apply(User user) throws Exception {
                                if (user.getStatus() ==-1){
                                    return MainResource.error("Could not check for update", null);
                                }else if (user.getStatus()==200) {
                                        return MainResource.update(user);
                                }else {
                                    return MainResource.error("Could not check for update 1212", null);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        payUser.addSource(source, new Observer<MainResource<User>>() {
            @Override
            public void onChanged(MainResource<User> user) {
                payUser.setValue(user);
                payUser.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<User>> observerPay(){
        return payUser;
    }



}
