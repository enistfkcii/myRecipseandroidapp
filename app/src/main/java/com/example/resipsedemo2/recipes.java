package com.example.resipsedemo2;

import java.io.Serializable;

public class recipes implements Serializable {
    private int id;
    private String foodName;
    private String preparationTime;
    private int numberOfPerson;
    private String picture;
    private String material;
    private String calories;
    private String recipe;
    private int time;
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }



    public String getMaterial() {
        return material;
    }

    public void setMalzemeler(String malzeme) {
        this.material = malzeme;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public recipes() {
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public recipes(int id, String foodName, String preparationTime, int numberOfPerson, String picture, String malzemeler, String recipe, int time, String calories) {
        this.id = id;
        this.foodName = foodName;

        this.preparationTime = preparationTime;
        this.numberOfPerson = numberOfPerson;
        this.picture = picture;
        this.time = time;
        this.calories = calories;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }
}