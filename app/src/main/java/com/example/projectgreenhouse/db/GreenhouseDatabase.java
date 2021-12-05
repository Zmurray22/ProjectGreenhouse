package com.example.projectgreenhouse.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Plant.class}, version = 1)
public abstract class GreenhouseDatabase extends RoomDatabase {

    private static GreenhouseDatabase instance;
    public abstract PlantDao plantDao();

    public static synchronized GreenhouseDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GreenhouseDatabase.class, "plant_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    //Populate db on separate thread
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private  PlantDao plantDao;

        private PopulateDBAsyncTask(GreenhouseDatabase db){
            plantDao = db.plantDao();
        }

        @Override
        protected Void doInBackground(final Void... voids){
            plantDao.insert(new Plant("Roses"));
            plantDao.insert(new Plant("Gardenia"));
            plantDao.insert(new Plant("Apples"));
            return null;
        }
    }

}
