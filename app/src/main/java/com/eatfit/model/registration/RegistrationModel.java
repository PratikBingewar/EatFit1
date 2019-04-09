package com.eatfit.model.registration;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.presenter.registration.basicRegistration.IRegisterPresenter;
import com.eatfit.presenter.registration.basicRegistration.RegisterPresenter;
import com.eatfit.view.registration.basicRegistration.IRegisterView;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationModel implements IRegisterModel {

    SetCurrentCalorieConsmptionActivity setCurrentCalorieConsmptionActivity;
    IRegisterPresenter registerPresenter;
    public final String  CHECK_USER_URL = "https://eatfit223.000webhostapp.com/volley/register.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    String name, gender, username, password;

    double age, weight, height, BMR, BMI, calorieGoal, increment,
            intakeForBreakFast,intakeForLunch, intakeForSnack,intakeForDinner,timeToReachGoal;

    int fitnessID,activityID,intensityID;

    public RegistrationModel(String name, String gender, double age, double weight, double height,
                             String username, String password, double bmr, double bmi,
                             double calorieGoal, double increment, double intakeForBreakFast,
                             double intakeForLunch, double intakeForSnack, double intakeForDinner,
                             double timeToReachGoal, int intensityID, int fitnessID, int activityID,
                             SetCurrentCalorieConsmptionActivity setCurrentCalorieConsmptionActivity) {

        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.password = password;
        this.BMR = bmr;
        this.BMI = bmi;
        this.calorieGoal = calorieGoal;
        this.increment = increment;
        this.intakeForBreakFast = intakeForBreakFast;
        this.intakeForSnack = intakeForSnack;
        this.intakeForLunch = intakeForLunch;
        this.intakeForDinner = intakeForDinner;
        this.timeToReachGoal = timeToReachGoal;

        this.intensityID = intensityID;
        this.fitnessID = fitnessID;
        this.activityID =activityID;

        this.setCurrentCalorieConsmptionActivity = setCurrentCalorieConsmptionActivity;

        requestQueue = Volley.newRequestQueue((Context) setCurrentCalorieConsmptionActivity);

    }

    @Override
    public void authenticate() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, CHECK_USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Pratik","In No of Model error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("name",name);
                param.put("gender",gender);
                param.put("age", String.valueOf(age));
                param.put("weight", String.valueOf(weight));
                param.put("height", String.valueOf(height));
                param.put("username",username);
                param.put("password",password);
                param.put("BMI", String.valueOf(BMI));
                param.put("BMR", String.valueOf(BMR));
                param.put("calorie_goal", String.valueOf(calorieGoal));
                param.put("increment", String.valueOf(increment));
                param.put("breakfast_intake", String.valueOf(intakeForBreakFast));
                param.put("lunch_intake", String.valueOf(intakeForLunch));
                param.put("snack_intake", String.valueOf(intakeForSnack));
                param.put("dinner_intake", String.valueOf(intakeForDinner));
                param.put("time_to_reach_goal", String.valueOf(timeToReachGoal));
                param.put("intensity_id", String.valueOf(intensityID));
                param.put("fitness_goal_id", String.valueOf(fitnessID));
                param.put("activity_id", String.valueOf(activityID));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void changeConnectionStatus(boolean val) {
        CONNECTION_STATUS = val;
    }

    public void checkResponse(String response){
        try {
            JSONObject obj = new JSONObject(response);
            String res=obj.getString("res");

            if (res.equals("Y")) {
                Log.d("Pratik","In yes of Model error");
                changeConnectionStatus(true);
                setCurrentCalorieConsmptionActivity.onSuccessfulRegistration();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                setCurrentCalorieConsmptionActivity.onFailedRegistration();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            Log.d("pratik",ex.toString());
            setCurrentCalorieConsmptionActivity.onError(ex.toString());
        }
    }
}
