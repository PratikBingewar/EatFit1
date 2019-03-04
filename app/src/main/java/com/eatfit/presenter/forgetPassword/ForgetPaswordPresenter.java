package com.eatfit.presenter.forgetPassword;

import android.widget.EditText;

import com.eatfit.model.Validator;
import com.eatfit.model.forgetPassword.ForgetPasswordModel;
import com.eatfit.model.forgetPassword.IForgetPasswordModel;
import com.eatfit.view.forgorPassword.EnterNewPasswordActivity;
import com.eatfit.view.forgorPassword.ForgotPasswordActivity;
import com.eatfit.view.forgorPassword.IEnterNewPasswordView;
import com.eatfit.view.forgorPassword.IForgotPasswordView;

public class ForgetPaswordPresenter implements IForgetPasswordPresenter{
    ForgotPasswordActivity forgotPasswordview;
    String email,goal,password,rePassword;
    IForgetPasswordModel iForgetPasswordModel;

    EnterNewPasswordActivity enterNewPasswordActivity;

    public ForgetPaswordPresenter(ForgotPasswordActivity forgotPasswordActivity, String emailVal, String goal) {
        this.email = emailVal;
        this.goal = goal;
        this.forgotPasswordview = forgotPasswordActivity;
    }



    @Override
    public void onWrongEmailInput() {
        forgotPasswordview.onWrongEmail();
    }

    @Override
    public void onInvalidUser() {
        forgotPasswordview.onInvalidUser();
    }

    @Override
    public void validate() {
        Validator validator = new Validator();
        boolean result = validator.validateEmail(email);
        if(result){
            iForgetPasswordModel = new ForgetPasswordModel(forgotPasswordview,this,email,goal);
            iForgetPasswordModel.authenticate();
        }else {
            onWrongEmailInput();
        }
    }

    @Override
    public void onValidUserInput() {
        forgotPasswordview.onValidInput();
    }

    @Override
    public void onConnectionFailed() {
        forgotPasswordview.onConnectionFailed();
    }
}
