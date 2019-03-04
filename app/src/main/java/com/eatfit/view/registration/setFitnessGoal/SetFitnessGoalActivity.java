package com.eatfit.view.registration.setFitnessGoal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.view.registration.setWeightGoal.SetWeightGoalActivity;

public class SetFitnessGoalActivity extends AppCompatActivity implements ISetFitnessGoalView, View.OnClickListener {

    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    Button next;
    String goal;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_fitness_goal);

        Intent intent  = getIntent();
        user = (User) intent.getSerializableExtra("user");

        radioGenderGroup = findViewById(R.id.radioGroup_fitness_goal);

        next = findViewById(R.id.btn_fitness_goal_next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = findViewById(selectedId);
        if(radioGenderButton == null) {
            onNoSelection();
        } else {
            goal = radioGenderButton.getText().toString().toLowerCase();
            callIntent();
        }
    }

    private void callIntent() {
        user.setFitnessGoal(goal);
        Intent intent = new Intent(SetFitnessGoalActivity.this, SetWeightGoalActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    @Override
    public void onNoSelection() {
        Toast.makeText(SetFitnessGoalActivity.this,"Select One Goal !!",Toast.LENGTH_SHORT).show();
    }
}
