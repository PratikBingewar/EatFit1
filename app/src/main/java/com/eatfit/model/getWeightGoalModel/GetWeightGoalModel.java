package com.eatfit.model.getWeightGoalModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.view.registration.setWeightGoal.SetWeightGoalActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetWeightGoalModel {
    public String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/update_weight.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    String userNAME;
    SetWeightGoalActivity setWeightGoalActivity;

    public GetWeightGoalModel(String username, SetWeightGoalActivity setWeightGoalActivity) {
        this.userNAME = username;
        this.setWeightGoalActivity = setWeightGoalActivity;
        requestQueue = Volley.newRequestQueue((Context) setWeightGoalActivity);
    }

    public  void  getUerInfo() {
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
                        Log.d("user data fetch: ","In volley error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",userNAME);
                Log.d("username change weight",userNAME);
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

            JSONObject root = new JSONObject(response);

            double weight = root.getDouble("weight");
            Log.d("weight in model:  ",weight+"");

            setWeightGoalActivity.afterSuccessfulWeightFetch(weight);

        }
        catch (Exception ex) {
            Log.d("Pratik","In exception "+ex);
            changeConnectionStatus(false);
            setWeightGoalActivity.onFailedToFetchCurentWeight();
            Log.d("fetch current weight",ex+"");
        }
    }
}
