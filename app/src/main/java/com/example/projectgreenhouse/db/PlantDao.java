package com.example.projectgreenhouse.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert
    void insert(Plant plant);

    @Delete
    void delete(Plant plant);

    @Update
    void update(Plant plant);

    @Query("DELETE FROM plant_table")
    void deleteAlLPlants();

    @Query("SELECT * from plant_table ORDER BY id ASC")
    LiveData<List<Plant>> getAllPlants();
}
