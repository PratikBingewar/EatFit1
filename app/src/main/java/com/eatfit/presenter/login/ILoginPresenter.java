package com.eatfit.presenter.login;

public interface ILoginPresenter {
    void onLogin(String email, String password);
    void onSuccessfulLogin();
    void onFailedLogin();
    void validate(String email, String password);
    void onSuccessfulValidation(String email, String password);
    void onPasswordError();
    void onEmailError();

}
