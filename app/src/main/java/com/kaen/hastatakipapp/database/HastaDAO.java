package com.kaen.hastatakipapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HastaDAO {

    @Insert
    void insert(Hasta hasta);

    @Delete
    void delete(Hasta hasta);

    @Update
    void update(Hasta hasta);

    @Query("select * from hasta_table")
    LiveData<List<Hasta>> getAllHasta();

    @Query("delete from hasta_table")
    void deleteAll();

}
