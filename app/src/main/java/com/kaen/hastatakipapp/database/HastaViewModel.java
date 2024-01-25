package com.kaen.hastatakipapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HastaViewModel extends AndroidViewModel {

    private MyRepository myRepository;

    private LiveData<List<Hasta>> allHastas;

    public HastaViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new MyRepository(application);
        allHastas = myRepository.getAllHastas();
    }

    public void addHasta(Hasta hasta){
        myRepository.insert(hasta);
    }
    public void updateHasta(Hasta hasta){
        myRepository.update(hasta);
    }
    public void deleteHasta(Hasta hasta){
        myRepository.delete(hasta);
    }
    public void deleteAll(){
        myRepository.deleteAll();
    }
    public LiveData<List<Hasta>> getAllHastas(){
        return allHastas;
    }

}
