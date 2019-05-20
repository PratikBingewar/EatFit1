package com.eatfit.model.forgetPassword;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.presenter.forgetPassword.ForgetPaswordPresenter;
import com.eatfit.presenter.forgetPassword.IForgetPasswordPresenter;
import com.eatfit.view.forgorPassword.IForgotPasswordView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordModel implements IForgetPasswordModel{

    String username,goal;
    int goalID;
    IForgetPasswordPresenter iForgetPasswordPresenter;
    IForgotPasswordView iForgotPasswordView;
    public final String  FORGET_PASSWORD_URL = "https://eatfit223.000webhostapp.com/volley/forgotPassword.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;

    public ForgetPasswordModel(IForgotPasswordView forgotPasswordView, ForgetPaswordPresenter forgetPaswordPresenter, String username, String goal) {
        this.iForgetPasswordPresenter = forgetPaswordPresenter;
        this.iForgotPasswordView = forgotPasswordView;
        this.username = username;
        this.goal = goal;
        setGoalID();
        requestQueue = Volley.newRequestQueue((Context) forgotPasswordView);
    }

    private void setGoalID() {
        goalID = 1;
        if(goal.equals("weight loss")) {
            goalID = 1;
        }
        else if(goal.equals("muscle gain")) {
            goalID = 2;
        }
        else if(goal.equals("stay fit")) {
            goalID = 3;
        }
    }

    @Override
    public void authenticate() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, FORGET_PASSWORD_URL,
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
                param.put("goal",String.valueOf(goalID));
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
                iForgetPasswordPresenter.onValidUserInput();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                iForgetPasswordPresenter.onInvalidUser();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            iForgetPasswordPresenter.onConnectionFailed();
        }
    }
}
