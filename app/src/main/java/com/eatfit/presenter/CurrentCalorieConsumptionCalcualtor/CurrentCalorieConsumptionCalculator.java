package com.eatfit.presenter.CurrentCalorieConsumptionCalcualtor;

public class CurrentCalorieConsumptionCalculator {
    double breakfastIntake,lunchIntake,snackIntake,dinnerIntake;
    double totalIntake;

    public CurrentCalorieConsumptionCalculator(double breakfastIntake, double lunchIntake, double snackIntake, double dinnerIntake){
        this.breakfastIntake = breakfastIntake;
        this.lunchIntake = lunchIntake;
        this.snackIntake = snackIntake;
        this.dinnerIntake = dinnerIntake;
    }

    public double calculateAndReturnTotalCalories(){
        totalIntake = breakfastIntake+lunchIntake+snackIntake+dinnerIntake;
        return totalIntake;
    }
}
