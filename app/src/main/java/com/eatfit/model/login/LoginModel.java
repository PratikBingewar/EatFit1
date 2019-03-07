package com.eatfit.model.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.model.URLs;
import com.eatfit.presenter.login.ILoginPresenter;
import com.eatfit.presenter.login.LoginRepresenter;
import com.eatfit.view.login.ILoginView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements ILoginModel {

    String username,password;
    public final String  LOGIN_URL = "http://eatfit223.000webhostapp.com/volley/login.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    ILoginPresenter loginRepresenter;

    public LoginModel(String username, String password, ILoginView iLoginView, LoginRepresenter mLoginRepresenter) {
        this.username = username;
        this.password = password;
        this.loginRepresenter = mLoginRepresenter;
        requestQueue = Volley.newRequestQueue((Context) iLoginView);
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
                loginRepresenter.onSuccessfulLogin();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                loginRepresenter.onFailedLogin();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
        }
    }
}
