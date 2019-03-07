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
import com.eatfit.presenter.forgetPassword.EnterPasswordPresenter;
import com.eatfit.presenter.forgetPassword.IEnterNewPasswordPresenter;
import com.eatfit.view.forgorPassword.EnterNewPasswordActivity;
import com.eatfit.view.forgorPassword.ForgotPasswordActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EnterNewPasswordModel implements IEnterNewPasswordModel{
    String username,password;
    EnterNewPasswordActivity enterNewPasswordActivity;
    IEnterNewPasswordPresenter enterNewPasswordPresenter;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    public static final String ENTER_NEW_PASSWORD_URL = "https://eatfit223.000webhostapp.com/volley/NewPassword.php";


    public EnterNewPasswordModel(String username, String password, EnterNewPasswordActivity enterNewPasswordView, EnterPasswordPresenter enterPasswordPresenter) {
        this.username = username;
        this.password = password;
        this.enterNewPasswordActivity = enterNewPasswordView;
        this.enterNewPasswordPresenter = enterPasswordPresenter;
        requestQueue = Volley.newRequestQueue((Context) enterNewPasswordActivity);
    }

    @Override
    public void validate() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, ENTER_NEW_PASSWORD_URL,
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
                enterNewPasswordPresenter.onUnsuccessfulUpdate();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                enterNewPasswordPresenter.onUnsuccessfulUpdate();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            enterNewPasswordPresenter.onConnectionFailed();
        }
    }
}
