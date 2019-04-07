package com.eatfit.presenter.TimeCalculator;

public class TimeCalculatorClass {
    String gender;
    String goal;
    String intensity;

    double time;

    public TimeCalculatorClass(String gender, String goal, String intensity) {
        this.gender = gender;
        this.goal = goal;
        this.intensity = intensity;
    }

    public double calcualteAndReturnCTime(){

        if(goal.toLowerCase().equals("weight loss")){
            calcualteGoalForWeightLoss(intensity,gender);
        }
        else if(goal.toLowerCase().equals("muscle gain")){
            calcualteGoalForMuscleGain(intensity,gender);
        }
        else if(goal.toLowerCase().equals("stay fit")){
            calcualteGoalForStayFit(intensity,gender);
        }
        return time;
    }




    private void calcualteGoalForStayFit(String intensity, String gender) {
        if(intensity.toLowerCase().equals("easy")){
            calcualteGoalForStayFitAndEasyIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("medium")){
            calcualteGoalForStayFitAndMediumIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("hard")){
            calcualteGoalForStayFitAndHardIntensity(gender);
        }
    }

    private void calcualteGoalForStayFitAndHardIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 1 ;
        }
        else {
            time = 1 ;
        }
    }

    private void calcualteGoalForStayFitAndMediumIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 2;
        }
        else {
            time = 2;
        }
    }

    private void calcualteGoalForStayFitAndEasyIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 3 ;
        }
        else {
            time = 3;
        }
    }






    private void calcualteGoalForMuscleGain(String intensity, String gender) {
        if(intensity.toLowerCase().equals("easy")){
            calcualteGoalForMuscleGainAndEasyIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("medium")){
            calcualteGoalForMuscleGainAndMedunIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("hard")){
            calcualteGoalForMuscleGainAndHardIntensity(gender);
        }
    }

    private void calcualteGoalForMuscleGainAndHardIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 2 ;
        }
        else {
            time = 1.75 ;
        }
    }

    private void calcualteGoalForMuscleGainAndEasyIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 1 ;
        }
        else {
            time = 1 ;
        }
    }

    private void calcualteGoalForMuscleGainAndMedunIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 1.5 ;
        }
        else {
            time = 1.25  ;
        }
    }





    private void calcualteGoalForWeightLoss(String intensity,String gender) {
        if(intensity.toLowerCase().equals("easy")){
            calcualteGoalForWeightLossAndEasyIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("medium")){
            calcualteGoalForWeightLossAndMedunIntensity(gender);
        }
        else if(intensity.toLowerCase().equals("hard")){
            calcualteGoalForWeightLossAndHardIntensity(gender);
        }
    }

    private void calcualteGoalForWeightLossAndHardIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 2.5 ;
        }
        else {
            time = 2.5 ;
        }
    }

    private void calcualteGoalForWeightLossAndMedunIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 2.5 ;
        }
        else {
            time = 2.5 ;
        }
    }

    private void calcualteGoalForWeightLossAndEasyIntensity(String gender) {
        if (gender.toLowerCase().equals("male")) {
            time = 4;
        }
        else {
            time = 3.5;
        }
    }




}
