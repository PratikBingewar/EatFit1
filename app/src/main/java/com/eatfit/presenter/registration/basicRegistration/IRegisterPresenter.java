package com.eatfit.presenter.registration.basicRegistration;

public interface IRegisterPresenter {
    void getData(String name, String gender, int age, String email, String password,String rePassword);
    void onSuccessfulRegistration();
    void onSuccessfulValidation(String name, String gender, int age, String email, String password);
    void onFailedConnection();
    void userAlreadyExists();
    void validate(String name, String gender, int age, String email, String password);
    void onEmailError();
    void onNameError();
    void onPasswordError();
    void onAgeError();
    void onValidUsername();
}
