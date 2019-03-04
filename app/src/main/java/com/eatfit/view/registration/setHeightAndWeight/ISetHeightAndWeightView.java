package com.eatfit.view.registration.setHeightAndWeight;

public interface ISetHeightAndWeightView {
    void onHeightError();
    void onWeightError();
    void onEmptyFieldOfHeightError();
    void onEmptyFieldOfWeightError();
    void onSuccessfulInput();
}
