package com.assignment.entities;

import java.io.Serializable;

/**
 * Created by muddassiriqbal on 24/07/15.
 * This class represents a chef
 */
public class Chef implements Serializable {
    private int id;
    private String chefName;
    private float rating;
    private int chefImage; // this actually need to be taken string but due to hard coding I have taken it as int

    public Chef(int id, String chefName, float rating, int chefImage) {
        this.id = id;
        this.chefName = chefName;
        this.rating = rating;
        this.chefImage = chefImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getChefImage() {
        return chefImage;
    }

    public void setChefImage(int chefImage) {
        this.chefImage = chefImage;
    }
}
