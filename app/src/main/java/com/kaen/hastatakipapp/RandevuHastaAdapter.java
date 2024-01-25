package com.kaen.hastatakipapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.databinding.ActivityRandevuBinding;
import com.kaen.hastatakipapp.databinding.RandevuHastaListItemBinding;

import java.util.List;

public class RandevuHastaAdapter extends RecyclerView.Adapter<RandevuHastaAdapter.RandevuHolder> {

    List<Hasta> hastaList;
    Context context;



    public RandevuHastaAdapter(List<Hasta> hastaList, Context context) {
        this.hastaList = hastaList;
        this.context = context;
    }



    @NonNull
    @Override
    public RandevuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RandevuHastaListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                        R.layout.randevu_hasta_list_item,
                        parent,
                        false

        );
        return new RandevuHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RandevuHolder holder, int position) {
        Hasta hasta = hastaList.get(position);
        holder.listItemBinding.setHasta(hasta);
    }

    @Override
    public int getItemCount() {
        return hastaList.size();
    }

    public class RandevuHolder extends RecyclerView.ViewHolder{

        RandevuHastaListItemBinding listItemBinding;

        public RandevuHolder(RandevuHastaListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
            listItemBinding.randevuCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(),RandevuEkleActivity.class);
                    HastaHandler.setHasta(hastaList.get(getAdapterPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
