package com.kaen.hastatakipapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kaen.hastatakipapp.databinding.ActivityIlaclarBinding;
import com.kaen.hastatakipapp.databinding.IlacItemBinding;

import java.util.List;

public class IlacAdapter extends RecyclerView.Adapter<IlacAdapter.IlacHolder> {

    List<String> ilaclar;
    Context context;

    public IlacAdapter(List<String> ilaclar, Context context) {
        this.context = context;
        this.ilaclar = ilaclar;
    }

    @NonNull
    @Override
    public IlacAdapter.IlacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IlacItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.ilac_item,
                parent,
                false
        );

        return new IlacHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IlacAdapter.IlacHolder holder, int position) {
        holder.binding.ilacAdi.setText(ilaclar.get(position));
    }

    @Override
    public int getItemCount() {
        return ilaclar.size();
    }

    public class IlacHolder extends RecyclerView.ViewHolder{
        IlacItemBinding binding;

        public IlacHolder(IlacItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
