package com.example.projectgreenhouse.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectgreenhouse.db.GreenhouseRepository;
import com.example.projectgreenhouse.db.PlantItem;

import java.util.List;

public class GreenhouseViewModel extends AndroidViewModel {
    private static LiveData<List<PlantItem>> mAllPlants;
    //Reference to repository
    private GreenhouseRepository mRepository;

    //Constructor
    public GreenhouseViewModel (Application application){
        super(application);
        mRepository = new GreenhouseRepository(application);
        mAllPlants = mRepository.getAllPlants();
    }

    public static LiveData<List<PlantItem>> getAllPlants() {
        return mAllPlants;
    }

    //wrapper to call repository insert() method
    public void insert(PlantItem item) {mRepository.insert(item);}

}
