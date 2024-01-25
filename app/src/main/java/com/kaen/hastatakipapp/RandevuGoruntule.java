package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.databinding.ActivityRandevuGoruntuleBinding;

public class RandevuGoruntule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_goruntule);
        Hasta hasta = HastaHandler.getHasta();
        ActivityRandevuGoruntuleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_randevu_goruntule);
        binding.setHasta(hasta);
    }
}