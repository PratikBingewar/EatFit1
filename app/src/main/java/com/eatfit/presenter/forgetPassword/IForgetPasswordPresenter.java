package com.eatfit.presenter.forgetPassword;

public interface IForgetPasswordPresenter {
    void onWrongEmailInput();
    void onInvalidUser();
    void validate();
    void onValidUserInput();
    void onConnectionFailed();
}
