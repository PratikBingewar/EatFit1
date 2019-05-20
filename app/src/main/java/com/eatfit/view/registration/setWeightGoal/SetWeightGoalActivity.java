package com.eatfit.view.registration.setWeightGoal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;

public class SetWeightGoalActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button add, substract, next;
    TextView weightValue;
    double currentWeight, maxWeight, minWeight;
    Spinner spinnerIntensity;
    String intensityLevel;
    User user;
    String fitnessGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_weight);


        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        fitnessGoal = user.getFitnessGoal();
        currentWeight = user.getWeight();

        Log.d("weight of usr: ",currentWeight+"");

        add = findViewById(R.id.new_btn_add);
        substract = findViewById(R.id.new_btn_substract);
        weightValue = findViewById(R.id.weight_goal_value);

        spinnerIntensity = findViewById(R.id.intensity_level_spinner);
        ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this,R.array.intensity_array,android.R.layout.simple_spinner_item );
        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIntensity.setAdapter(charSequenceArrayAdapter);
        spinnerIntensity.setOnItemSelectedListener(this);

        next = findViewById(R.id.btn_set_weight_goal_next);
        add.setOnClickListener(this);
        substract.setOnClickListener(this);
        next.setOnClickListener(this);

        weightValue.setText(user.getWeight()+" kg");

        maxWeight = currentWeight + 20;
        minWeight = currentWeight - 20;
        if(minWeight < 40) {
            minWeight = 40;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.new_btn_add:
                if (maxWeight > currentWeight) {
                    currentWeight = currentWeight + 1;
                    weightValue.setText(Double.toString(currentWeight) + " kg");
                }
                break;

            case R.id.new_btn_substract:
                if ((currentWeight - minWeight) > 0 && currentWeight > 40) {
                    currentWeight = currentWeight - 1;
                    weightValue.setText(Double.toString(currentWeight) + " kg");
                }
                break;

            case R.id.btn_set_weight_goal_next:

                user.setWeightGoal(currentWeight);
                user.setIntensity(intensityLevel);
                Log.d("weight goal: ",currentWeight+" "+intensityLevel);
                Intent intent = new Intent(SetWeightGoalActivity.this, SetCurrentCalorieConsmptionActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        intensityLevel = parent.getItemAtPosition(position).toString().toLowerCase();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}