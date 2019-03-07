package com.eatfit.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.login.ILoginPresenter;
import com.eatfit.presenter.login.LoginRepresenter;
import com.eatfit.view.forgorPassword.ForgotPasswordActivity;
import com.eatfit.view.menu.MainMenuActivity;
import com.eatfit.view.registration.basicRegistration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    EditText txtEmail, txtPassword;
    Button login, register, forgotPassword;
    ILoginPresenter loginPresenter;
    boolean setPasswordViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        txtEmail = findViewById(R.id.input_login_email);
        txtPassword = findViewById(R.id.input_login_password);
        txtPassword.setOnClickListener(this);

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);

        forgotPassword = findViewById(R.id.btn_forgot_password);
        forgotPassword.setOnClickListener(this);
        loginPresenter = new LoginRepresenter(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        loginPresenter = new LoginRepresenter(this);
        switch (view.getId()) {

            case R.id.btn_login:
                loginPresenter.onLogin(txtEmail.getText().toString(),txtPassword.getText().toString());
                break;

            case R.id.btn_register:
                intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_forgot_password:
                intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccessfulLogin() {
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailedLogin() {
        Log.d("pratik","in onFailedLogin ");
        makeToast("Login Failed");

    }

    @Override
    public void onEmailError() {
        Log.d("pratik","in onEmailError ");
        txtEmail.setError("Incorrect Email ID");
    }

    @Override
    public void onPsswordError() {
        Log.d("pratik","in onPsswordError ");
        txtPassword.setError("Incorrect Password");
    }

    public void makeToast(String msg) {
        Toast.makeText(LoginActivity.this,""+msg,Toast.LENGTH_SHORT).show();
    }
}