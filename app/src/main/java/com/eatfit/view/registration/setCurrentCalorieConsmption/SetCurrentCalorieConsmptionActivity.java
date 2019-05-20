package com.eatfit.view.registration.setCurrentCalorieConsmption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.newUser.InsertUserInFoodTable;
import com.eatfit.model.registration.RegistrationModel;
import com.eatfit.presenter.BMICalculator.BMICalculator;
import com.eatfit.presenter.BMRCalc.BMRCalculator;
import com.eatfit.presenter.CalculateID.CalcActivityID;
import com.eatfit.presenter.CalculateID.CalcFitnessID;
import com.eatfit.presenter.CalculateID.CalcIntensityID;
import com.eatfit.presenter.CalorieCalculationToReachGoal.CalorieCalculationToReachGoalClass;
import com.eatfit.presenter.CurrentCalorieConsumptionCalcualtor.CurrentCalorieConsumptionCalculator;
import com.eatfit.presenter.IntakeCalculator.IntakeCalculatorClass;
import com.eatfit.presenter.TimeCalculator.TimeCalculatorClass;
import com.eatfit.presenter.User;
import com.eatfit.presenter.calculateIncrement.CalculateIncrement;
import com.eatfit.presenter.calorieGoalCalculator.CalorieGoalCalculator;
import com.eatfit.view.menu.MainMenuActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SetCurrentCalorieConsmptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener , Serializable {


    Spinner breakfastIntensity, lunchIntensity, snackIntensity, dinnerIntensity;
    Spinner breakfastDiet, lunchDiet, snackDiet, dinnerDiet;
    ImageButton breakfastImageButton, lunchImageButton, snackImageButton, dinnerImageButton;
    Button next;
    String dietIntensityForBreakfast="light", dietIntensityForLunch="light", dietIntensityForSnack="light", dietIntensityForDinner="light";
    String dietTypeForBreakfast="common", dietTypeForLunch="common", dietTypeForSnack="common", dietTypeForDinner="common";
    String dietDescForBreakfast, dietDescForLunch, dietDescForSNack, dietDescForDinner;
    Intent intent;
    User user;

    BMICalculator bmiCalculator;
    BMRCalculator bmrCalculator;
    CalculateIncrement calculateIncrement;
    TimeCalculatorClass timeCalculatorClass;
    CalorieGoalCalculator calorieGoalCalculator;
    CurrentCalorieConsumptionCalculator currentCalorieConsumptionCalculator;
    IntakeCalculatorClass intakeCalculatorClass;
    CalcActivityID calcActivityID;
    CalcFitnessID calcFitnessID;
    CalcIntensityID calcIntensityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_current_calorie_consmption);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        Log.d("User info","User info : "+user.name+" "+user.age+" "+user.getGender()+" "+user.username+" ");


        setIDs();
        setStringsToSpinners();
        setListeners();

    }

    public void setIDs() {

        breakfastIntensity = findViewById(R.id.breakfast_calorie_consumption);
        lunchIntensity = findViewById(R.id.lunch_calorie_consumption);
        snackIntensity = findViewById(R.id.snack_calorie_consumption);
        dinnerIntensity = findViewById(R.id.dinner_calorie_consumption);

        breakfastDiet = findViewById(R.id.breakfast_diet_type);
        lunchDiet = findViewById(R.id.lunch_diet_type);
        snackDiet = findViewById(R.id.snack_diet_type);
        dinnerDiet = findViewById(R.id.dinner_diet_type);

        breakfastImageButton = findViewById(R.id.breakfast_diet_info);
        lunchImageButton = findViewById(R.id.lunch_diet_info);
        snackImageButton = findViewById(R.id.snack_diet_info);
        dinnerImageButton = findViewById(R.id.dinner_diet_info);

        next = findViewById(R.id.btn_complete_profile_next);
    }

    public void setListeners() {
        breakfastIntensity.setOnItemSelectedListener(this);
        lunchIntensity.setOnItemSelectedListener(this);
        snackIntensity.setOnItemSelectedListener(this);
        dinnerIntensity.setOnItemSelectedListener(this);

        breakfastDiet.setOnItemSelectedListener(this);
        dinnerDiet.setOnItemSelectedListener(this);
        snackDiet.setOnItemSelectedListener(this);
        lunchDiet.setOnItemSelectedListener(this);

        breakfastImageButton.setOnClickListener(this);
        lunchImageButton.setOnClickListener(this);
        snackImageButton.setOnClickListener(this);
        dinnerImageButton.setOnClickListener(this);

        next.setOnClickListener(this);

    }


    private void setInfoToDinnerImageButton() {
        setDescForDinner(dietIntensityForDinner,dietTypeForDinner);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForDinner, Toast.LENGTH_LONG).show();
    }

    private void setDietDescForBreakfast(String dietTypeForBreakfast, String dietIntensityForBreakfast) {
        //for english diet
        if(dietTypeForBreakfast.toLowerCase().equals("english") && dietIntensityForBreakfast.toLowerCase().equals("light")) {
            dietDescForBreakfast = "The light english Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("english") && dietIntensityForBreakfast.toLowerCase().equals("medium")) {
            dietDescForBreakfast = "The medium english Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("english") && dietIntensityForBreakfast.toLowerCase().equals("heavy")) {
            dietDescForBreakfast = "The heavy english Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }


        //for south indian diet
        if(dietTypeForBreakfast.toLowerCase().equals("south indian") && dietIntensityForBreakfast.toLowerCase().equals("light")) {
            dietDescForBreakfast = "The light south indian Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("south indian") && dietIntensityForBreakfast.toLowerCase().equals("medium")) {
            dietDescForBreakfast = "The medium south indian Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("south indian") && dietIntensityForBreakfast.toLowerCase().equals("heavy")) {
            dietDescForBreakfast = "The heavy south indian Breakfast contains\n" +
                    "two cup tea/ coffee or milk\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }


        //for north indian diet
        if(dietTypeForBreakfast.toLowerCase().equals("north indian") && dietIntensityForBreakfast.toLowerCase().equals("light")) {
            dietDescForBreakfast = "The light north indian Breakfast contains\n" +
                    "One cup lassi or milk\n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("north indian") && dietIntensityForBreakfast.toLowerCase().equals("medium")) {
            dietDescForBreakfast = "The medium north indian Breakfast contains\n" +
                    "\\nOne cup lassi or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "\\nIt Contains 500 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("north indian") && dietIntensityForBreakfast.toLowerCase().equals("heavy")) {
            dietDescForBreakfast = "The heavy north indian Breakfast contains\n" +
                    "\\ntwo cup lassi or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "\\nIt Contains 700 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        //for maharashtrian diet
        if(dietTypeForBreakfast.toLowerCase().equals("maharashtrian") && dietIntensityForBreakfast.toLowerCase().equals("light")) {
            dietDescForBreakfast = "The light maharashtrian Breakfast contains\n" +
                    "\\nOne cup tea/ coffee or milk \n" +
                    "\\nIt Contains 300 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForBreakfast.toLowerCase().equals("maharashtrian") && dietIntensityForBreakfast.toLowerCase().equals("medium")) {
            dietDescForBreakfast = "The medium maharashtrian Breakfast contains\n" +
                    "\\ntwo cup tea/ coffee or milk \n" +
                    "\\nIt Contains 500 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForBreakfast.toLowerCase().equals("maharashtrian") && dietIntensityForBreakfast.toLowerCase().equals("heavy")) {
            dietDescForBreakfast = "The heavy common Breakfast contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }


        //for common diet
        if(dietTypeForBreakfast.toLowerCase().equals("common") && dietIntensityForBreakfast.toLowerCase().equals("light")) {
            dietDescForBreakfast = "The light common Breakfast contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForBreakfast.toLowerCase().equals("common") && dietIntensityForBreakfast.toLowerCase().equals("medium")) {
            dietDescForBreakfast = "The medium common Breakfast contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForBreakfast.toLowerCase().equals("common") && dietIntensityForBreakfast.toLowerCase().equals("heavy")) {
            dietDescForBreakfast = "The heavy common Breakfast contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    ",biscuits or 2 big cups of oats.\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }
    }

    private void setInfoToBreakFastImageButton() {
        setDietDescForBreakfast(dietTypeForBreakfast,dietIntensityForBreakfast);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForBreakfast, Toast.LENGTH_LONG).show();
    }

    private void setDescForDinner(String dietIntensityForDinner, String dietTypeForDinner) {

        //for english diet
        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("light")) {

            dietDescForDinner = "The light English Dinner contains\n" +
                    "One slice Canadian bacon,One large slice bread,One cup beans.\n" +
                    "\\nOne fried egg,One link sausage,\\n\n" +
                    "\\nIt Contains 500 calories, 40.4 g total fat,\n" +
                    "\\n19.4 g carbohydrates and 43.4 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = "The medium English Dinner contains\n" +
                    "two slice Canadian bacon,One large slice bread,One cup beans.\n" +
                    "\\nOne fried egg,One link sausage,\\n\n" +
                    "\\nIt Contains 700 calories, 40.4 g total fat,\n" +
                    "\\n19.4 g carbohydrates and 43.4 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = "The heavy English Dinner contains\n" +
                    "two slice Canadian bacon,two large slice bread,One cup beans.\n" +
                    "\\two fried egg,One link sausage,\\n\n" +
                    "\\nIt Contains 850 calories, 40.4 g total fat,\n" +
                    "\\n19.4 g carbohydrates and 43.4 g of protein.";
        }


        //for south indian diet
        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = "The light south indian Dinner contains\n" +
                    "\\n3 millet Idlis / 1 Dosa / 2 Medu Vada \\n\n" +
                    "1 small bowl sambar,and coconut chutney.\n" +
                    "\\nIt Contains 150 calories, 5 g total fat,\n" +
                    "\\n30 g carbohydrates and 15 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = "The medium south indian Dinner contains\n" +
                    "\\n8 millet Idlis / 3 Dosa / 5 Medu Vada / 2 uthappa of any type\\n\n" +
                    "2 small bowl sambar,and coconut chutney.\n" +
                    "\\nIt Contains 300 calories, 10 g total fat,\n" +
                    "\\n60 g carbohydrates and 30 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = "The heavy south indian Dinner contains\n" +
                    "\\n8 millet Idlis / 3 Dosa / 5 Medu Vada / 2 uthappa of any type\\n\n" +
                    "2 small bowl sambar,and coconut chutney.\n" +
                    "\\nIt Contains 475 calories, 17 g total fat,\n" +
                    "\\n90 g carbohydrates and 45 g of protein.";
        }


        //for north indian diet
        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = "The light North Indian Dinner contains\n" +
                    "nOne Aloo paratha,One cup curd,\n" +
                    "and 1 spoon butter.\n" +
                    "It Contains 350 calories, 30 g total fat,\n" +
                    "40 g carbohydrates and 20 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = "The medium North Indian Dinner contains\n" +
                    "One Aloo paratha,one cup curd,\n" +
                    "and one spoon butter and chai with buiscuits.\n" +
                    "nIt Contains 410 calories, 37 g total fat,\n" +
                    "n65 g carbohydrates and 26 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = "The heavy North Indian Dinner contains\n" +
                    "Two Aloo paratha,one cup curd,\n" +
                    "and one spoon butter and chai with buiscuits.\n" +
                    "It Contains 520 calories, 50 g total fat,\n" +
                    "70 g carbohydrates and 42 g of protein.";
        }

        //for maharashtrian diet
        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = "The light maharashtrian Dinner contains " +
                    "\none chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "\nIt Contains 500 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";
        }

        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = "The medium Maharashtrian Dinner contains\n" +
                    "\ntwo chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "It Contains 550 calories, 45 g total fat,\n" +
                    "59.4 g carbohydrates and 25.4 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = "The heavy Maharashtrian Dinner contains\n" +
                    "\nthree chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "It Contains 675 calories, 60 g total fat,\n" +
                    "71.8 g carbohydrates and 35.9 g of protein.";
        }


        //for common diet
        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = "The light common Dinner contains " +
                    "\none chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "It Contains 375 calories, 60 g total fat,\n" +
                    "71.8 g carbohydrates and 35.9 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = "The medium common Dinner contains " +
                    "\ntwo chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "It Contains 675 calories, 60 g total fat,\n" +
                    "71.8 g carbohydrates and 35.9 g of protein.";
        }

        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = "The heavy common Dinner contains " +
                    "\nthree chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "It Contains 800 calories, 60 g total fat,\n" +
                    "71.8 g carbohydrates and 35.9 g of protein.";
        }
    }

    private void setInfoToLunchImageButton() {
        setDescForLunch(dietTypeForLunch,dietIntensityForLunch);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForLunch, Toast.LENGTH_LONG).show();
    }

    private void setDescForLunch(String dietTypeForLunch, String dietIntensityForLunch) {
        //for english diet
        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = "The light english Lunch contains " +
                    "\nOne slice of bacon " +
                    "\n,buiscuits and one fried egg along with fried tomatoes." +
                    "\nIt Contains 300 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;
        }

        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = "The medium english Lunch contains " +
                    "\ntwo slice of bacon " +
                    "\n,buiscuits and three fried egg along with fried tomatoes." +
                    "\nIt Contains 500 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;
        }

        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = "The heavy english Lunch contains " +
                    "\ntwo slice of bacon " +
                    "\n,buiscuits and two fried egg along with fried tomatoes." +
                    "\nIt Contains 700 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;
        }


        //for south indian diet
        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = "The light south indian Lunch contains " +
                    "\none plate dosa or uthappa" +
                    "\n,along with sambar and chatni." +
                    "\nIt Contains 300 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;;
        }

        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = "The medium south indian Lunch contains " +
                    "\ntwo plate dosa or uthappa" +
                    "\n,along with sambar and chatni." +
                    "\nIt Contains 500 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;;;
        }

        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = "The heavy south indian Lunch contains " +
                    "\nthree plate dosa or uthappa" +
                    "\n,along with sambar and chatni." +
                    "\nIt Contains 700 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;;;
        }


        //for north indian diet
        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = "The light north indian Lunch contains " +
                    "\none paratha of any type" +
                    "\n,along with dahi or lassi." +
                    "\nIt Contains 300 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;;;;
        }

        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = "The medium north indian Lunch contains " +
                    "\ntwo paratha of any type" +
                    "\n,along with dahi or lassi." +
                    "\nIt Contains 500 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;;;;;
        }

        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = "The heavy north indian Lunch contains " +
                    "\nthree paratha of any type" +
                    "\n,along with dahi or lassi." +
                    "\nIt Contains 700 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;
        }

        //for maharashtrian diet
        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = "The light maharashtrian Lunch contains " +
                    "\none chapati " +
                    "\n,along with vegetable and salad" +
                    "\nIt Contains 300 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;;
        }

        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch =  "The medium maharashtrian Lunch contains " +
                    "\ntwo chapati " +
                    "\n,along with vegetable and rice and dal." +
                    "\nIt Contains 500 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;
        }

        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = "The heavy maharashtrian Lunch contains " +
                    "\ntwo chapati " +
                    "\n,along with vegetable and 2 cups of rice and dal." +
                    "\nIt Contains 700 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";;
        }


        //for common diet
        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = "The light Common Lunch contains " +
                    "\nOne chapati along with veggies" +
                    "\nIt Contains 300 calories, 20 g total fat,\n45 g carbohydrates and 10 g of protein";
        }

        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = "The medium Common Lunch contains\n" +
                    "\ntwo chapati along with veggies and rice and dal" +
                    "It Contains 400 calories, 29 g total fat,\n" +
                    "71 g carbohydrates and 22 g of protein.";
        }

        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = "The heavy Common Lunch contains\n" +
                    "The medium Common Lunch contains\n" +
                    "\nthree chapati along with veggies and rice and dal" +
                    "It Contains 500 calories, 40 g total fat,\n" +
                    "100 g carbohydrates and 35 g of protein.";
        }
    }

    private void setInfoTosnackImageButton() {
        setDescForSnack(dietTypeForSnack,dietIntensityForSnack);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForSNack, Toast.LENGTH_LONG).show();
    }

    private void setDescForSnack(String dietTypeForSnack, String dietIntensityForSnack) {
        //for english diet
        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = "The light english Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = "The medium english Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = "The heavy english Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }


        //for south indian diet
        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = "The light south indian Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = "The medium south indian Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = "The heavy south indian Snack contains\n" +
                    "two cup tea/ coffee or milk\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;;
        }


        //for north indian diet
        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = "The light north indian Snack contains\n" +
                    "One cup lassi or milk\n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";;
        }

        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = "The medium north indian Snack contains\n" +
                    "\\nOne cup lassi or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "\\nIt Contains 500 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = "The heavy north indian Snack contains\n" +
                    "\\ntwo cup lassi or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "\\nIt Contains 700 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        //for maharashtrian diet
        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = "The light maharashtrian Snack contains\n" +
                    "\\nOne cup tea/ coffee or milk \n" +
                    "\\nIt Contains 300 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";;;
        }

        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = "The medium maharashtrian Snack contains\n" +
                    "\\ntwo cup tea/ coffee or milk \n" +
                    "\\nIt Contains 500 calories, 20 g total fat,\n" +
                    "\\n45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = "The heavy common Snack contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }


        //for common diet
        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = "The light common Snack contains\n" +
                    "One cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 300 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = "The medium common Snack contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    ",buiscuits or big cup of oats.\n" +
                    "It Contains 500 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }

        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = "The heavy common Snack contains\n" +
                    "two cup tea/ coffee or milk \n" +
                    ",biscuits or 2 big cups of oats.\n" +
                    "It Contains 700 calories, 20 g total fat,\n" +
                    "45 g carbohydrates and 10 g of protein.";
        }
    }

    private void notifyUserToChooseOption() {
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, "choose one of the options!!", Toast.LENGTH_SHORT).show();
    }

    public void setStringsToSpinners() {
        ArrayAdapter<CharSequence> dietLevelArrayAdapter = ArrayAdapter.createFromResource(this, R.array.currnt_diet_intensity, android.R.layout.simple_spinner_item);
        dietLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfastIntensity.setAdapter(dietLevelArrayAdapter);
        lunchIntensity.setAdapter(dietLevelArrayAdapter);
        snackIntensity.setAdapter(dietLevelArrayAdapter);
        dinnerIntensity.setAdapter(dietLevelArrayAdapter);


        ArrayAdapter<CharSequence> dietTypeArrayAdapter = ArrayAdapter.createFromResource(this, R.array.current_diet_types, android.R.layout.simple_spinner_item);
        dietLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfastDiet.setAdapter(dietTypeArrayAdapter);
        lunchDiet.setAdapter(dietTypeArrayAdapter);
        snackDiet.setAdapter(dietTypeArrayAdapter);
        dinnerDiet.setAdapter(dietTypeArrayAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view == breakfastIntensity) {
            dietIntensityForBreakfast = parent.getItemAtPosition(position).toString();
        }

        if (view == lunchIntensity) {
            dietIntensityForLunch = parent.getItemAtPosition(position).toString();
        }

        if (view == snackIntensity) {
            dietIntensityForSnack = parent.getItemAtPosition(position).toString();
        }

        if (view == dinnerIntensity) {
            dietIntensityForDinner = parent.getItemAtPosition(position).toString();
        }

        if (view == breakfastDiet) {
            dietTypeForBreakfast = parent.getItemAtPosition(position).toString();
        }

        if (view == lunchDiet) {
            dietTypeForLunch = parent.getItemAtPosition(position).toString();
        }

        if (view == snackDiet) {
            dietTypeForSnack = parent.getItemAtPosition(position).toString();
        }

        if (view == dinnerDiet) {
            dietTypeForDinner = parent.getItemAtPosition(position).toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if (view == breakfastImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForBreakfast.isEmpty() || dietTypeForBreakfast.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToBreakFastImageButton();
            }
        }

        if (view == lunchImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForLunch.isEmpty() || dietTypeForLunch.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToLunchImageButton();
            }
        }

        if (view == snackImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietTypeForSnack.isEmpty() || dietTypeForSnack.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoTosnackImageButton();
            }

        }

        if (view == dinnerImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForDinner.isEmpty() || dietTypeForDinner.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToDinnerImageButton();
            }
        }
        if(view == next) {
            String name = user.getName();
            String username = user.getUsername();
            String password = user.getPassword();
            String gender = user.getGender();
            double weight = user.getWeight();
            double height = user.getHeight();
            Log.d("User info","gender: "+gender);

            bmiCalculator = new BMICalculator(height,weight);
            double BMI = bmiCalculator.calculateAndReturnBMI();
            Log.d("User info: ","BMI: "+BMI);

            double age = user.getAge();
            bmrCalculator = new BMRCalculator(height,weight,age,gender);
            double BMR = bmrCalculator.calculateBMR();
            Log.d("BMR: ","BMR: "+BMR);

            String lifeStyle = user.getActivityLevel();
            String fitnessGoal = user.getFitnessGoal();
            calorieGoalCalculator = new CalorieGoalCalculator(lifeStyle,BMR,fitnessGoal);
            double calorieGoal = calorieGoalCalculator.getCalorieGoal();

            intakeCalculatorClass = new IntakeCalculatorClass();
            double intakeForBreakFast = intakeCalculatorClass.calculateIntakeForBreakFast();
            double intakeForLunch = intakeCalculatorClass.calculateIntakeForLunch();
            double intakeForSnack = intakeCalculatorClass.calculateIntakeForSnack();
            double intakeForDinner = intakeCalculatorClass.calculateIntakeForDinner();

            currentCalorieConsumptionCalculator = new CurrentCalorieConsumptionCalculator(intakeForBreakFast,intakeForLunch,intakeForSnack,intakeForDinner);
            double totalCalories = currentCalorieConsumptionCalculator.calculateAndReturnTotalCalories();

            String intensity = user.getIntensity();

            timeCalculatorClass = new TimeCalculatorClass(gender,fitnessGoal,intensity);
            double timeToReachGoal = timeCalculatorClass.calcualteAndReturnCTime();
            timeToReachGoal = timeToReachGoal * 30;

            calculateIncrement = new CalculateIncrement(totalCalories,calorieGoal,intensity);
            double increment = calculateIncrement.calculateIncrement();

            calcActivityID = new CalcActivityID(user.getActivityLevel());
            int activityID = calcActivityID.returnActivityID();

            calcIntensityID = new CalcIntensityID(user.getIntensity());
            int intensityID = calcIntensityID.returnIntnsityID();

            calcFitnessID = new CalcFitnessID(user.getFitnessGoal());
            int fitnessID = calcFitnessID.returnFitnessID();

            Log.d("User object final: ",
                    name+" "+gender+" "+age+" "+weight+" "+height+" "
                            +username+" "+password+" "+BMR+" "+BMI+" "
                            +calorieGoal+" "+increment+" "+intakeForBreakFast+" "
                            +intakeForLunch+" "+intakeForSnack+" "+intakeForDinner+" "
                            +timeToReachGoal+" "+user.getIntensity()+" "+intensityID+" "
                            +user.getFitnessGoal()+" "+fitnessID+" "+user.getActivityLevel()+" "
                            +activityID+" "+this.getLocalClassName());

            RegistrationModel registerModel = new RegistrationModel(name,gender,age,weight,
                    height,username,password,BMR,BMI,calorieGoal,increment,
                    intakeForBreakFast,intakeForLunch,intakeForSnack,intakeForDinner,
                    timeToReachGoal,intensityID,fitnessID,activityID,this);
            registerModel.authenticate();
        }

    }

    public  void onSuccessfulRegistration(){

        InsertUserInFoodTable insertUserInFoodTable = new InsertUserInFoodTable(user.getUsername(),this);
        insertUserInFoodTable.setUserInfo();
    }

    public  void onError(String error){
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    public void onFailedRegistration(){
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
    }

    public  void goToMainMenu(){
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this,"Registration Successful !!",Toast.LENGTH_SHORT).show();
        intent = new Intent(SetCurrentCalorieConsmptionActivity.this, MainMenuActivity.class);
        intent.putExtra("username",user.getUsername());
        startActivity(intent);
        finish();
    }

    public void onFailedToAddInFoodTable(){
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this,"onFailed To Add In Food Table",Toast.LENGTH_SHORT).show();
    }
}
