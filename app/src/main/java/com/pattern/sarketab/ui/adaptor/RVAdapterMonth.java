package com.pattern.sarketab.ui.adaptor;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.pattern.alasraar.R;
import com.pattern.sarketab.data.remote.model.MonthesInfo;
import com.pattern.sarketab.ui.ActivityResult;

import java.util.List;


/**
 * Created by Ahmad_sh on 9/7/2016.
 */
public class RVAdapterMonth extends RecyclerView.Adapter<RVAdapterMonth.QoranViewHolder> {
     class QoranViewHolder extends RecyclerView.ViewHolder {
         String content;
         TextView title;
         RelativeLayout monthClick;
        QoranViewHolder(final View itemView) {
            super(itemView);
        title = itemView.findViewById(R.id.month_title);
        monthClick = itemView.findViewById(R.id.month);
        monthClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), ActivityResult.class);
                intent.putExtra("activity",6);
                intent.putExtra("month",title.getText().toString());
                intent.putExtra("content",content);
               itemView.getContext().startActivity(intent);
            }
        });
        }
    }

    List<MonthesInfo> monthesInfos;

    public RVAdapterMonth(List<MonthesInfo> qoren){
        this.monthesInfos = qoren;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
    @Override
    public QoranViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_month, viewGroup, false);
       QoranViewHolder pvh = new QoranViewHolder(v);

        return pvh;
    }
    @Override
    public void onBindViewHolder(QoranViewHolder personViewHolder, int i) {
//    personViewHolder.date.setText(qoranList.get(i).getTime());
        personViewHolder.title.setText(monthesInfos.get(i).getTitle());
        personViewHolder.content = monthesInfos.get(i).getDescription();

    }
    @Override
    public int getItemCount() {
        return  monthesInfos.size();
    }
}
