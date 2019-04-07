package com.eatfit.presenter.CalculateID;

public class CalcIntensityID {
    String intensity;
    public CalcIntensityID(String intensity){
        this.intensity = intensity;
    }

    public int returnIntnsityID(){
        if(intensity.toLowerCase().equals("easy")){
            return 1;
        }
        else if(intensity.toLowerCase().equals("medium")){
            return 2;
        }
        else{
            return 3;
        }
    }
}
