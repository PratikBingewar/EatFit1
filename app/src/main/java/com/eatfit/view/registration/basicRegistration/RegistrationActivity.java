package com.eatfit.view.registration.basicRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.presenter.registration.basicRegistration.IRegisterPresenter;
import com.eatfit.presenter.registration.basicRegistration.RegisterPresenter;
import com.eatfit.view.login.LoginActivity;
import com.eatfit.view.registration.setHeightAndWeight.SetHeightAndWeightActivity;

import java.io.Serializable;

public class RegistrationActivity extends AppCompatActivity implements IRegisterView, View.OnClickListener {

    EditText edtName, edtAge, edtEmail,edtPassword, edtRePassword;
    Button btnSignUp, btnLogin;
    IRegisterPresenter iRegisterPresenter;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    String name,email,password,rePassword,gender;
    int age;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_1);

        user = new User();

        InputFilter[] nameFilter = new InputFilter[1];
        edtName = findViewById(R.id.input_reg_name);
        nameFilter[0] = new InputFilter.LengthFilter(20);
        edtName.setFilters(nameFilter);

        radioGenderGroup = findViewById(R.id.radioGroup);

        InputFilter[] ageFilters = new InputFilter[1];
        edtAge = findViewById(R.id.input_reg_age);
        ageFilters[0] = new InputFilter.LengthFilter(2);
        edtAge.setFilters(ageFilters);

        InputFilter[] emailFilters = new InputFilter[1];
        edtEmail = findViewById(R.id.input_reg_email);
        emailFilters[0] = new InputFilter.LengthFilter(20);
        edtEmail.setFilters(emailFilters);

        InputFilter[] passFilters = new InputFilter[1];
        edtPassword = findViewById(R.id.input_reg_password);
        passFilters[0] = new InputFilter.LengthFilter(10);
        edtPassword.setFilters(passFilters);

        edtRePassword = findViewById(R.id.input_reg_reEnterPassword);
        passFilters[0] = new InputFilter.LengthFilter(10);
        edtRePassword.setFilters(passFilters);


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
        gender = radioGenderButton.getText().toString().toLowerCase();
        user.setGender(gender);
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
        user.setName(name);
        user.setAge(age);
        user.setUsername(email);
        user.setPassword(password);
        Intent intent = new Intent(RegistrationActivity.this, SetHeightAndWeightActivity.class);
        intent.putExtra("user", user);
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
    public void onFailedConection() {
        makeToast("Check your Internet Connection !!");
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
