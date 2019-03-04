package com.eatfit.view.forgorPassword;

public interface IEnterNewPasswordView {
    void onSuccessfulChange();
    void onPasswordDoNotMatch();
    void onInvalidLength();
    void onEmptyPassword();
    void onEmptyRePassword();
    void onConnectionFailed();
    void onUnsuccessfulUpdate();
}
