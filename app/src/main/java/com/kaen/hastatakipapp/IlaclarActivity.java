package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kaen.hastatakipapp.databinding.ActivityIlaclarBinding;

import java.util.Arrays;
import java.util.List;

public class IlaclarActivity extends AppCompatActivity {

    ActivityIlaclarBinding binding;
    IlacAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilaclar);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ilaclar);
        Intent intent = getIntent();
        String ilaclar = intent.getStringExtra("Ilaclar");
        String[] ilacList = ilaclar.split(", ");
        List<String> ilaclarList = Arrays.asList(ilacList);

        binding.ilacRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.ilacRecyclerView.setHasFixedSize(true);
        adapter = new IlacAdapter(ilaclarList,this);
        binding.ilacRecyclerView.setAdapter(adapter);

    }
}