package com.assignment.entities;

import java.io.Serializable;

/**
 * Created by muddassiriqbal on 24/07/15.
 * This class represents the individual food item
 */
public class FoodItem implements Serializable {
    private int id;
    private String dishName;
    private String description;
    private int quantity;
    private double rate;
    private boolean isVeg;
    private String foodType;
    private int[] images; // this actually need to be taken string but due to hard coding I have taken it as int to store drawable id
    private Chef chef;

    public FoodItem(int id, String dishName, String description, int quantity, double rate
            , boolean isVeg, String foodType, int[] images, Chef chef) {
        this.id = id;
        this.dishName = dishName;
        this.description = description;
        this.quantity = quantity;
        this.rate = rate;
        this.isVeg = isVeg;
        this.foodType = foodType;
        this.images = images;
        this.chef = chef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setIsVeg(boolean isVeg) {
        this.isVeg = isVeg;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }
}
