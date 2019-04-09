package com.eatfit.presenter.registration.setHeightAndWeight;

import com.eatfit.view.registration.setHeightAndWeight.SetHeightAndWeightActivity;

public class SetHeightAndWeightPresenter implements ISetHeightAndWeightPresenter {

    double height,weight;
    SetHeightAndWeightActivity setHeightAndWeightView;

    public SetHeightAndWeightPresenter(SetHeightAndWeightActivity iSetHeightAndWeightView, double height, double weight){
        setHeightAndWeightView = iSetHeightAndWeightView;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public void checkWeightAndHeight() {

        if(height > 2.74 || height < 0.30) {
            onFailedHeightInput();
        }
        else if(weight > 300 || weight < 30) {
            onFailedWeightInput();
        }
        else
            SuccessfulVerification();
    }

    @Override
    public void SuccessfulVerification() {
        setHeightAndWeightView.onSuccessfulInput();
    }

    @Override
    public void onFailedHeightInput() {
        setHeightAndWeightView.onHeightError();
    }

    @Override
    public void onFailedWeightInput() {
        setHeightAndWeightView.onWeightError();
    }
}
