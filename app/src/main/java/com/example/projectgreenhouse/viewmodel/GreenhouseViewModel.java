package com.example.projectgreenhouse.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectgreenhouse.db.GreenhouseRepository;
import com.example.projectgreenhouse.db.Plant;

import java.util.List;

public class GreenhouseViewModel extends AndroidViewModel {
    private GreenhouseRepository repository;
    private LiveData<List<Plant>> allPlants;
    //Reference to repository

    //Constructor
    public GreenhouseViewModel (@NonNull Application application){
        super(application);
        repository = new GreenhouseRepository(application);
        allPlants = repository.getAllPlants();
    }

    //wrapper to call repository insert() method
    public void insert(Plant plant) {repository.insert(plant);}

    public void update(Plant plant) {repository.update(plant);}

    public void delete(Plant plant) {repository.delete(plant);}

    public void deleteAllPlants(Plant plant) {repository.deleteAllPlants();}

    public LiveData<List<Plant>> getAllPlants() {
        return allPlants;
    }


}
