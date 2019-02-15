package com.eatfit.view.login;

public interface ILoginView {
    void onSuccessfulLogin();
    void onFailedLogin();
    void onEmailError();
    void onPsswordError();
}
