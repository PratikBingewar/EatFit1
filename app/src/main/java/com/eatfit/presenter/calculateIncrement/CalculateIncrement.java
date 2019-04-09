package com.eatfit.presenter.calculateIncrement;

public class CalculateIncrement {
    double currentCalorieConsumption;
    double calorieConsumptionGoal;
    double timePeriodToReachGoal;
    String intensity;
    double incrementEachDay;

    public CalculateIncrement(double currentCalorieConsumption, double calorieConsumptionGoal,String intensity) {
        this.currentCalorieConsumption = currentCalorieConsumption;
        this.calorieConsumptionGoal = calorieConsumptionGoal;
        this.intensity = intensity;
    }


    public double calculateIncrement() {
        incrementEachDay = (calorieConsumptionGoal - currentCalorieConsumption) / incrementEachDay;
        return incrementEachDay;
    }

}
