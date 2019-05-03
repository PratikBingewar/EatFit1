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
        this.BMR = checkSigns(this.BMR);
        this.BMI = bmi;
        this.BMI = Math.round(this.BMI * 100D) / 100D;
        Log.d("rounded BMI",""+this.BMI);
        this.BMI = checkSigns(this.BMI);
        this.calorieGoal = calorieGoal;
        this.calorieGoal = checkSigns(this.calorieGoal);
        this.increment = increment;
        this.increment = checkSigns(this.increment);
        this.intakeForBreakFast = intakeForBreakFast;
        this.intakeForBreakFast = checkSigns(this.intakeForBreakFast);
        this.intakeForSnack = intakeForSnack;
        this.intakeForSnack = checkSigns(this.intakeForSnack);
        this.intakeForLunch = intakeForLunch;
        this.intakeForLunch = checkSigns(this.intakeForLunch);
        this.intakeForDinner = intakeForDinner;
        this.intakeForDinner = checkSigns(this.intakeForDinner);
        this.timeToReachGoal = timeToReachGoal;
        this.timeToReachGoal = checkSigns(this.timeToReachGoal);

        this.intensityID = intensityID;
        this.fitnessID = fitnessID;
        this.activityID =activityID;

        this.setCurrentCalorieConsmptionActivity = setCurrentCalorieConsmptionActivity;

        requestQueue = Volley.newRequestQueue((Context) setCurrentCalorieConsmptionActivity);

    }

    private double checkSigns(double val) {
        if(val < 0) {
            val = val* (-1);
        }
        return val;
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
                param.put("age", Double.toString(age));
                param.put("weight", Double.toString(weight));
                param.put("height", Double.toString(height));
                param.put("username",username);
                param.put("password",password);
                param.put("BMI", Double.toString(BMI));
                param.put("BMR", Double.toString(BMR));
                calorieGoal = 1000+BMR;
                param.put("calorie_goal", Double.toString(calorieGoal));
                param.put("increment", Double.toString(100));
                param.put("breakfast_intake", Double.toString(calorieGoal / 4));
                param.put("lunch_intake", Double.toString(calorieGoal / 4));
                param.put("snack_intake", Double.toString(calorieGoal / 4));
                param.put("dinner_intake", Double.toString(calorieGoal / 4));
                param.put("time_to_reach_goal", Double.toString(150));
                param.put("intensity_id", Integer.toString(intensityID));
                param.put("fitness_goal_id", Integer.toString(fitnessID));
                param.put("activity_id", Integer.toString(activityID));
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
            String res = obj.getString("res");

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
            Log.d("pratik",""+ex.getLocalizedMessage()+ex.fillInStackTrace()+" "
                    +ex.getCause());
            ex.printStackTrace();
            setCurrentCalorieConsmptionActivity.onError(ex.toString());
        }
    }
}
