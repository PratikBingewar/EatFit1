package com.eatfit.presenter.registration;

public interface IRegisterPresenter {
    void getData(String name, String gender, int age, String s, String email, String password);
    void onSuccessfulRegistration();
    void onFailedRegistration();
    void userAlreadyExists();
    void validate(String name, String gender, int age, String email, String password);
    void onEmailError();
    void onNameError();
    void onPasswordError();
    void onAgeError();
}
