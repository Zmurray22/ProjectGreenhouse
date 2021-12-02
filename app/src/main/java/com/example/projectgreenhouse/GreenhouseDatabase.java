package com.example.projectgreenhouse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlantItem.class}, version = 1, exportSchema = false)
public abstract class GreenhouseDatabase extends RoomDatabase {

    public abstract  PlantDao plantDao();

    private static GreenhouseDatabase INSTANCE;

    public static GreenhouseDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (GreenhouseDatabase.class){
                if (INSTANCE == null){
                    //TODO: Create database
                        //Drop and rebuild if no migration implemented
                        // TODO: Migration strategy
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GreenhouseDatabase.class, "greenhouse_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
