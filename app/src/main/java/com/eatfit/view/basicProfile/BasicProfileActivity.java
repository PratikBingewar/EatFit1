package com.eatfit.view.basicProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.eatfit.R;
import com.eatfit.model.getUserInfo.GetUserInfoModel;
import com.eatfit.presenter.User;

public class BasicProfileActivity extends AppCompatActivity {

    Intent intent;
    User user;
    TextView age,name,gender,emailOnTop,nameOnTop,BMI,BMR,username;
    String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_profile);

        age = findViewById(R.id.age_on_basic_profile_to_edit);
        name = findViewById(R.id.name_on_basic_profile_to_edit);
        gender = findViewById(R.id.gender_on_basic_profile_to_edit);
        emailOnTop = findViewById(R.id.email_on_basic_profile_top);
        nameOnTop = findViewById(R.id.name_on_basic_profile_top);
        BMI = findViewById(R.id.BMI_on_basic_profile_to_edit);
        BMR = findViewById(R.id.BMR_on_basic_profile_to_edit);

        username = findViewById(R.id.email_on_basic_profile_to_edit);

        intent = getIntent();
        USERNAME = (String) intent.getSerializableExtra("username");

        GetUserInfoModel getUserInfoModel = new GetUserInfoModel(USERNAME,this);
        getUserInfoModel.getUserObject();



    }

    private void setValuesOnView() {

        name.setText(user.getName());
        nameOnTop.setText(user.getName());

        emailOnTop.setText(USERNAME);
        username.setText(USERNAME);

        age.setText(Double.toString(user.getAge()));
        gender.setText(user.getGender());
        BMI.setText(Double.toString(user.getBMI()));
        BMR.setText(Double.toString(user.getBMR()));

    }


    public void onSuccessfulUserObject(User user) {

        this.user = user;
        setValuesOnView();


    }
}
