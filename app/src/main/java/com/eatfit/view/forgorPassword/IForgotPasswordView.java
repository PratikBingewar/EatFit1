package com.eatfit.view.forgorPassword;

public interface IForgotPasswordView {
    void onNoSelection();
    void onNoEmail();
    void onWrongEmail();
    void onInvalidUser();
    void onValidInput();
    void onConnectionFailed();
}
