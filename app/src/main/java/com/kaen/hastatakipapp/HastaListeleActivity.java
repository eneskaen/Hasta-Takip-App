package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.database.HastaDatabase;
import com.kaen.hastatakipapp.database.HastaViewModel;
import com.kaen.hastatakipapp.databinding.ActivityHastaListeleBinding;
import com.kaen.hastatakipapp.databinding.HastaListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HastaListeleActivity extends AppCompatActivity {

    ActivityHastaListeleBinding binding;

    private HastaViewModel viewModel;

    private ArrayList<Hasta> hastaList = new ArrayList<>();

    private HastaDatabase database;

    private HastaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_listele);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_hasta_listele);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

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
        adapter = new HastaAdapter(hastaList,this.getApplicationContext());
        binding.recyclerView.setAdapter(adapter);
    }
}