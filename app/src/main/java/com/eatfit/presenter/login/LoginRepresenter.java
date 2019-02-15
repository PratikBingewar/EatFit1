package com.eatfit.presenter.login;

import android.util.Log;

import com.eatfit.model.Validator;
import com.eatfit.model.login.ILoginModel;
import com.eatfit.model.login.LoginModel;
import com.eatfit.view.login.ILoginView;

public class LoginRepresenter implements ILoginPresenter {

    ILoginView loginView;
    ILoginModel iLoginModel;
    Validator validator;

    public LoginRepresenter(ILoginView iLoginView){
        loginView = iLoginView;
    }

    @Override
    public void onLogin(String email, String password) {
        //craete validator object
        validator = new Validator(this);

        //validate email and password
        validate(email,password);
    }

    @Override
    public void onSuccessfulLogin() {
        Log.d("pratik","in onSuccessfulValidation of presenter");
        loginView.onSuccessfulLogin();
    }

    @Override
    public void onFailedLogin() {
        loginView.onFailedLogin();
    }

    @Override
    public void validate(String emailVal, String passwordVal) {
        validator = new Validator(this);
        validator.validate(emailVal,passwordVal);
    }

    //on sccuessful login go to model
    @Override
    public void onSuccessfulValidation(String email, String password) {
        Log.d("pratik","in onSuccessfulValidation of presenter");
        iLoginModel = new LoginModel(email,password,loginView,this);
        iLoginModel.authenticate();
    }

    @Override
    public void onPasswordError() {
        Log.d("pratik","in onPasswordError of presenter");
        loginView.onPsswordError();
    }

    @Override
    public void onEmailError() {
        Log.d("pratik","in onEmailError for presenter");
        loginView.onEmailError();
    }

}


