package com.eatfit.presenter.forgetPassword;

import com.eatfit.model.Validator;
import com.eatfit.model.forgetPassword.EnterNewPasswordModel;
import com.eatfit.model.forgetPassword.IEnterNewPasswordModel;
import com.eatfit.model.forgetPassword.IForgetPasswordModel;
import com.eatfit.view.forgorPassword.EnterNewPasswordActivity;
import com.eatfit.view.forgorPassword.ForgotPasswordActivity;

public class EnterPasswordPresenter implements IEnterNewPasswordPresenter{

    EnterNewPasswordActivity enterNewPasswordView;
    String password,rePassword,username;
    IEnterNewPasswordModel enterNewPasswordModel;
    public EnterPasswordPresenter(EnterNewPasswordActivity enterNewPasswordActivity, String username,String password, String rePassword) {
        this.enterNewPasswordView = enterNewPasswordActivity;
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
    }

    @Override
    public void validate() {
        Validator validator = new Validator();
        if(! rePassword.equals(password)){
            enterNewPasswordView.onPasswordDoNotMatch();
        }
        else if(validator.validatePassword(rePassword)){
            if (validator.validatePassword(password)) {
                onSuccessfulValidation();
            }else{
                enterNewPasswordView.onInvalidLength();
            }
        }else{
            enterNewPasswordView.onInvalidLength();
        }
    }

    @Override
    public void onSuccessfulValidation() {
        enterNewPasswordModel = new EnterNewPasswordModel(username,password,enterNewPasswordView,this);
        enterNewPasswordModel.validate();
    }

    @Override
    public void onConnectionFailed() {
        enterNewPasswordView.onConnectionFailed();
    }

    @Override
    public void onUnsuccessfulUpdate() {
        enterNewPasswordView.onUnsuccessfulUpdate();
    }

    @Override
    public void onSuccessfulUpdate() {
        enterNewPasswordView.onSuccessfulChange();
    }
}
