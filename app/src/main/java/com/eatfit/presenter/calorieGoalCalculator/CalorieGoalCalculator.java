package com.eatfit.presenter.calorieGoalCalculator;

import android.widget.Switch;

import java.security.PublicKey;

public class CalorieGoalCalculator {
    String lifestyle;
    double BMR;
    double calorieGoal;
    String fitnessGoal;

    public CalorieGoalCalculator(String lifestyle, double BMR,String fitnessGoal) {
        this.lifestyle = lifestyle;
        this.BMR = BMR;
        this.fitnessGoal = fitnessGoal;
    }

    public double getCalorieGoal(){
        lifestyle = lifestyle.toLowerCase();
        fitnessGoal = fitnessGoal.toLowerCase();

        if(fitnessGoal.equals("muscle gain"))
            checkLifestyleAndSetCalorieGoalForMuscleGain();
        else if(fitnessGoal.equals("weight loss"))
            checkLifestyleAndSetCalorieGoalForWeightLoss();
        else if (fitnessGoal.equals("weight loss"))
            checkLifestyleAndSetCalorieGoalForStayFit();

        return calorieGoal;
    }

    private void checkLifestyleAndSetCalorieGoalForStayFit() {
        switch(lifestyle) {

            case "sedentary":
                calorieGoal = BMR*1.2;
                break;

            case "lightly active":
                calorieGoal = BMR*1.4;
                break;

            case "moderately active":
                calorieGoal = BMR*1.575;
                break;

            case "very active":
                calorieGoal = BMR*1.75;
                break;

            case "extra active":
                calorieGoal = BMR*1.96;
                break;
        }
    }

    private void checkLifestyleAndSetCalorieGoalForWeightLoss() {

        switch(lifestyle) {

            case "sedentary":
                calorieGoal = BMR*1.2;
                break;

            case "lightly active":
                calorieGoal = BMR*1.375;
                break;

            case "moderately active":
                calorieGoal = BMR*1.55;
                break;

            case "very active":
                calorieGoal = BMR*1.725;
                break;

            case "extra active":
                calorieGoal = BMR*1.9;
                break;
        }
    }

    private void checkLifestyleAndSetCalorieGoalForMuscleGain() {

        lifestyle = lifestyle.toLowerCase();
        switch(lifestyle) {

            case "sedentary":
                calorieGoal = BMR*1.4;
                break;

            case "lightly active":
                calorieGoal = BMR*1.65;
                break;

            case "moderately active":
                calorieGoal = BMR*1.8;
                break;

            case "very active":
                calorieGoal = BMR*1.95;
                break;

            case "extra active":
                calorieGoal = BMR*2.15;
                break;
        }
    }

}
