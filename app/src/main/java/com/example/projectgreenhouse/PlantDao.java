package com.example.projectgreenhouse;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert
    void insert(PlantItem item);

    @Delete
    void delete(PlantItem item);

    @Update
    void update(PlantItem item);

    @Query("DELETE FROM plant_item")
    void deleteAlL();

    @Query("SELECT * from plant_item ORDER BY plant_id ASC")
    List<PlantItem> getAllWords();
}
