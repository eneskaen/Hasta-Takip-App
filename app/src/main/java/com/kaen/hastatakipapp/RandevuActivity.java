package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.database.HastaDatabase;
import com.kaen.hastatakipapp.database.HastaViewModel;
import com.kaen.hastatakipapp.databinding.ActivityHastaListeleBinding;
import com.kaen.hastatakipapp.databinding.ActivityRandevuBinding;

import java.util.ArrayList;
import java.util.List;

public class RandevuActivity extends AppCompatActivity {
    ActivityRandevuBinding binding;

    private HastaViewModel viewModel;

    private ArrayList<Hasta> hastaList = new ArrayList<>();

    private HastaDatabase database;

    private RandevuHastaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_randevu);

        binding.recyclerViewRandevu.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewRandevu.setHasFixedSize(true);

        database = HastaDatabase.getInstance(this);

        viewModel = new ViewModelProvider(this).get(HastaViewModel.class);

        viewModel.getAllHastas().observe(this, new Observer<List<Hasta>>() {
            @Override
            public void onChanged(List<Hasta> hastas) {
                hastaList.clear();
                for (Hasta h : hastas){
                    hastaList.add(h);
                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new RandevuHastaAdapter(hastaList,this.getApplicationContext());
        binding.recyclerViewRandevu.setAdapter(adapter);
    }
}