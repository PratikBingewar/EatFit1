package com.eatfit.model;

import android.util.Patterns;

import com.eatfit.presenter.login.ILoginPresenter;
import com.eatfit.presenter.registration.basicRegistration.IRegisterPresenter;

import java.util.regex.Pattern;

public class Validator {
    IRegisterPresenter iRegisterPresenter;
    ILoginPresenter iLoginPresenter;

    public Validator(){

    }
    public Validator(ILoginPresenter iLoginPresenter) {
        this.iLoginPresenter = iLoginPresenter;
    }

    public Validator(IRegisterPresenter iRegisterPresenter) {
        this.iRegisterPresenter = iRegisterPresenter;
    }

    public boolean validateEmail(String email) {

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return true;
        }
        return false;
    }

    public boolean validatePassword(String password) {

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            return false;
        }
        return true;
    }

    public boolean validateName(String name) {
        if (!(Pattern.matches("^[\\p{L} .'-]+$", name))) {
            return false;
        }
        return true;
    }

    public boolean validateAge(int age) {
        if (age <= 10 || age >= 100) {
            return false;
        }
        return true;
    }


    public void validate(String name, String gender,int age, String email, String password) {
        if (validateName(name)) {
            //check for age validity
            if (validateAge(age)) {
                //check for email validity
                if (validateEmail(email)) {
                    //check for password validity
                    if (validatePassword(password)) {
                        iRegisterPresenter.onSuccessfulValidation(name,gender,age,email,password);
                    } else {
                        //for invalid password
                        iRegisterPresenter.onPasswordError();
                    }
                } else {
                    //for invalid email
                    iRegisterPresenter.onEmailError();
                }
            } else {
                //for invalid age
                iRegisterPresenter.onAgeError();
            }
        } else {
            //for invalid name
            iRegisterPresenter.onNameError();
        }
    }

    public void validate(String email, String password) {
        if (validateEmail(email)) {
            //check for password validity
            if (validatePassword(password)) {
                iLoginPresenter.onSuccessfulValidation(email,password);
            } else {
                //for invalid password
                iLoginPresenter.onPasswordError();
            }
        } else {
            //for invalid email
            iLoginPresenter.onEmailError();
        }
    }
}