package com.example.projectgreenhouse.db;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GreenhouseRepository {
    private PlantDao plantDao;
    private LiveData<List<Plant>> allPlants;

    public GreenhouseRepository(Application application) {
        GreenhouseDatabase database = GreenhouseDatabase.getInstance(application);
        plantDao = database.plantDao();
        allPlants = plantDao.getAllPlants();
    }

    public void insert(Plant plant) {
        new InsertPlantAsyncTask(plantDao).execute(plant);
    }

    public void update(Plant plant) {
        new UpdatePlantAsyncTask(plantDao).execute(plant);
    }

    public void delete(Plant plant) {
        new DeletePlantAsyncTask(plantDao).execute(plant);
    }

    public void deleteAllPlants() {
        new DeleteAllPlantsAsyncTask(plantDao).execute();
    }

    public LiveData<List<Plant>> getAllPlants() {
        return allPlants;
    }

    private static class InsertPlantAsyncTask extends AsyncTask<Plant, Void, Void> {
        private PlantDao plantDao;

        private InsertPlantAsyncTask(PlantDao plantDao) {
            this.plantDao = plantDao;
        }

        @Override
        protected Void doInBackground(Plant... plants) {
            plantDao.insert(plants[0]);
            return null;
        }
    }

    private static class UpdatePlantAsyncTask extends AsyncTask<Plant, Void, Void> {
        private PlantDao plantDao;

        private UpdatePlantAsyncTask(PlantDao plantDao) {
            this.plantDao = plantDao;
        }

        @Override
        protected Void doInBackground(Plant... plants) {
            plantDao.update(plants[0]);
            return null;
        }
    }

    private static class DeletePlantAsyncTask extends AsyncTask<Plant, Void, Void> {
        private PlantDao plantDao;

        private DeletePlantAsyncTask(PlantDao plantDao) {
            this.plantDao = plantDao;
        }

        @Override
        protected Void doInBackground(Plant... plants) {
            plantDao.delete(plants[0]);
            return null;
        }
    }

    private static class DeleteAllPlantsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlantDao plantDao;

        private DeleteAllPlantsAsyncTask(PlantDao plantDao) {
            this.plantDao = plantDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            plantDao.deleteAlLPlants();
            return null;
        }
    }
}