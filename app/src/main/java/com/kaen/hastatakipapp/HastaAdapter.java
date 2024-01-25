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
import com.kaen.hastatakipapp.databinding.HastaListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HastaAdapter extends RecyclerView.Adapter<HastaAdapter.HastaViewHolder> {

    private List<Hasta> hastaArrayList;
    private Context context;
    public HastaAdapter(ArrayList<Hasta> hastaArrayList, Context context) {
        this.hastaArrayList = hastaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HastaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HastaListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.hasta_list_item,
                parent,
                false
        );

        return new HastaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HastaViewHolder holder, int position) {
        Hasta currentHasta = hastaArrayList.get(position);
        holder.binding.setHasta(currentHasta);
    }

    @Override
    public int getItemCount() {
        return hastaArrayList.size();
    }


    public class HastaViewHolder extends RecyclerView.ViewHolder{
        private HastaListItemBinding binding;

        public HastaViewHolder(HastaListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ilaclarimBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, IlaclarActivity.class);
                    intent.putExtra("Ilaclar", hastaArrayList.get(getAdapterPosition()).getIlaclar());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            binding.hastaCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HastaEkleActivity.class);
                    intent.putExtra("Ilaclar", hastaArrayList.get(getAdapterPosition()).getIlaclar());
                    intent.putExtra("Name", hastaArrayList.get(getAdapterPosition()).getName());
                    intent.putExtra("Tc", hastaArrayList.get(getAdapterPosition()).getTcNo());
                    intent.putExtra("DogumTarihi", hastaArrayList.get(getAdapterPosition()).getBirthDate());
                    intent.putExtra("Id", hastaArrayList.get(getAdapterPosition()).getId());
                    HastaHandler.setHasta(hastaArrayList.get(getAdapterPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            binding.randevuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RandevuGoruntule.class);
                    HastaHandler.setHasta(hastaArrayList.get(getAdapterPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
