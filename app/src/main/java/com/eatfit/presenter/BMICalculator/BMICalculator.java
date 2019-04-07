package com.eatfit.presenter.BMICalculator;

public class BMICalculator {
    double height,weight;
    double BMI;
    public BMICalculator(double height, double weight){
        this.height = height;
        this.weight = weight;
    }

    public double calculateAndReturnBMI(){
        BMI = weight / (height*height);
        return BMI;
    }
}
