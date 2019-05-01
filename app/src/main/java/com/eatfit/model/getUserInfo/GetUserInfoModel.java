package com.eatfit.model.getUserInfo;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.presenter.User;
import com.eatfit.view.basicProfile.BasicProfileActivity;
import com.eatfit.view.menu.MainMenuActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetUserInfoModel {
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/getUsreInfo.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    MainMenuActivity mainMenuActivity;
    String username;
    BasicProfileActivity basicProfileActivity;
    public GetUserInfoModel(String mUser, MainMenuActivity mainMenuActivity) {
        this.username = mUser;
        this.mainMenuActivity = mainMenuActivity;
        requestQueue = Volley.newRequestQueue((Context) mainMenuActivity);
    }

    public GetUserInfoModel(String username, BasicProfileActivity basicProfileActivity) {
        this.username = username;
        this.basicProfileActivity = basicProfileActivity;
        requestQueue = Volley.newRequestQueue((Context) basicProfileActivity);
    }

    public  void  getUserObject() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Pratik","In volley error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",username);
                Log.d("username ",username);
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

//            JSONObject obj = new JSONObject(response);
//
//            int id = obj.getInt("user_id");
//            user.setID(id);
//            Log.d("id ",""+id);


            JSONObject obj = new JSONObject(response);

            User user = new User();

            Log.d("id ",obj.getString("user_id"));

            String name=obj.getString("name");
            user.setName(name);
            double age=obj.getDouble("age");
            user.setAge(age);
            String gender=obj.getString("gender");
            user.setGender(gender);
            String password = obj.getString("password");
            user.setPassword(password);
            double weight=obj.getDouble("weight");
            user.setWeight(weight);
            double height=obj.getDouble("height");
            user.setHeight(height);
            double BMI=obj.getDouble("BMI");
            user.setBMI(BMI);
            double BMR=obj.getDouble("BMR");
            user.setBMR(BMR);
            double calorie_goal=obj.getDouble("calorie_goal");
            user.setCalorieGoal(calorie_goal);
            double increment=obj.getDouble("increment");
            user.setIncrementInCalorieGoal(increment);
            double breakfast_intake=obj.getDouble("breakfast_intake");
            user.setBreakfastIntake(breakfast_intake);
            double lunch_intake=obj.getDouble("lunch_intake");
            user.setLunchIntake(lunch_intake);
            double snack_intake=obj.getDouble("snack_intake");
            user.setSnackIntake(snack_intake);
            double dinner_intake=obj.getDouble("dinner_intake");
            user.setDinnerIntake(dinner_intake);
            double time_to_reach_goal=obj.getDouble("time_to_reach_goal");
            user.setTimeToReachGoal(time_to_reach_goal);
            int intensity_id=obj.getInt("intensity_id");
            user.setIntensityID(intensity_id);
            int fitness_goal_id=obj.getInt("fitness_goal_id");
            user.setFitnessGoalID(fitness_goal_id);
            int activity_id=obj.getInt("activity_id");
            user.setActivityID(activity_id);

            Log.d("RESPONSE ",name+" "+gender+" "+age+" "+height+" "+weight+" "+BMI+" "+BMR+
                    " "+calorie_goal+" "+increment+" "+breakfast_intake+" "+lunch_intake+" "+snack_intake+
                    " "+dinner_intake+
                    " "+time_to_reach_goal+" "+intensity_id+" "+fitness_goal_id+" "+activity_id);

            if(mainMenuActivity != null) {
                mainMenuActivity.onWelComeToast(user);
            }
            else if(basicProfileActivity != null) {
                basicProfileActivity.onSuccessfulUserObject(user);
            }

        }
        catch (Exception ex) {
            Log.d("Pratik","In exception "+ex);
            changeConnectionStatus(false);
        }
    }
}
