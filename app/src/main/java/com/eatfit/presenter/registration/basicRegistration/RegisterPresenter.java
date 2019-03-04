package com.eatfit.presenter.registration.basicRegistration;

import com.eatfit.model.Validator;
import com.eatfit.model.registration.IRegisterModel;
import com.eatfit.model.registration.RegisterModel;
import com.eatfit.view.registration.basicRegistration.RegistrationActivity;

public class RegisterPresenter implements IRegisterPresenter {

    RegistrationActivity registerView;
    IRegisterModel iRegisterModel;
    Validator validator;

    public RegisterPresenter(RegistrationActivity iRegisterView){
        registerView = iRegisterView;
    }

    @Override
    public void getData(String name, String gender, int age, String email, String password,String rePassword) {

        //check if password matches
        if(password.equals(rePassword)) {

            //check if all fields are filled
            if((!password.isEmpty()) && (!rePassword.isEmpty()) && !name.isEmpty() && (age != 0) && (!email.isEmpty())) {

                validate(name,gender,age,email,password);
            }else{

                //inform user to fil all fields
                callForEmptyFields();
            }
        }else{
            //inform user that passwords do not match
            callReEnterPassword();
        }
    }

    public void callForEmptyFields(){
        registerView.callForEmptyFields();
    }

    public void callReEnterPassword(){
        registerView.callReEnterPassword();
    }


    @Override
    public void onEmailError() {
        registerView.onEmailError();
    }

    @Override
    public void onNameError() {
        registerView.onNameError();
    }

    @Override
    public void onPasswordError() {
        registerView.onPasswordError();
    }

    @Override
    public void onAgeError() {
        registerView.onAgeError();
    }

    @Override
    public void onValidUsername() {
        registerView.onSuccessfulRegistration();
    }

    @Override
    public void onSuccessfulRegistration() {
        registerView.onSuccessfulRegistration();
    }

    @Override
    public void userAlreadyExists() {
        registerView.userAlreadyExists();
    }

    @Override
    public void validate(String name, String gender, int age, String email, String password) {
        validator = new Validator(this);
        validator.validate(name, gender,age, email, password);
    }

    //on successful validation
    public void onSuccessfulValidation(String name, String gender, int age, String email, String password){
        //iRegisterModel = new RegisterModel(name,gender,age,email,password,registerView,this);
        // iRegisterModel.authenticate();
        iRegisterModel = new RegisterModel(email,registerView,this);
        iRegisterModel.authenticate();
    }

    @Override
    public void onFailedConnection() {
        registerView.onFailedConection();
    }

}