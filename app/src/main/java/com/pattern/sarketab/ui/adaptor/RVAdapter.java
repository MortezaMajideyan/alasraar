package com.pattern.sarketab.ui.adaptor;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.pattern.alasraar.R;
import com.pattern.sarketab.data.local.models.Details;

import java.util.List;


/**
 * Created by Ahmad_sh on 9/7/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.QoranViewHolder> {
     class QoranViewHolder extends RecyclerView.ViewHolder {
         ImageView icon ;
         TextView explain;
         TextView content;
        QoranViewHolder(final View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            content = itemView.findViewById(R.id.content);
            explain = itemView.findViewById(R.id.explain);
        }
    }

    List<Details> qoranList;

    public RVAdapter(List<Details> qoren){
        this.qoranList = qoren;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
    @Override
    public QoranViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
       QoranViewHolder pvh = new QoranViewHolder(v);

        return pvh;
    }
    @Override
    public void onBindViewHolder(QoranViewHolder personViewHolder, int i) {
//    personViewHolder.date.setText(qoranList.get(i).getTime());

        switch (qoranList.get(i).getId()){
            case 1:
                personViewHolder.icon.setImageResource(R.drawable.ic_diamond);
                personViewHolder.content.setText("عنصر: ");
                break;
            case 2:
                personViewHolder.icon.setImageResource(R.drawable.ic_flame);
                personViewHolder.content.setText("سمبل: ");
                break;

            case 3:
                personViewHolder.icon.setImageResource(R.drawable.ic_infographic);
                personViewHolder.content.setText("روز اقبال:");
                break;

            case 4:
                personViewHolder.icon.setImageResource(R.drawable.ic_dice);
                personViewHolder.content.setText("اعداد شانس: ");
                break;
            case 5:
                personViewHolder.icon.setImageResource(R.drawable.ic_color);
                personViewHolder.content.setText("رنگ: ");
                break;
        }
        personViewHolder.explain.setText(qoranList.get(i).getContent());
    }
    @Override
    public int getItemCount() {
        return  qoranList.size();
    }
}
