package com.example.projectgreenhouse;

import android.graphics.Bitmap;

public class PlantItem {

    private String nickname;
    private String species_name;
    private Bitmap profile_pic;
    private Bitmap freeze, heart, storm, toxic, water;

    public PlantItem(String nickname, String species_name) {
        this.nickname = nickname;
        this.species_name = species_name;
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
    public Bitmap getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Bitmap profile_pic) {
        this.profile_pic = profile_pic;
    }

    public Bitmap getFreeze() {
        return freeze;
    }

    public void setFreeze(Bitmap freeze) {
        this.freeze = freeze;
    }

    public Bitmap getHeart() {
        return heart;
    }

    public void setHeart(Bitmap heart) {
        this.heart = heart;
    }

    public Bitmap getStorm() {
        return storm;
    }

    public void setStorm(Bitmap storm) {
        this.storm = storm;
    }

    public Bitmap getToxic() {
        return toxic;
    }

    public void setToxic(Bitmap toxic) {
        this.toxic = toxic;
    }

    public Bitmap getWater() {
        return water;
    }

    public void setWater(Bitmap water) {
        this.water = water;
    }
}
