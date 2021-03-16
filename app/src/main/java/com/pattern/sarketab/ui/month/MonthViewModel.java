package com.pattern.sarketab.ui.month;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.data.remote.model.Month;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.network.month.GetMonthInfo;
import com.pattern.sarketab.ui.main.MainResource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kavak ;)
 */
public class MonthViewModel extends ViewModel {

    private static final String TAG = "MonthViewModel";
    private MediatorLiveData<MonthResource<Month>> months = new MediatorLiveData<>();
    private final GetMonthInfo getMonthInfo;
    @Inject
    public MonthViewModel(GetMonthInfo getMonthInfo) {
        this.getMonthInfo = getMonthInfo;
    }

    public void getMonths(String gate,String userId){
        months.setValue(MonthResource.loading((Month) null));
        final LiveData<MonthResource<Month>> source = LiveDataReactiveStreams.fromPublisher(
                getMonthInfo.getMonths(gate,userId)
                        .onErrorReturn(new Function<Throwable, Month>() {
                            @Override
                            public Month apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                Month month = new Month();
                               month.setStatus(-1);
                                return month;
                            }
                        })
                        .map(new Function<Month, MonthResource<Month>>() {
                            @Override
                            public MonthResource<Month> apply(Month month) throws Exception {
                                if (month.getStatus() ==-1){
                                    return MonthResource.error("Could not check for update", null);
                                }else {
                                        return MonthResource.receive(month);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        months.addSource(source, new Observer<MonthResource<Month>>() {
            @Override
            public void onChanged(MonthResource<Month> month) {
                months.setValue(month);
                months.removeSource(source);
            }
        });
    }
    public LiveData<MonthResource<Month>> observerMonths(){
        return months;
    }
}
