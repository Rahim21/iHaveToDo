package com.example.iHaveToDo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;

    // List<EntityClass> entityClasses;

    List<EntityClass> entityClasses;

    public HistoryAdapter(Context context, List<EntityClass> entityClasses ) {
        this.context = context;
        this.entityClasses = entityClasses;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventText.setText(entityClasses.get(position).getEventname());
        holder. Date.setText(entityClasses.get(position).getEventdate());
        holder.time .setText(entityClasses.get(position).getEventtime());

    }

    @Override
    public int getItemCount() {
        return entityClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventText, time, Date;
        private RelativeLayout toplayout;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.message);
            Date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time3);
            toplayout = (RelativeLayout) itemView.findViewById(R.id.layout);
            imageView = (ImageView) itemView.findViewById(R.id.icon);

        }
    }
}