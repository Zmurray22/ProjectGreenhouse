package com.example.projectgreenhouse;

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
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="plant_id")
    private Integer plant_id;
    public PlantItem(@NonNull Integer plant) {this.plant_id = plant;}
    public Integer getPlant(){return this.plant_id;}
    //Entity values
    private String nickname;
    private String species_name;
    private String profile_pic;
    private String freeze, heart, storm, toxic, water;

    public PlantItem(@NonNull Integer plant_id, String nickname, String species_name, String profile_pic, String freeze, String heart, String storm, String toxic, String water) {
        this.plant_id = plant_id;
        this.nickname = nickname;
        this.species_name = species_name;
        this.profile_pic = profile_pic;
        this.freeze = freeze;
        this.heart = heart;
        this.storm = storm;
        this.toxic = toxic;
        this.water = water;
    }

    public int getPlantId() {
        return plant_id;
    }

    public void setPlantId(Integer plant_id) {
        this.plant_id = plant_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }
    //Image methods
    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getStorm() {
        return storm;
    }

    public void setStorm(String storm) {
        this.storm = storm;
    }

    public String getToxic() {
        return toxic;
    }

    public void setToxic(String toxic) {
        this.toxic = toxic;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }
}
