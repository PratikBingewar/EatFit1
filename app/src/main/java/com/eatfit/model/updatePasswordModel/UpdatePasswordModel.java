package com.eatfit.model.updatePasswordModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.view.basicProfile.UpdatePassword;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordModel {
    String password,username;
    UpdatePassword updatePassword;
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/updateName.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;

    public UpdatePasswordModel(String username, String password, UpdatePassword updatePassword) {
        this.password = password;
        this.username = username;
        this.updatePassword = updatePassword;
        requestQueue = Volley.newRequestQueue((Context) updatePassword);
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
                param.put("password",password);
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
                updatePassword.onSuccessfulUpdate();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                updatePassword.onunSuccessfulUpdate();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            updatePassword.onError(ex.toString());
        }
    }
}
