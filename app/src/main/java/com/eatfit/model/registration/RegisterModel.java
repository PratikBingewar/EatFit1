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
import com.eatfit.model.URLs;
import com.eatfit.presenter.registration.IRegisterPresenter;
import com.eatfit.presenter.registration.RegisterPresenter;
import com.eatfit.view.registration.IRegisterView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel implements IRegisterModel {

    String email,password,name,gender;
    int age;
    IRegisterView iRegisterView;
    IRegisterPresenter registerPresenter;
    public final String  INSERT_URL = "http://eatfit223.000webhostapp.com/volley/insert.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;

    public RegisterModel(String name, String gender, int age, String email, String password, IRegisterView registerView, RegisterPresenter registerPresenter) {
        this.name =name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.registerPresenter = registerPresenter;
        this.iRegisterView = registerView;
        requestQueue = Volley.newRequestQueue((Context) iRegisterView);
    }

    @Override
    public void authenticate() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, INSERT_URL,
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
                param.put("name",name);
                param.put("gender",gender);
                param.put("age", Integer.toString(age));
                param.put("email",email);
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
                registerPresenter.onSuccessfulRegistration();
            } else if (res.equals("N")) {
                Log.d("Pratik","In No of Model error");
                changeConnectionStatus(false);
                registerPresenter.onFailedRegistration();
            } else if(res.equals("E")) {
                Log.d("Pratik","In user allready exist");
                changeConnectionStatus(true);
                registerPresenter.userAlreadyExists();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception ");
            changeConnectionStatus(false);
            Log.d("pratik",ex.toString());
        }
    }
}
