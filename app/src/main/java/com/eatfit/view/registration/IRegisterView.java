package com.eatfit.view.registration;

public interface IRegisterView {
    void onFailedRegistration();
    void userAlreadyExists();
    void onEmailError();
    void onNameError();
    void onPasswordError();
    void onAgeError();
    void callForEmptyFields();
    void callReEnterPassword();
    void onSuccessfulRegistration();
}
