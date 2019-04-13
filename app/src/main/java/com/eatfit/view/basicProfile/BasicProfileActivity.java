package com.eatfit.view.basicProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eatfit.R;
import com.eatfit.presenter.User;

public class BasicProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button editName, editPassword, editUsername;
    static User user;
    TextView age,name,gender,emailOnTop,nameOnTop,BMI,BMR,username;
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

        user = (User) intent.getSerializableExtra("user");

        setValuesOnView();

        editName = findViewById(R.id.edit_name_button);
        editPassword = findViewById(R.id.edit_password_button);
        editUsername = findViewById(R.id.edit_username_button);

        editName.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        editUsername.setOnClickListener(this);

    }

    private void setValuesOnView() {

        name.setText(user.getName());
        nameOnTop.setText(user.getName());

        emailOnTop.setText(user.getUsername());
        username.setText(user.getUsername());

        age.setText(Double.toString(user.getAge()));
        gender.setText(user.getGender());
        BMI.setText(Double.toString(user.getBMI()));
        BMR.setText(Double.toString(user.getBMR()));

    }

    @Override
    public void onClick(View view) {
        if(view == editName) {
            Intent intent = new Intent(BasicProfileActivity.this,UpdateName.class);
            intent.putExtra("username",user.getUsername());
            startActivity(intent);
        }
        else if(view == editPassword) {
            Intent intent = new Intent(BasicProfileActivity.this,UpdatePassword.class);
            intent.putExtra("username",user.getUsername());
            startActivity(intent);
        }
        else if(view == editUsername) {
            Intent intent = new Intent(BasicProfileActivity.this,UpdateUserName.class);
            intent.putExtra("username",user.getUsername());
            startActivity(intent);
        }
    }
}
