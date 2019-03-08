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

    public void calculateTimePeriodAndIncrement(){
        checkIntensityAndSetIncrementInCalories();
        calculateTimePeriod();
    }

    private void calculateTimePeriod() {
        timePeriodToReachGoal = (calorieConsumptionGoal - currentCalorieConsumption) / incrementEachDay;
    }

    private void checkIntensityAndSetIncrementInCalories() {
        intensity = intensity.toLowerCase();

        if(intensity.equals("easy")) {
            incrementEachDay = 30;

        }else if(intensity.equals("medium")){
            incrementEachDay = 60;

        }else if(intensity.equals("hard")) {
            incrementEachDay = 100;

        }
    }
}
