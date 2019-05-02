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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewUserDataCreation {
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/createNewUserInFoodTable.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    LoginActivity loginActivity;
    String userNAME;
    MainMenuActivity mainMenuActivity;

    public NewUserDataCreation(String username, MainMenuActivity mainMenuActivity) {
        this.userNAME = username;
        this.mainMenuActivity = mainMenuActivity;
        requestQueue = Volley.newRequestQueue((Context) mainMenuActivity);
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
                mainMenuActivity.setUserInfo(foods,0);
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                mainMenuActivity.onfailedInternetConnection();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
        }
    }
}
