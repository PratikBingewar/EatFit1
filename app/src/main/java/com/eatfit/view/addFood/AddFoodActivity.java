package com.eatfit.view.addFood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.addFood.AddFoodModel;
import com.eatfit.presenter.User;
import com.eatfit.view.menu.MainMenuActivity;
import com.eatfit.view.registration.setCurrentCalorieConsmption.SetCurrentCalorieConsmptionActivity;
import com.eatfit.view.registration.setWeightGoal.SetWeightGoalActivity;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    TextView foodName;
    TextView carbQty;
    TextView proteinQty;
    TextView fatQty;
    TextView unitOfFood;
    TextView totalCal;
    TextView qtyOfFood;
    Button save,add, sub;
    String nameOfFood;
    double carbs,fats,proteins,totalCalVal;
    String unit;
    double quantity=1;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        intent = getIntent();
        nameOfFood = (String) intent.getSerializableExtra("name");

        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");
        Log.d("username: ",username);
        
        carbs = (double) intent.getSerializableExtra("carbs");
        fats = (double) intent.getSerializableExtra("fats");
        proteins = (double) intent.getSerializableExtra("proteins");

        unit = (String) intent.getSerializableExtra("unit");
        totalCalVal = (double) intent.getSerializableExtra("unit_cal");

        Toast.makeText(AddFoodActivity.this,"selected: "+nameOfFood+" \ncalories: "+totalCalVal,Toast.LENGTH_SHORT).show();
        setIDs();

        setTexts();

        setListeners();
    }

    private void setListeners() {
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void setIDs() {
        foodName =findViewById(R.id.selected_food_name);
        carbQty = findViewById(R.id.carbs_qty);
        proteinQty = findViewById(R.id.protein_qty);
        fatQty = findViewById(R.id.fats_qty);
        //totalCal = findViewById(R.id.total_cal_qty);
        unitOfFood = findViewById(R.id.unit);
        qtyOfFood = findViewById(R.id.value_of_qty);

        save = findViewById(R.id.btn_save);
        add = findViewById(R.id.add_food_qty);
        sub = findViewById(R.id.sub_food_qty);
    }

    private void setTexts() {
        foodName.setText(nameOfFood);

        carbQty.setText(String.valueOf(carbs));
        proteinQty.setText(String.valueOf(proteins));
        fatQty.setText(String.valueOf(fats));

        unitOfFood.setText(unit);

        //totalCal.setText(String.valueOf(totalCal));

        qtyOfFood.setText("1");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.add_food_qty:

                quantity = Double.valueOf(qtyOfFood.getText().toString());
                carbs = Double.valueOf(carbQty.getText().toString());
                proteins = Double.valueOf(carbQty.getText().toString());
                fats = Double.valueOf(carbQty.getText().toString());
                //totalCalVal = Double.valueOf(totalCal.getText().toString());

                if(quantity <= 5){
                    quantity = quantity + 0.5;
                    qtyOfFood.setText(String.valueOf(quantity));

                    carbs = carbs + carbs*(0.5);
                    proteins = proteins + proteins*(0.5);
                    fats = fats + fats*(0.5);
                    //totalCalVal = totalCalVal + totalCalVal*(0.5);

                    carbs = (double) Math.round(carbs * 100) / 100;
                    proteins = (double) Math.round(proteins * 100) / 100;
                    fats = (double) Math.round(fats * 100) / 100;

                    carbQty.setText(String.valueOf(carbs));
                    proteinQty.setText(String.valueOf(proteins));
                    fatQty.setText(String.valueOf(fats));
                   // totalCal.setText(String.valueOf(totalCalVal));
                }
                break;

            case R.id.sub_food_qty:
                quantity = Double.valueOf(qtyOfFood.getText().toString());
                carbs = Double.valueOf(carbQty.getText().toString());
                proteins = Double.valueOf(carbQty.getText().toString());
                fats = Double.valueOf(carbQty.getText().toString());
                //totalCalVal = Double.valueOf(totalCal.getText().toString());
                if(quantity >= 1){
                    quantity = quantity - 0.5;
                    qtyOfFood.setText(String.valueOf(quantity));

                    carbs = carbs - carbs*(0.5);
                    proteins = proteins - proteins*(0.5);
                    fats = fats - fats*(0.5);
                   // totalCalVal = totalCalVal - totalCalVal*(0.5);


                    carbs = (double) Math.round(carbs * 100) / 100;
                    proteins = (double) Math.round(proteins * 100) / 100;
                    fats = (double) Math.round(fats * 100) / 100;

                    carbQty.setText(String.valueOf(carbs));
                    proteinQty.setText(String.valueOf(proteins));
                    fatQty.setText(String.valueOf(fats));
                    //totalCal.setText(String.valueOf(totalCalVal));
                }
                break;

            case R.id.btn_save:


                carbs = (double) Math.round(carbs * 100) / 100;
                proteins = (double) Math.round(proteins * 100) / 100;
                fats = (double) Math.round(fats * 100) / 100;

                quantity = Double.valueOf(qtyOfFood.getText().toString());
                carbs = Double.valueOf(carbQty.getText().toString());
                proteins = Double.valueOf(carbQty.getText().toString());
                fats = Double.valueOf(carbQty.getText().toString());
               // totalCalVal = Double.valueOf(totalCal.getText().toString());

                Toast.makeText(AddFoodActivity.this,""+carbs+" "+proteins+" "+fats+" ",Toast.LENGTH_SHORT).show();
                AddFoodModel addFoodModel = new AddFoodModel(username, nameOfFood, this);
                addFoodModel.authenticate();
                break;
                
        }
    }

    public void onSuccessfulAddition() {
        Intent intent = new Intent(AddFoodActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void onFailedAddition() {
        Intent intent = new Intent(AddFoodActivity.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
