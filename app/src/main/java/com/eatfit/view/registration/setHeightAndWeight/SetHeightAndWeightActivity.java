package com.eatfit.view.registration.setHeightAndWeight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.presenter.registration.setHeightAndWeight.ISetHeightAndWeightPresenter;
import com.eatfit.presenter.registration.setHeightAndWeight.SetHeightAndWeightPresenter;
import com.eatfit.view.registration.setFitnessGoal.SetFitnessGoalActivity;

public class SetHeightAndWeightActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , View.OnClickListener , ISetHeightAndWeightView{

    EditText height,weight;
    Button next;
    Spinner spinner;
    String activityLevel;
    int heightVal,weightVal;
    User user;
    ISetHeightAndWeightPresenter iSetHeightAndWeightPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_height_and_weight);

        height = findViewById(R.id.input_height);
        weight = findViewById(R.id.input_weight);

        next = findViewById(R.id.btn_height_and_weight_next);
        next.setOnClickListener(this);

        Intent intent  = getIntent();
        user = (User) intent.getSerializableExtra("user");

        spinner = findViewById(R.id.activity_level);
        ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this,R.array.activity_array,android.R.layout.simple_spinner_item );

        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(charSequenceArrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        activityLevel = parent.getItemAtPosition(position).toString().toLowerCase();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        if(height.getText().toString().isEmpty()) {
            onEmptyFieldOfHeightError();
        }
        else if(weight.getText().toString().isEmpty()){
            onEmptyFieldOfWeightError();
        }
        else {
            heightVal = Integer.parseInt(height.getText().toString());
            weightVal = Integer.parseInt(weight.getText().toString());
            iSetHeightAndWeightPresenter = new SetHeightAndWeightPresenter(this,heightVal,weightVal);
            iSetHeightAndWeightPresenter.checkWeightAndHeight();
        }
    }

    @Override
    public void onHeightError() {
        height.setError("Invalid Height");
    }

    @Override
    public void onWeightError() {
        weight.setError("Invalid weight");
    }

    @Override
    public void onEmptyFieldOfHeightError() {
        height.setError("Field cannot be empty");
    }

    @Override
    public void onEmptyFieldOfWeightError() {
        weight.setError("Field cannot be empty");
    }

    @Override
    public void onSuccessfulInput() {
        user.setHeight(heightVal);
        user.setWeight(weightVal);
        user.setActivityLevel(activityLevel);
        Intent intent = new Intent(SetHeightAndWeightActivity.this, SetFitnessGoalActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

}
