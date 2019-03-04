package com.eatfit.presenter.forgetPassword;

public interface IEnterNewPasswordPresenter {
    void validate();
    void onSuccessfulValidation();
    void onConnectionFailed();
    void onUnsuccessfulUpdate();
}
