package com.eatfit.view.changeWeight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.presenter.registration.setWeightGoal.ISetWeightGoalPresenter;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;
import com.eatfit.view.registration.setWeightGoal.SetWeightGoalActivity;

public class ChangeWeightActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button add,substract,next;
    Spinner spinner;
    TextView weightValue;
    User user;
    String intensityValue;
    double currentWeight, maxWeight,minWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight_goal);

        spinner = findViewById(R.id.intensty_level_weight_goal);

        Intent intent  = getIntent();
        user = (User) intent.getSerializableExtra("user");

        String fitnessGoal = user.getFitnessGoal();

        if(fitnessGoal.equals("muscle gain")){
            currentWeight = user.getWeight();
            minWeight = currentWeight;
            maxWeight = currentWeight + 30;
        }
        else if(fitnessGoal.equals("weight loss")) {
            currentWeight = user.getWeight();
            maxWeight = currentWeight;
            minWeight= currentWeight - 30;
        }else if(fitnessGoal.equals("stay fit")){
            currentWeight = user.getWeight();
            maxWeight = currentWeight + 30;
            minWeight= currentWeight - 30;
        }


        add = findViewById(R.id.btn_add);
        substract = findViewById(R.id.btn_substract);
        weightValue = findViewById(R.id.weight_goal_value);
        weightValue.setText(Double.toString(currentWeight)+" kg");

        next = findViewById(R.id.btn_set_weight_goal_next);
        add.setOnClickListener(this);
        substract.setOnClickListener(this);
        next.setOnClickListener(this);

        ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this,R.array.intensity_array,android.R.layout.simple_spinner_item );
        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(charSequenceArrayAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        intensityValue = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add:
                if(maxWeight > currentWeight){
                    currentWeight = currentWeight + 1;
                    weightValue.setText(Double.toString(currentWeight)+" kg");
                }
                break;

            case R.id.btn_substract:
                if((currentWeight - minWeight) > 0 && currentWeight > 40) {
                    currentWeight = currentWeight - 1;
                    weightValue.setText(Double.toString(currentWeight)+" kg");
                }
                break;

//            case R.id.btn_set_weight_goal_next:
//                if(intensityValue.isEmpty()){
//                    onNotSelectingIntensity();
//                }else{
//                    String goalWeight = weightValue.getText().toString();
//                    goalWeight = goalWeight.substring(0,goalWeight.length() -3);
//                    double weightGoal = Double.parseDouble(goalWeight);
//                    user.setWeightGoal(weightGoal);
//                    user.setIntensity(intensityValue);
//
//                    Intent intent = new Intent(SetWeightGoalActivity.this, SetCurrentCalorieConsmptionActivity.class);
//                    intent.putExtra("user",user);
//                    startActivity(intent);
//                }
//                break;
        }
    }

}
