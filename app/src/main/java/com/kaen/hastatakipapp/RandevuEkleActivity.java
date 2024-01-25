package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.database.HastaDatabase;
import com.kaen.hastatakipapp.database.HastaViewModel;
import com.kaen.hastatakipapp.databinding.ActivityHastaEkleBinding;
import com.kaen.hastatakipapp.databinding.ActivityRandevuBinding;
import com.kaen.hastatakipapp.databinding.ActivityRandevuEkleBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RandevuEkleActivity extends AppCompatActivity {
    ActivityRandevuEkleBinding binding;
    private Calendar dogumTarihiDate;
    private String tarih = "";
    private String saat = "";
    HastaDatabase database;
    HastaViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_ekle);
        Hasta hasta = HastaHandler.getHasta();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_randevu_ekle);


        database = HastaDatabase.getInstance(this);
        viewModel = new ViewModelProvider(this).get(HastaViewModel.class);

        dogumTarihiDate = Calendar.getInstance();


        binding.buttonRandevuTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        binding.buttonRandevuSaati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });



        binding.butonRandevuEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yer = binding.editTextYer.getText().toString().trim();
                String poliklinik = binding.editTextPoliklinik.getText().toString().trim();
                if (yer.isEmpty() || poliklinik.isEmpty() || tarih.isEmpty() || saat.isEmpty()){
                    Toast.makeText(RandevuEkleActivity.this, "Lütfen gerekli tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show();
                }
                else {
                 hasta.setRandevuYeri(yer);
                 hasta.setRandevuDate(tarih);
                 hasta.setRandevuTime(saat);
                 hasta.setRandevuPoliklinik(poliklinik);

                 Toast.makeText(RandevuEkleActivity.this, "Randevu Eklendi.", Toast.LENGTH_SHORT).show();
                 viewModel.updateHasta(hasta);
                 Intent intent = new Intent(RandevuEkleActivity.this, MainActivity.class);
                 startActivity(intent);
                }
            }
        });
    }

    private void showTimePickerDialog() {
        Calendar randevuSaat = Calendar.getInstance();
        int hour = randevuSaat.get(Calendar.HOUR_OF_DAY);
        int minute = randevuSaat.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        saat = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                        binding.buttonRandevuSaati.setText(saat);
                    }
                },
                hour,
                minute,
                true
        );

        timePickerDialog.show();
    }


    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dogumTarihiDate.set(Calendar.YEAR, year);
                        dogumTarihiDate.set(Calendar.MONTH, month);
                        dogumTarihiDate.set(Calendar.DAY_OF_MONTH, day);
                        tarih = updateButtonText();
                        binding.buttonRandevuTarihi.setText(tarih);
                    }
                },
                dogumTarihiDate.get(Calendar.YEAR),
                dogumTarihiDate.get(Calendar.MONTH),
                dogumTarihiDate.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();

    }

    private String updateButtonText() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        String formatDate = simpleDateFormat.format(dogumTarihiDate.getTime());
        return formatDate;
    }
}