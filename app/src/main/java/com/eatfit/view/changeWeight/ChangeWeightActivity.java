package com.eatfit.view.changeWeight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.getWeightGoalModel.GetWeightModel;
import com.eatfit.model.setNewWeightModel.SetNewWeightModel;
import com.eatfit.view.menu.MainMenuActivity;

public class ChangeWeightActivity extends AppCompatActivity implements View.OnClickListener{

    Button add,substract,next;
    TextView weightValue;
    String username;
    double currentWeight, maxWeight,minWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_weight);


        Intent intent  = getIntent();
        username = (String) intent.getSerializableExtra("username");

        getWeight(username);



    }

    private void getWeight(String username) {
        GetWeightModel getWeightModel = new GetWeightModel(username,this);
        getWeightModel.getUerInfo();
    }

    public void afterSuccessfulWeightFetch(Double current_Weight){
        this.currentWeight= current_Weight;

        maxWeight = currentWeight + 30;
        minWeight= currentWeight - 30;
        add = findViewById(R.id.btn_add_new_weight);
        substract = findViewById(R.id.btn_substract_new_weight);
        weightValue = findViewById(R.id.new_weight_value);
        weightValue.setText(currentWeight+" kg");

        next = findViewById(R.id.btn_change_weight_next_button);
        add.setOnClickListener(this);
        substract.setOnClickListener(this);
        next.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add_new_weight:
                if(maxWeight > currentWeight){
                    currentWeight = currentWeight + 1;
                    weightValue.setText(Double.toString(currentWeight)+" kg");
                }
                break;

            case R.id.btn_substract_new_weight:
                if((currentWeight - minWeight) > 0 && currentWeight > 40) {
                    currentWeight = currentWeight - 1;
                    weightValue.setText(Double.toString(currentWeight)+" kg");
                }
                break;

            case R.id.btn_change_weight_next_button:
                String goalWeight = weightValue.getText().toString();
                goalWeight = goalWeight.substring(0,goalWeight.length() -3);
                double weight = Double.parseDouble(goalWeight);
                updateWeight(weight);
        }
    }

    private void updateWeight(double weight) {
        SetNewWeightModel setNewWeightModel = new SetNewWeightModel(username,weight,this);
        setNewWeightModel.authenticate();
    }

    public void onFailedToFetchCurentWeight() {
        Toast.makeText(ChangeWeightActivity.this,"check internet connection !!",Toast.LENGTH_SHORT).show();;
        Intent intent = new Intent(ChangeWeightActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void onSuccessfulUpdate() {
        Toast.makeText(ChangeWeightActivity.this,"Successful update !!",Toast.LENGTH_SHORT).show();;
        Intent intent = new Intent(ChangeWeightActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}

