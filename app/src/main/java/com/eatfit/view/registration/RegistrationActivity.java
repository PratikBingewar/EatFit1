package com.eatfit.view.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.registration.IRegisterPresenter;
import com.eatfit.presenter.registration.RegisterPresenter;
import com.eatfit.view.login.LoginActivity;
import com.eatfit.view.registration.setHeightAndWeight.SetHeightAndWeightActivity;

public class RegistrationActivity extends AppCompatActivity implements IRegisterView, View.OnClickListener {

    EditText edtName, edtAge, edtEmail,edtPassword, edtRePassword;
    Button btnSignUp, btnLogin;
    IRegisterPresenter iRegisterPresenter;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    String name,email,password,rePassword,gender;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_1);

        edtName = findViewById(R.id.input_reg_name);

        radioGenderGroup = findViewById(R.id.radioGroup);

        edtAge = findViewById(R.id.input_reg_age);

        edtEmail = findViewById(R.id.input_reg_email);
        edtPassword = findViewById(R.id.input_reg_password);
        edtRePassword = findViewById(R.id.input_reg_reEnterPassword);

        btnSignUp = findViewById(R.id.btn_reg_signup);
        btnLogin = findViewById(R.id.btn_reg_login);


        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        iRegisterPresenter = new RegisterPresenter(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (view == btnSignUp) {
            validateRadioButtonAndAge();
            iRegisterPresenter = new RegisterPresenter(this);
            iRegisterPresenter.getData(name,gender,age,email,password,rePassword);
        }
    }

    private void validateRadioButtonAndAge() {

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = findViewById(selectedId);
        if(radioGenderButton == null) {
            onEmptyRadioButton();
            return;
        }

        name = edtName.getText().toString().trim();
        gender = radioGenderButton.getText().toString().trim();
        if(! edtAge.getText().toString().equals("")){
            age = Integer.parseInt(edtAge.getText().toString().trim());
        }else{
            onAgeError();
            return;
        }

        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        rePassword = edtRePassword.getText().toString().trim();
    }

    @Override
    public void onSuccessfulRegistration() {
        Intent intent = new Intent(RegistrationActivity.this, SetHeightAndWeightActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("gender",gender);
        intent.putExtra("age",age);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        startActivity(intent);
    }

    public void onEmptyRadioButton() {
        makeToast("Select Gender");
    }

    public void callForEmptyFields() {
        makeToast("Fill all fields");
    }

    public void callReEnterPassword() {
        makeToast("Passwords do not match !!");
    }



    @Override
    public void onFailedRegistration() {
        makeToast("Registration Failed !!");
    }

    @Override
    public void userAlreadyExists() {
        edtEmail.setError("Email already exists !!");
    }

    @Override
    public void onEmailError() {
        edtEmail.setError("Invalid Email");
    }

    @Override
    public void onNameError() {
        edtName.setError("Invalid Name");
    }

    @Override
    public void onPasswordError() {
        edtPassword.setError("Invalid Password");
    }

    @Override
    public void onAgeError() {
        edtAge.setError("Invalid Age");
    }

    public void makeToast(String msg) {
        Toast.makeText(RegistrationActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
