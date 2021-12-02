package com.example.projectgreenhouse.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PlantItem.class}, version = 1, exportSchema = false)
public abstract class GreenhouseDatabase extends RoomDatabase {

    public abstract PlantDao plantDao();
    private static GreenhouseDatabase INSTANCE;

    public static GreenhouseDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (GreenhouseDatabase.class){
                if (INSTANCE == null){
                    //TODO: Create database
                        //Drop and rebuild if no migration implemented
                        // TODO: Migration strategy
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GreenhouseDatabase.class, "greenhouse_database").fallbackToDestructiveMigration().addCallback(sRoomDBCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    //Populate db on separate thread
    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final PlantDao mDao;
        String[] items = {"Red", "Blue", "Green"};

        PopulateDBAsync(GreenhouseDatabase db){
            mDao = db.plantDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            //Temporary implementation to wipe db on restart
            mDao.deleteAlL();

            for(int i = 0; i <= items.length - 1; i++){
                PlantItem plantItem = new PlantItem(items[i]);mDao.insert(plantItem);
            }
            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDBCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };
}
