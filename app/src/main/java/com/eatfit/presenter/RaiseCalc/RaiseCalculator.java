package com.eatfit.presenter.RaiseCalc;

public class RaiseCalculator {
    double time;
    double timeInDays;
    double raise;
    double diifereence;
    double currntCalorieIntake, caloriesGoalToReach;
    int flagToIncreaseConsumption;

    RaiseCalculator(double currntCalorieIntake, double caloriesGoalToReach, double time) {
        this.currntCalorieIntake = currntCalorieIntake;
        this.caloriesGoalToReach = caloriesGoalToReach;
        //calculating difference
        diifereence = caloriesGoalToReach - currntCalorieIntake;

        this.time = time;
        //calculating time in days
        timeInDays = time*30;

    }

    public  double calculateAndReturnRaise() {
        if(diifereence < 0) {
            raise = diifereence*(-1) / timeInDays;
            //flag to indicate consume less each day
            flagToIncreaseConsumption = -1;
        }
        else if(diifereence == 0) {
            raise = 0;
            //flag to indicate consume less each day
            flagToIncreaseConsumption = 0;
        }
        else {
            raise = diifereence / timeInDays;
            //flag to indicate consume more each day
            flagToIncreaseConsumption = 1;
        }
        return raise;
    }

    public int consumeMoreOrNot() {
        if (flagToIncreaseConsumption == 1)
            return 1;
        else if(flagToIncreaseConsumption == -1) {
            return -1;
        }else{
            return 0;
        }
    }
}
