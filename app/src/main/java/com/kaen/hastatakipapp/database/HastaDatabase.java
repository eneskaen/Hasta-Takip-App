package com.kaen.hastatakipapp.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Hasta.class, version = 2)
public abstract class HastaDatabase extends RoomDatabase {

    public abstract HastaDAO hastaDAO();
    private static HastaDatabase database;
    public static synchronized HastaDatabase getInstance(Context context) {

        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), HastaDatabase.class, "hasta_database")
                    .fallbackToDestructiveMigration().build();

        }
        return database;
    }

}
