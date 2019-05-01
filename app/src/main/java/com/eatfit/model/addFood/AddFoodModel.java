package com.eatfit.model.addFood;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.view.addFood.AddFoodActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddFoodModel {
    String username,foodName;
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/appendFoodList.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    AddFoodActivity addFoodActivity;
    double totalCalVal;

    public AddFoodModel(String username, String foodName, double totalCalVal, AddFoodActivity addFoodActivity) {

        this.username = username;
        this.foodName = foodName;
        this.totalCalVal = totalCalVal;
        this.addFoodActivity = addFoodActivity;
        requestQueue = Volley.newRequestQueue((Context) addFoodActivity);
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
                param.put("foodname",foodName);
                param.put("calorie_consumption",String.valueOf(totalCalVal));
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
                addFoodActivity.onSuccessfulAddition();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                addFoodActivity.onFailedAddition();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
        }
    }
}
