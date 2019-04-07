package com.eatfit.presenter.CalorieCalculationToReachGoal;

public class CalorieCalculationToReachGoalClass {
    double BMR;
    String goal,lifestyle;
    double calorieGoal;
    CalorieCalculationToReachGoalClass(double BMR, String goal, String lifestyle){
        this.BMR = BMR;
        this.goal = goal;
        this.lifestyle = lifestyle;
    }

    public double calcualteAndReturnCalorieGoal(){

        if(goal.toLowerCase().equals("weight loss")){
            calcualteGoalForWeightLoss(lifestyle);
        }
        else if(goal.toLowerCase().equals("muscle gain")){
            calcualteGoalForMuscleGain(lifestyle);
        }
        else if(goal.toLowerCase().equals("stay fit")){
            calcualteGoalForStayFit(lifestyle);
        }
        return calorieGoal;
    }

    private void calcualteGoalForStayFit(String lifestyle) {
        if(lifestyle.equals("Sedentary")){
            calorieGoal = BMR * 1.4 ;
        }else if (lifestyle.equals("Lightly Active")){
            calorieGoal = BMR * 1.65  ;
        }else if (lifestyle.equals("Moderately Active")){
            calorieGoal = BMR *   1.8  ;
        }else if (lifestyle.equals("Very Active")){
            calorieGoal = BMR *   1.95  ;
        }else{
            calorieGoal = BMR * 2.15;
        }
    }

    private void calcualteGoalForMuscleGain(String lifestyle) {
        if(lifestyle.equals("Sedentary")){
            calorieGoal = BMR * 1.4 ;
        }else if (lifestyle.equals("Lightly Active")){
            calorieGoal = BMR * 1.65  ;
        }else if (lifestyle.equals("Moderately Active")){
            calorieGoal = BMR *   1.8  ;
        }else if (lifestyle.equals("Very Active")){
            calorieGoal = BMR *   1.95  ;
        }else{
            calorieGoal = BMR * 2.15;
        }
    }

    private void calcualteGoalForWeightLoss(String lifestyle) {

        if(lifestyle.equals("Sedentary")){
            calorieGoal = BMR * 1.2;
        }else if (lifestyle.equals("Lightly Active")){
            calorieGoal = BMR * 1.375 ;
        }else if (lifestyle.equals("Moderately Active")){
            calorieGoal = BMR *  1.55 ;
        }else if (lifestyle.equals("Very Active")){
            calorieGoal = BMR *  1.725 ;
        }else{
            calorieGoal = BMR * 1.9 ;
        }
    }

}
