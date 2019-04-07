package com.eatfit.presenter.registration.setCurrentCalorieConsmption;

import com.eatfit.presenter.BMRCalc.BMRCalculator;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;

public class SetCurrentCalorieConsmptionPresenter {

    SetCurrentCalorieConsmptionActivity setCurrentCalorieConsmptionActivity;
    BMRCalculator bmrCalculator;
    double height,weight,age;
    String gender;
    double BMI;
    SetCurrentCalorieConsmptionPresenter(){
        bmrCalculator = new BMRCalculator(height,weight,age,gender);
        BMI = bmrCalculator.calculateBMR();
    }

    public  double calculateAndReturnBMI(){
        return BMI;
    }
}
