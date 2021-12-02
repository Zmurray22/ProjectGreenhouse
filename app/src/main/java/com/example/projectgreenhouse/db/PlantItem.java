package com.example.projectgreenhouse.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*Table (Example)
                                    plant_item
________________________________________________________________________________________
|plant_id|nickname|species_name|profile_pic|freeze  |heart   |storm   |toxic   |water   |
|1       |Rose    |R. damascena|filePath   |filePath|filePath|filePath|filePath|filePath|
 */

@Entity(tableName = "plant_item")
public class PlantItem {

    //Table name constructor
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="nickname")
    private String nickname;


    public PlantItem(String nickname) {
        this.nickname = nickname;}

    public String getPlant(){
        return this.nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
