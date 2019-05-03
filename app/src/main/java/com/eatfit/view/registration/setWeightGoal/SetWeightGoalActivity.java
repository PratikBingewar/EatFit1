package com.eatfit.view.registration.setWeightGoal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.getFitnessGoal.GetWeightModel;
import com.eatfit.model.getWeightGoalModel.GetWeightGoalModel;
import com.eatfit.model.setNewWeightModel.SetNewWeightModel;
import com.eatfit.presenter.User;
import com.eatfit.presenter.registration.setWeightGoal.ISetWeightGoalPresenter;
import com.eatfit.view.changeWeight.ChangeWeightActivity;
import com.eatfit.view.menu.MainMenuActivity;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;

public class SetWeightGoalActivity extends AppCompatActivity  implements View.OnClickListener{

    Button add, substract, next;
    TextView weightValue;
    String username;
    double currentWeight, maxWeight, minWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight_goal);


        Intent intent = getIntent();
        username = (String) intent.getSerializableExtra("username");

        getWeight(username);


    }

    private void getWeight(String username) {
        GetWeightGoalModel getWeightGoalModel = new GetWeightGoalModel(username, this);
        getWeightGoalModel.getUerInfo();
    }

    public void afterSuccessfulWeightFetch(Double current_Weight) {
        this.currentWeight = current_Weight;

        maxWeight = currentWeight + 30;
        minWeight = currentWeight - 30;
        add = findViewById(R.id.btn_add);
        substract = findViewById(R.id.btn_substract);
        weightValue = findViewById(R.id.weight_goal_value);
        weightValue.setText(Double.toString(currentWeight) + " kg");

        next = findViewById(R.id.btn_set_chage_weight_next);
        add.setOnClickListener(this);
        substract.setOnClickListener(this);
        next.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add:
                if (maxWeight > currentWeight) {
                    currentWeight = currentWeight + 1;
                    weightValue.setText(Double.toString(currentWeight) + " kg");
                }
                break;

            case R.id.btn_substract:
                if ((currentWeight - minWeight) > 0 && currentWeight > 40) {
                    currentWeight = currentWeight - 1;
                    weightValue.setText(Double.toString(currentWeight) + " kg");
                }
                break;

            case R.id.btn_set_chage_weight_next:
                String goalWeight = weightValue.getText().toString();
                goalWeight = goalWeight.substring(0, goalWeight.length() - 3);
                double weight = Double.parseDouble(goalWeight);
                updateWeight(weight);
                break;
        }
    }

    private void updateWeight(double weight) {
        Toast.makeText(SetWeightGoalActivity.this, "Successful update !!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SetWeightGoalActivity.this, MainMenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onFailedToFetchCurentWeight() {
        Toast.makeText(SetWeightGoalActivity.this, "Successful update !!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SetWeightGoalActivity.this, MainMenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onSuccessfulUpdate() {
        Toast.makeText(SetWeightGoalActivity.this,"Successful update !!",Toast.LENGTH_SHORT).show();;
        Intent intent = new Intent(SetWeightGoalActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}