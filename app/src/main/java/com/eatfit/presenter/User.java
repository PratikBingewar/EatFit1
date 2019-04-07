package com.eatfit.presenter;

import com.eatfit.presenter.BMRCalc.BMRCalculator;
import com.eatfit.presenter.calculateIncrement.CalculateIncrement;

import java.io.Serializable;

public class User implements Serializable {
    public  String name;
    public  String gender;
    public  String username;
    public  String password;
    public String intensity;
    public  String fitnessGoal;
    public  String activityLevel;
    public  double age,weight,height,BMI,BMR,calorieGoal,incrementInCalorieGoal,breakfastIntake,lunchIntake,snackIntake,dinnerIntake,timeToReachGoal;
    public  BMRCalculator bmrCalculator;
    public  double weightGoal;

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public BMRCalculator getBmrCalculator() {
        return bmrCalculator;
    }

    public void setBmrCalculator(BMRCalculator bmrCalculator) {
        this.bmrCalculator = bmrCalculator;
    }

    public CalculateIncrement getCalculateIncrement() {
        return calculateIncrement;
    }

    public void setCalculateIncrement(CalculateIncrement calculateIncrement) {
        this.calculateIncrement = calculateIncrement;
    }

    public User(){

    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public CalculateIncrement calculateIncrement;



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBMI() {
        bmrCalculator = new BMRCalculator(height,weight,age,gender);
        BMR = bmrCalculator.calculateBMR();
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public double getBMR() {
        return BMR;
    }

    public void setBMR(double BMR) {
        this.BMR = BMR;
    }

    public double getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public double getIncrementInCalorieGoal() {
        return incrementInCalorieGoal;
    }

    public void setIncrementInCalorieGoal(double incrementInCalorieGoal) {
        this.incrementInCalorieGoal = incrementInCalorieGoal;
    }

    public double getBreakfastIntake() {
        return breakfastIntake;
    }

    public void setBreakfastIntake(double breakfastIntake) {
        this.breakfastIntake = breakfastIntake;
    }

    public double getLunchIntake() {
        return lunchIntake;
    }

    public void setLunchIntake(double lunchIntake) {
        this.lunchIntake = lunchIntake;
    }

    public double getSnackIntake() {
        return snackIntake;
    }

    public void setSnackIntake(double snackIntake) {
        this.snackIntake = snackIntake;
    }

    public double getDinnerIntake() {
        return dinnerIntake;
    }

    public void setDinnerIntake(double dinnerIntake) {
        this.dinnerIntake = dinnerIntake;
    }

    public double getTimeToReachGoal() {
        return timeToReachGoal;
    }

    public void setTimeToReachGoal(double timeToReachGoal) {
        this.timeToReachGoal = timeToReachGoal;
    }

    public  void calculateBMI(){
        BMI = (Double) weight / ( (height*height) /100);
    }

}
