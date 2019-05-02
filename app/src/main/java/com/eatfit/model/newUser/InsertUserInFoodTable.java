package com.eatfit.model.newUser;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.view.login.LoginActivity;
import com.eatfit.view.menu.MainMenuActivity;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertUserInFoodTable {
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/addUserInFoodTable.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    String userNAME;
    SetCurrentCalorieConsmptionActivity setCurrentCalorieConsmptionActivity;

    public InsertUserInFoodTable(String username, SetCurrentCalorieConsmptionActivity setCurrentCalorieConsmptionActivity) {
        this.userNAME = username;
        this.setCurrentCalorieConsmptionActivity = setCurrentCalorieConsmptionActivity;
        requestQueue = Volley.newRequestQueue((Context) setCurrentCalorieConsmptionActivity);
    }

    public  void  setUserInfo() {
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
                Log.d("username ",userNAME);
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

                String[] foods = {""};
                setCurrentCalorieConsmptionActivity.goToMainMenu();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                setCurrentCalorieConsmptionActivity.onFailedToAddInFoodTable();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            setCurrentCalorieConsmptionActivity.onFailedRegistration();
        }
    }
}
