package com.example.projectgreenhouse.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GreenhouseRepository {
    private PlantDao mPlantDao;
    private LiveData<List<PlantItem>> mAllPlants;

    //Constructor
    public GreenhouseRepository(Application application){
        GreenhouseDatabase db = GreenhouseDatabase.getDatabase(application);
        mPlantDao = db.plantDao();
        mAllPlants = mPlantDao.getAllPlants();
    }
    //Return cached data as LiveData
    public LiveData<List<PlantItem>> getAllPlants(){
        return mAllPlants;
    }
    //Wrapper to call insert on new thread
    public void insert (PlantItem item){
        new insertAsyncTask(mPlantDao).execute(item);
    }
    //Inner class
    private static class insertAsyncTask extends AsyncTask<PlantItem, Void, Void> {

        private PlantDao mAsyncTaskDao;
        //TODO: Replace with non-depreciated alternative
        insertAsyncTask(PlantDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlantItem... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
