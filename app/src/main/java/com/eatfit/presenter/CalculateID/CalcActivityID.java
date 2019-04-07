package com.eatfit.presenter.CalculateID;

public class CalcActivityID {
    String Activity;
    public CalcActivityID(String activity){
        this.Activity = activity;
    }

    public int returnActivityID(){
        if(Activity.toLowerCase().equals("sedentary")){
            return 1;
        }else if (Activity.toLowerCase().equals("lightly active")){
            return 2;

        }else if (Activity.toLowerCase().equals("moderately active")){
            return 3;

        }else if (Activity.toLowerCase().equals("very active")){
            return 4;
        }else{
            return 5;
        }
    }
}
