package com.eatfit.presenter.holder;

public class FoodHolder {
    int id;
    String name;
    double carbs;
    double protein;
    double fat;
    String servingUnit;
    double unitCal;
    double weight;

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
