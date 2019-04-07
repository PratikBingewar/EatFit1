package com.eatfit.presenter.IntakeCalculator;

public class IntakeCalculatorClass {

    double breakFastIntake, lunchIntake, snackIntake, dinnerIntake;
    String intensityForBreakFast,intensityForLunch,intensityForSnack,intensityForDinner;
    String dietTypeForBreakfast, dietTypeForLunch, dietTypeForSnack, dietTypeForDinner;

    public IntakeCalculatorClass(){

    }
    public IntakeCalculatorClass(String intensityForBreakFast, String intensityForLunch, String intensityForSnack, String intensityForDinner,
                                 String dietTypeForBreakfast, String dietTypeForLunch, String dietTypeForSnack, String dietTypeForDinner) {

        this.intensityForBreakFast = intensityForBreakFast;
        this.intensityForLunch = intensityForLunch;
        this.intensityForSnack = intensityForSnack;
        this.intensityForDinner = intensityForDinner;

        this.dietTypeForBreakfast = dietTypeForBreakfast;
        this.dietTypeForLunch = dietTypeForLunch;
        this.dietTypeForSnack = dietTypeForSnack;
        this.dietTypeForDinner = dietTypeForDinner;
    }

    //return calorofic values of perticular foods
    public double calculateIntakeForBreakFast(){

        breakFastIntake = 500;
        return breakFastIntake;
    }

    public double calculateIntakeForLunch(){
        lunchIntake = 500;
        return lunchIntake;
    }

    public double calculateIntakeForSnack(){
        snackIntake = 500;
        return snackIntake;
    }

    public double calculateIntakeForDinner(){
        dinnerIntake = 500;
        return dinnerIntake;
    }
}
