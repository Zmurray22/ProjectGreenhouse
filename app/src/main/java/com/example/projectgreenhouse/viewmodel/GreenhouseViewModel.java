package com.example.projectgreenhouse.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectgreenhouse.db.GreenhouseRepository;
import com.example.projectgreenhouse.db.PlantItem;

import java.util.List;

public class GreenhouseViewModel extends AndroidViewModel {
    //Reference to repository
    private GreenhouseRepository mRepository;
    //cache the list
    private LiveData<List<PlantItem>> mAllPlants;
    //Constructor
    public GreenhouseViewModel (Application application){
        super(application);
        mRepository = new GreenhouseRepository(application);
        mAllPlants = mRepository.getAllPlants();
    }

    LiveData<List<PlantItem>> getAllPlants() {return mAllPlants;}

    //wrapper to called repository insert() method
    public void insert(PlantItem item) {mRepository.insert(item);}

}
