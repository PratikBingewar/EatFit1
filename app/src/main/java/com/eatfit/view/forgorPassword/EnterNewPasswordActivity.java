package com.eatfit.view.forgorPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.forgetPassword.EnterPasswordPresenter;
import com.eatfit.presenter.forgetPassword.IEnterNewPasswordPresenter;
import com.eatfit.view.menu.MainMenuActivity;

public class EnterNewPasswordActivity extends AppCompatActivity implements IEnterNewPasswordView, View.OnClickListener {

    EditText password,rePassword;
    Button next;
    String passwordVal,rePasswordVal,username;
    IEnterNewPasswordPresenter enterNewPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_password);

        password = findViewById(R.id.input_new_password);
        rePassword = findViewById(R.id.input_new_repassword);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        next = findViewById(R.id.btn_forget_password_next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(password.getText().toString().isEmpty()){
            onEmptyPassword();
        }else if(rePassword.getText().toString().isEmpty()){
            onEmptyRePassword();
        }else {

            passwordVal = password.getText().toString();
            rePasswordVal = rePassword.getText().toString();
            enterNewPasswordPresenter = new EnterPasswordPresenter(this,username,passwordVal,rePasswordVal);
            enterNewPasswordPresenter.validate();
        }
    }

    @Override
    public void onSuccessfulChange() {
        Toast.makeText(EnterNewPasswordActivity.this,"Password Saved Successfully !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EnterNewPasswordActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPasswordDoNotMatch() {
        rePassword.setError("Password do not match");
    }

    @Override
    public void onInvalidLength() {
        password.setError("Invalid Length !!!");
    }

    @Override
    public void onEmptyPassword() {
        password.setError("Password cannot be empty !!!");
    }

    @Override
    public void onEmptyRePassword() {
        rePassword.setError("Password cannot be empty !!!");
    }

    @Override
    public void onConnectionFailed() {
        Toast.makeText(EnterNewPasswordActivity.this,"Connection Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnsuccessfulUpdate() {
        Toast.makeText(EnterNewPasswordActivity.this,"Failed to update !!!\nCheck your Internet Connection",Toast.LENGTH_SHORT).show();
    }
}
