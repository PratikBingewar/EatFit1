package com.eatfit.presenter;

import java.io.Serializable;

public class User implements Serializable {
    public static String name;
    public static String gender;
    public static String username;
    public static String password;
    public static String fitnessGoal;
    public static String activityLevel;
    public static double age,weight,height,BMI,BMR,calorieGoal,incrementInCalorieGoal,breakfastIntake,lunchIntake,snackIntake,dinnerIntake,timeToReachGoal;
    public User(){

    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        User.gender = gender;
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
}
