package com.eatfit.view.registration.setHeightAndWeight;

public interface ISetHeightAndWeightView {
    void onHeightError();
    void onWeightError();
    void onEmptyFieldsError();

    void onSuccessFulInput();
}
