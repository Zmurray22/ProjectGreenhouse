package com.example.projectgreenhouse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlantItem.class}, version = 1, exportSchema = false)
public abstract class PlantRoomDatabase extends RoomDatabase {

    public abstract  PlantDao plantDao();

    private static PlantRoomDatabase INSTANCE;

    public static PlantRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (PlantRoomDatabase.class){
                if (INSTANCE == null){
                    //TODO: Create database
                        //Drop and rebuild if no migration implemented
                        // TODO: Migration strategy
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlantRoomDatabase.class, "greenhouse_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
