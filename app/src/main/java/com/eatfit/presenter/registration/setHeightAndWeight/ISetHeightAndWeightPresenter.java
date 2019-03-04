package com.eatfit.presenter.registration.setHeightAndWeight;

public interface ISetHeightAndWeightPresenter {
    void checkWeightAndHeight();
    void SuccessfulVerification();
    void onFailedHeightInput();
    void onFailedWeightInput();
}
