package com.example.projectgreenhouse.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*Table (Example)
                                    plant_item
________________________________________________________________________________________
|plant_id|nickname|species_name|profile_pic|freeze  |heart   |storm   |toxic   |water   |
|1       |Rose    |R. damascena|filePath   |filePath|filePath|filePath|filePath|filePath|
 */

@Entity(tableName = "plant_table")
public class Plant {

    //Table name constructor
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nickname;

    //Constructor
    public Plant(String nickname) {
        this.nickname = nickname;
    }

        //TODO: Create list of table records

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
