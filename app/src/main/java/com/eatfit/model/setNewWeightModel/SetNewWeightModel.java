package com.eatfit.model.setNewWeightModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.presenter.login.ILoginPresenter;
import com.eatfit.presenter.login.LoginRepresenter;
import com.eatfit.view.changeWeight.ChangeWeightActivity;
import com.eatfit.view.login.ILoginView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SetNewWeightModel {

    String username;
    Double weight;
    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/update_new_weight.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    ChangeWeightActivity changeWeightActivity;

    public SetNewWeightModel(String username, double weight, ChangeWeightActivity changeWeightActivity) {
        this.username = username;
        this.weight = weight;
        this.changeWeightActivity = changeWeightActivity;
        requestQueue = Volley.newRequestQueue((Context) changeWeightActivity);
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
                        Log.d("Pratik","Set new weight: In volley error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",username);
                param.put("weight",String.valueOf(weight));
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
                changeWeightActivity.onSuccessfulUpdate();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                changeWeightActivity.onFailedToFetchCurentWeight();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
        }
    }
}
