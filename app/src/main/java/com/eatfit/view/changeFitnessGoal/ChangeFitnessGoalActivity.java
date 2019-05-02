package com.eatfit.view.changeFitnessGoal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.changeFitnessGoal.ChangeFitnessGoalModel;
import com.eatfit.view.menu.MainMenuActivity;
import com.eatfit.view.registration.setFitnessGoal.SetFitnessGoalActivity;
import com.eatfit.view.registration.setWeightGoal.SetWeightGoalActivity;

public class ChangeFitnessGoalActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String username;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;
    Button next;
    String goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_fitness_goal);

        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");

        Log.d("username in fit goal: ",username);
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
        int id = 1;
        if(goal.equals("weight loss")) {
            id = 1;
        }
        else if(goal.equals("muscle gain")) {
            id = 2;
        }
        else if(goal.equals("stay fit")) {
            id = 3;
        }
        ChangeFitnessGoalModel changeFitnessGoalModel = new ChangeFitnessGoalModel(username,id,this);
        changeFitnessGoalModel.authenticate();
    }

    public void onNoSelection() {
        Toast.makeText(ChangeFitnessGoalActivity.this,"Select One Goal !!",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulUpdate() {
        Toast.makeText(ChangeFitnessGoalActivity.this,"Update successful !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeFitnessGoalActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void onFailedUpdate() {
        Toast.makeText(ChangeFitnessGoalActivity.this,"failed to update !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeFitnessGoalActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
