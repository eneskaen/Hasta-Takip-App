package com.kaen.hastatakipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.kaen.hastatakipapp.database.Hasta;
import com.kaen.hastatakipapp.database.HastaDatabase;
import com.kaen.hastatakipapp.database.HastaViewModel;
import com.kaen.hastatakipapp.databinding.ActivityHastaEkleBinding;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HastaEkleActivity extends AppCompatActivity {

    private Calendar dogumTarihiDate;
    private String tarih = "";
    ActivityHastaEkleBinding binding;
    HastaDatabase database;
    HastaViewModel viewModel;
    int mode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_ekle);

        binding = DataBindingUtil.setContentView(this , R.layout.activity_hasta_ekle);

        Intent intent = getIntent();
        if (intent.hasExtra("Name")){
            mode = 1;
            Hasta hasta = HastaHandler.getHasta();
            binding.editTextName.setText(hasta.getName());
            binding.editTextTc.setText(hasta.getTcNo());
            binding.editTextIlac.setText(hasta.getIlaclar());
            binding.editTextTc.setText(hasta.getTcNo());
            binding.buttonDogumTarihi.setText(hasta.getBirthDate());
            binding.textViewTitle.setText("Hasta Düzenle");
            binding.buttonEkle.setText("Hastayı Güncelle");
            binding.buttonSil.setVisibility(View.VISIBLE);
        }


        database = HastaDatabase.getInstance(this);
        viewModel = new ViewModelProvider(this).get(HastaViewModel.class);

        dogumTarihiDate = Calendar.getInstance();

        binding.buttonDogumTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        binding.buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receteKod = createRandomRecete();
                String name = binding.editTextName.getText().toString().trim();
                String tcNo = binding.editTextTc.getText().toString().trim();
                String ilaclar = binding.editTextIlac.getText().toString().trim();
                if (ilaclar.isEmpty()){
                    ilaclar = "İlaç bulunamadı!";
                }
                if (name.isEmpty() || tcNo.isEmpty() || tarih.isEmpty() || tarih == null){
                    Toast.makeText(HastaEkleActivity.this, "Lütfen gerekli tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (mode == 1){
                        Hasta hasta = HastaHandler.getHasta();

                        hasta.setName(binding.editTextName.getText().toString().trim());

                        hasta.setBirthDate(binding.buttonDogumTarihi.getText().toString().trim());

                        hasta.setIlaclar(binding.editTextIlac.getText().toString().trim());

                        hasta.setTcNo(binding.editTextTc.getText().toString().trim());

                        Toast.makeText(HastaEkleActivity.this, "Hasta Güncellendi", Toast.LENGTH_SHORT).show();
                        viewModel.updateHasta(hasta);
                    }
                    else {
                        Hasta hasta = new Hasta(name, tcNo, tarih, receteKod, ilaclar);
                        viewModel.addHasta(hasta);
                        Toast.makeText(HastaEkleActivity.this, "Hasta veritabanına eklendi.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(HastaEkleActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.buttonSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hasta hasta = HastaHandler.getHasta();
                viewModel.deleteHasta(hasta);
                Toast.makeText(HastaEkleActivity.this, "Hasta veritabanından silindi.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HastaEkleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private String createRandomRecete() {

        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8 ; i++){

            int randomValue = random.nextInt(36);

            char randomChar = (char) (randomValue < 10 ? '0' + randomValue : 'A' + (randomValue - 10));

            result.append(randomChar);
        }

        return result.toString();
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
                        binding.buttonDogumTarihi.setText(tarih);
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