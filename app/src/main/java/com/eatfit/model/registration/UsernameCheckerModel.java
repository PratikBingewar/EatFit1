package com.eatfit.model.registration;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.presenter.registration.basicRegistration.IRegisterPresenter;
import com.eatfit.presenter.registration.basicRegistration.RegisterPresenter;
import com.eatfit.view.registration.basicRegistration.IRegisterView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsernameCheckerModel implements IRegisterModel {

    String username;
    IRegisterView iRegisterView;
    IRegisterPresenter registerPresenter;
    public final String  CHECK_USER_URL = "https://eatfit223.000webhostapp.com/volley/checkUsernameExistsOrNot.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;

    public UsernameCheckerModel(String username, IRegisterView registerView, RegisterPresenter registerPresenter) {
        this.username = username;
        this.registerPresenter = registerPresenter;
        this.iRegisterView = registerView;
        requestQueue = Volley.newRequestQueue((Context) iRegisterView);
    }

    @Override
    public void authenticate() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, CHECK_USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Pratik","In No of Model error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",username);
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
                registerPresenter.onValidUsername();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                registerPresenter.userAlreadyExists();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            Log.d("pratik",ex.toString());
            registerPresenter.onFailedConnection();
        }
    }
}
