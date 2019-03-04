package com.eatfit.view.forgorPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.forgetPassword.ForgetPaswordPresenter;
import com.eatfit.presenter.forgetPassword.IForgetPasswordPresenter;

public class ForgotPasswordActivity extends AppCompatActivity implements IForgotPasswordView, View.OnClickListener {

    EditText username;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    Button next;
    IForgetPasswordPresenter iForgetPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.input_forget_passwrod_email);
        radioGenderGroup = findViewById(R.id.radioGroup_fitness_goal);

        next = findViewById(R.id.btn_forget_password_next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = findViewById(selectedId);
        if(radioGenderButton == null) {
            onNoSelection();
        } else if(! username.getText().toString().isEmpty()){
            String goal = radioGenderButton.getText().toString().toLowerCase();
            String emailVal = username.getText().toString();
            iForgetPasswordPresenter = new ForgetPaswordPresenter(this,emailVal,goal);
            iForgetPasswordPresenter.validate();
        }
    }

    @Override
    public void onNoSelection() {
        Toast.makeText(ForgotPasswordActivity.this,"Select One Goal !!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoEmail() {
        username.setError("Enter Email");
    }

    @Override
    public void onWrongEmail() {
        username.setError("Wrong Input");
    }

    @Override
    public void onInvalidUser() {
        Toast.makeText(ForgotPasswordActivity.this,"Invalid email or fitness goal!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidInput() {
        Intent intent = new Intent(ForgotPasswordActivity.this,EnterNewPasswordActivity.class);
        //send email/username to @EnterNewPasswordActivity to update values
        intent.putExtra("username",username.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed() {
        Toast.makeText(ForgotPasswordActivity.this,"Connection Failed",Toast.LENGTH_SHORT).show();
    }


}
