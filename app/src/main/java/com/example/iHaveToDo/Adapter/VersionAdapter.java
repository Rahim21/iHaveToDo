package com.example.iHaveToDo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.R;

import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionVH> {

    List<EntityClass> versionsList;


    public VersionAdapter(List<EntityClass> versionsList) {
        this.versionsList = versionsList;
    }

    @NonNull
    @Override
    public VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new VersionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionVH holder, int position) {

        EntityClass versions = versionsList.get(position);
        holder.today.setText(versions.getEventname());
        holder.versionTxt.setText(versions.getEventtime());
        holder.apilevel.setText(versions.getEventdate());
//        holder.description.setText(versions.get());

        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.relativeLayout.setVisibility(isExpandable ? View.VISIBLE :  View.GONE);

    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        TextView today, versionTxt, apilevel, description;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            today = itemView.findViewById(R.id.header1);
            versionTxt = itemView.findViewById(R.id.version);
            apilevel = itemView.findViewById(R.id.api_level);
            description = itemView.findViewById(R.id.descriptionn);

            linearLayout = itemView.findViewById(R.id.linearlayout1);
            relativeLayout = itemView.findViewById(R.id.expand);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EntityClass versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}
