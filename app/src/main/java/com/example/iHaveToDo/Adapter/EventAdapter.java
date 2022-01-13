package com.example.iHaveToDo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.R;

import java.util.List;



public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{
    Context context;

    List<EntityClass> entityClasses;



    public EventAdapter(Context context, List<EntityClass> entityClasses ) {
        this.context = context;
        this.entityClasses = entityClasses;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.eventText.setText(entityClasses.get(position).getEventname());
        holder.timeAndDateText.setText(context.getResources().getString(R.string.datee)+entityClasses.get(position).getEventdate()+"  "  + context.getResources().getString(R.string.timee) + entityClasses.get(position).getEventtime());
//        holder.item_header.setText(context.getResources().getString(R.string.to_do)+ entityClasses.get(position).isEventdone());
        holder.item_header.setText(context.getResources().getString(R.string.to_do)+ entityClasses.get(position).getEventname());


    }

    @Override
    public int getItemCount() {
        return entityClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventText, timeAndDateText;
        private CardView toplayout;
        private TextView item_header;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time2);
            toplayout = (CardView) itemView.findViewById(R.id.toplayout);
            item_header = (TextView) itemView.findViewById(R.id.subheaderText1);


        }
    }
}