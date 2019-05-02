package com.eatfit.model.eatenFoodList;

import java.io.Serializable;
import java.util.ArrayList;

public class EatenFoodList implements Serializable {
    ArrayList<String> foodList = new ArrayList<>();
    Double carbs = 0.0;
    Double protein = 0.0;
    Double fat = 0.0;
    Double totalCal = 0.0;

    public EatenFoodList(){

    }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(String food) {
        foodList.add(food);
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = this.carbs + carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = this.protein + protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = this.fat + fat;
    }

    public Double getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(Double totalCal) {
        this.totalCal = this.totalCal + totalCal;
    }
}
