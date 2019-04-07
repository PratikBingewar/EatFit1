package com.eatfit.presenter.CalculateID;

public class CalcFitnessID {
    String fitnessGoal;

    public CalcFitnessID(String fitnessGoal){
        this.fitnessGoal = fitnessGoal;
    }

    public int returnFitnessID(){
        if(fitnessGoal.toLowerCase().equals("weight loss")){
            return 1;
        }
        else if(fitnessGoal.toLowerCase().equals("muscle gain")){
            return 2;
        }
        else{
            return 3;
        }
    }
}
