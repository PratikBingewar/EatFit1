package com.eatfit.presenter.heightAndWeight;

import com.eatfit.view.registration.setHeightAndWeight.ISetHeightAndWeightView;
import com.eatfit.view.registration.setHeightAndWeight.SetHeightAndWeightActivity;

public class HWPresenter implements IHWPresenter{

    ISetHeightAndWeightView iSetHeightAndWeightView;

    public HWPresenter(SetHeightAndWeightActivity setHeightAndWeightActivity) {
    }

    @Override
    public void validate(int height, int weight) {
        if(height < 55 || height > 250) {
            iSetHeightAndWeightView.onHeightError();
        }else if (weight < 30 || weight > 300) {
            iSetHeightAndWeightView.onWeightError();
        }else {
            iSetHeightAndWeightView.onSuccessFulInput();
        }
    }
}
