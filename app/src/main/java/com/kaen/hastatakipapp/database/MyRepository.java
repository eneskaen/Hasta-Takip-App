package com.kaen.hastatakipapp.database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository {
    private HastaDAO hastaDAO;
    private LiveData<List<Hasta>> allHastas;

    private ExecutorService executor;

    private Handler handler;

    public MyRepository(Application application) {
        HastaDatabase database = HastaDatabase.getInstance(application);
        this.hastaDAO = database.hastaDAO();
        this.allHastas = hastaDAO.getAllHasta();
        this.executor = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void insert(Hasta hasta){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                hastaDAO.insert(hasta);
            }
        });
    }

    public void update(Hasta hasta){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                hastaDAO.update(hasta);
            }
        });
    }

    public void delete(Hasta hasta){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                hastaDAO.delete(hasta);
            }
        });
    }

    public LiveData<List<Hasta>> getAllHastas(){
        allHastas = hastaDAO.getAllHasta();
        return allHastas;
    }

    public void deleteAll(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                hastaDAO.deleteAll();
            }
        });
    }
}
