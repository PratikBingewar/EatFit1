package com.eatfit.presenter.BMRCalc;

import android.util.Log;

public class BMRCalculator {
    Double height, weight, age;
    String gender;

    public BMRCalculator(Double height, Double weight, Double age, String gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public double calculateBMR(){
        double BMR = 0;
        if (gender.equals("male")) {
            BMR = calculateBMRForMale();
            Log.d("BMR ",BMR+"");
        }else if(gender.equals("female")){
            BMR = calculateBMRForFemale();
            Log.d("BMR ",BMR+"");
        }
        return BMR;
    }

    //Mifflin-St Jeor Equation:
    private double calculateBMRForFemale() {
        return 10*weight + 6.25*height - 5*age + 5;
    }

    //Mifflin-St Jeor Equation:
    private double calculateBMRForMale() {
        return 10*weight + 6.25*height - 5*age - 161;
    }
}
