package com.kaen.hastatakipapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "hasta_table")
public class Hasta{

    @ColumnInfo(name = "hasta_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "hasta_adi")
    private String name;

    @ColumnInfo(name = "hasta_tc_no")
    private String tcNo;

    @ColumnInfo(name = "hasta_birth")
    private String birthDate;

    @ColumnInfo(name = "hasta_recete_kod")
    private String receteKodu;

    @ColumnInfo(name = "hasta_ilaclar")
    private String ilaclar;

    @ColumnInfo(name = "hasta_randevu_date")
    private String randevuDate = "";

    @ColumnInfo(name = "hasta_randevu_time")
    private String randevuTime = "";

    @ColumnInfo(name = "hasta_randevu_yer")
    private String randevuYeri = "";

    @ColumnInfo(name = "hasta_randevu_poliklinik")
    private String randevuPoliklinik = "";

    public Hasta() {
    }


    public Hasta(String name, String tcNo, String birthDate, String receteKodu, String ilaclar) {
        this.name = name;
        this.tcNo = tcNo;
        this.birthDate = birthDate;
        this.receteKodu = receteKodu;
        this.ilaclar = ilaclar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getReceteKodu() {
        return receteKodu;
    }

    public void setReceteKodu(String receteKodu) {
        this.receteKodu = receteKodu;
    }

    public String getIlaclar() {
        return ilaclar;
    }

    public void setIlaclar(String ilaclar) {
        this.ilaclar = ilaclar;
    }

    public String getReceteString(){
        return "e-Reçete Kodu: " + getReceteKodu();
    }

    public String getRandevuDate() {
        return randevuDate;
    }

    public void setRandevuDate(String randevuDate) {
        this.randevuDate = randevuDate;
    }

    public String getRandevuTime() {
        return randevuTime;
    }

    public void setRandevuTime(String randevuTime) {
        this.randevuTime = randevuTime;
    }

    public String getRandevuYeri() {
        return randevuYeri;
    }

    public void setRandevuYeri(String randevuYeri) {
        this.randevuYeri = randevuYeri;
    }

    public String getRandevuPoliklinik() {
        return randevuPoliklinik;
    }

    public void setRandevuPoliklinik(String randevuPoliklinik) {
        this.randevuPoliklinik = randevuPoliklinik;
    }
}
