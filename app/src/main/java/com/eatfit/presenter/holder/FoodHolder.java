package com.eatfit.presenter.holder;

import java.io.Serializable;

public class FoodHolder implements Serializable {
    int id;
    String name;
    double carbs;
    double protein;
    double fat;
    String servingUnit;
    double unitCal;
    double weight;

    public FoodHolder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public double getUnitCal() {
        return unitCal;
    }

    public void setUnitCal(double unitCal) {
        this.unitCal = unitCal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public FoodHolder(int id, String name, double carbs, double protein, double fat, String servingUnit, double unitCal, double weight) {
        this.id = id;
        this.name = name;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.servingUnit = servingUnit;
        this.unitCal = unitCal;
        this.weight = weight;
    }
}
