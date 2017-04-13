package com.example.dell_pc.health_first;

/**
 * Created by nikpa on 26-02-2017.
 */

public class DietFields
{
    String foodName;

    int servingSize;

    int calories;


    public DietFields()
    {

    }


    public DietFields(String foodName,int servingSize,int calories)
    {
        this.foodName=foodName;
        this.servingSize=servingSize;
        this.calories=calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }
}
