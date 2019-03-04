package com.eatfit.view.registration.basicRegistration;

public interface IRegisterView {
    void onFailedConection();
    void userAlreadyExists();
    void onEmailError();
    void onNameError();
    void onPasswordError();
    void onAgeError();
    void callForEmptyFields();
    void callReEnterPassword();
    void onSuccessfulRegistration();
}
