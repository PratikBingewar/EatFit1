package com.eatfit.model.changeFitnessGoal;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.view.changeFitnessGoal.ChangeFitnessGoalActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangeFitnessGoalModel {

    String username;
    int goalID;
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/update_new_fitness_goal.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    ChangeFitnessGoalActivity changeFitnessGoalActivity;

    public ChangeFitnessGoalModel(String username, int goalID, ChangeFitnessGoalActivity changeFitnessGoalActivity) {
        this.username = username;
        this.goalID = goalID;
        this.changeFitnessGoalActivity = changeFitnessGoalActivity;
        requestQueue = Volley.newRequestQueue((Context) changeFitnessGoalActivity);
    }

    public  void  authenticate() {
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
                param.put("fitness_goal_id",String.valueOf(goalID));
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
                changeFitnessGoalActivity.onSuccessfulUpdate();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                changeFitnessGoalActivity.onFailedUpdate();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            changeFitnessGoalActivity.onFailedUpdate();
        }
    }
}
