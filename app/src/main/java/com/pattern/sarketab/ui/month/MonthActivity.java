package com.pattern.sarketab.ui.month;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern.alasraar.R;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.data.remote.model.Month;

import com.pattern.sarketab.ui.adaptor.RVAdapterMonth;
import com.pattern.sarketab.ui.payment.ActivityPayMonth;
import com.pattern.sarketab.viewmodels.ViewModelProviderFactory;
import com.thebrownarrow.customfont.CustomFontTextView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by kavak ;)
 */
public class MonthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MonthActivity";
    @Inject
    LocalStorage localStorage;
    @Inject
    ViewModelProviderFactory providerFactory;
    private MonthViewModel viewModel;
    RecyclerView recyclerView;
    RVAdapterMonth rvAdapterMonth;
    LinearLayout problem;
    CustomFontTextView date;
    ProgressBar progressBar;
    RelativeLayout tryBtn;
    ImageView back;
    CardView card;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.eghamat_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        card = findViewById(R.id.info);
        viewModel = ViewModelProviders.of(this, providerFactory).get(MonthViewModel.class);
        recyclerView = findViewById(R.id.rv);
        problem = findViewById(R.id.problem);
        tryBtn = findViewById(R.id.try_btn);
        date = findViewById(R.id.lastUpdate);
        tryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (problem.getVisibility()==View.VISIBLE){
                    problem.setVisibility(View.GONE);
                }

                viewModel.getMonths("direct",localStorage.readMessage("userId"));

            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        subscribeObservers();
        viewModel.getMonths("direct",localStorage.readMessage("userId"));
        Log.d(TAG, "onCreate: "+localStorage.readMessage("userId"));
    }
    private void subscribeObservers(){
        viewModel.observerMonths().observe(this, new Observer<MonthResource<Month>>() {
            @Override
            public void onChanged(MonthResource<Month> userStates) {
                if (userStates!=null){
                    switch (userStates.status){
                        case ERROR:{
                            progressBar.setVisibility(View.GONE);
                            problem.setVisibility(View.VISIBLE);
                            card.setVisibility(View.GONE);
                            Toast.makeText(MonthActivity.this, "Sorry a problem happen ...", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case Receive:{
                            if (userStates.data.getStatus()==200) {
                                progressBar.setVisibility(View.GONE);
                                rvAdapterMonth = new RVAdapterMonth(userStates.data.getMonthesInfos().getMonthesInfos());
                                date.setText(userStates.data.getMonthesInfos().getUpdated_at());
                                card.setVisibility(View.VISIBLE);
                                recyclerView.setAdapter(rvAdapterMonth);
                            }else if (userStates.data.getStatus()==422){
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(MonthActivity.this, ActivityPayMonth.class);
                                startActivity(intent);
                                Toast.makeText(MonthActivity.this, "اشتراک ماهانه شما به اتمام رسیده است", Toast.LENGTH_SHORT).show();
                            }else {
                                progressBar.setVisibility(View.GONE);
                                problem.setVisibility(View.VISIBLE);
                                card.setVisibility(View.GONE);
                                Toast.makeText(MonthActivity.this, "Sorry a problem happen ...", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                        case LOADING:{
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        }

                    }
                }

            }
        });
    }

}
