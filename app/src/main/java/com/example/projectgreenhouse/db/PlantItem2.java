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
public class PlantItem2 {
    //Table name constructor
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="plant_id")
    private Integer plant_id;
    public PlantItem2(@NonNull Integer plant) {this.plant_id = plant;}
    public Integer getPlant(){return this.plant_id;}
    //Entity values
    private String nickname;
    private String species_name;
    private String profile_pic;
    private Boolean freeze, heart, storm, toxic, water;

    public PlantItem2(@NonNull Integer plant_id, String nickname, String species_name, String profile_pic, Boolean freeze, Boolean heart, Boolean storm, Boolean toxic, Boolean water) {
        this.plant_id = plant_id;
        this.nickname = nickname;
        this.species_name = species_name;
        this.profile_pic = profile_pic;
        this.freeze =freeze;
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

    //If true, will display predetermined icon
    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }

    public Boolean getHeart() {
        return heart;
    }

    public void setHeart(Boolean heart) {
        this.heart = heart;
    }

    public Boolean getStorm() {
        return storm;
    }

    public void setStorm(Boolean storm) {
        this.storm = storm;
    }

    public Boolean getToxic() {
        return toxic;
    }

    public void setToxic(Boolean toxic) {
        this.toxic = toxic;
    }

    public Boolean getWater() {
        return water;
    }

    public void setWater(Boolean water) {
        this.water = water;
    }
}
