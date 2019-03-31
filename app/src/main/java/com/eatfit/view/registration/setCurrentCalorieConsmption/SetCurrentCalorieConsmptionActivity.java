package com.eatfit.view.registration.setCurrentCalorieConsmption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.eatfit.R;
public class SetCurrentCalorieConsmptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    Spinner breakfastIntensity, lunchIntensity, snackIntensity, dinnerIntensity;
    Spinner breakfastDiet, lunchDiet, snackDiet, dinnerDiet;
    ImageButton breakfastImageButton, lunchImageButton, snackImageButton, dinnerImageButton;
    Button next;
    String dietIntensityForBreakfast, dietIntensityForLunch, dietIntensityForSnack, dietIntensityForDinner;
    String dietTypeForBreakfast, dietTypeForLunch, dietTypeForSnack, dietTypeForDinner;
    String dietDescForBreakfast, dietDescForLunch, dietDescForSNack, dietDescForDinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_current_calorie_consmption);

        setIDs();
        setStringsToSpinners();
        setListeners();
    }

    private void setListeners() {
        breakfastIntensity.setOnItemSelectedListener(this);
        lunchIntensity.setOnItemSelectedListener(this);
        snackIntensity.setOnItemSelectedListener(this);
        dinnerIntensity.setOnItemSelectedListener(this);

        breakfastDiet.setOnItemSelectedListener(this);
        dinnerDiet.setOnItemSelectedListener(this);
        snackDiet.setOnItemSelectedListener(this);
        lunchDiet.setOnItemSelectedListener(this);

        breakfastImageButton.setOnClickListener(this);
        lunchImageButton.setOnClickListener(this);
        snackImageButton.setOnClickListener(this);
        dinnerImageButton.setOnClickListener(this);

        next.setOnClickListener(this);

    }


    private void setInfoToDinnerImageButton() {
        setDescForDinner(dietIntensityForDinner,dietTypeForDinner);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForDinner, Toast.LENGTH_LONG).show();
    }

    private void setDescForDinner(String dietIntensityForDinner, String dietTypeForDinner) {

        //for english diet
        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = String.valueOf(R.string.light_english_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = String.valueOf(R.string.medium_english_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("english") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = String.valueOf(R.string.heavy_english_dinner);
        }


        //for south indian diet
        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = String.valueOf(R.string.light_south_indian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = String.valueOf(R.string.medium_south_indian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("south indian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = String.valueOf(R.string.heavy_south_indian_dinner);
        }


        //for north indian diet
        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = String.valueOf(R.string.light_north_indian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = String.valueOf(R.string.medium_north_indian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("north indian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = String.valueOf(R.string.heavy_north_indian_dinner);
        }

        //for maharashtrian diet
        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = String.valueOf(R.string.light_maharashtrian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = String.valueOf(R.string.medium_maharashtrian_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("maharashtrian") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = String.valueOf(R.string.heavy_maharashtrian_dinner);
        }


        //for common diet
        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("light")) {
            dietDescForDinner = String.valueOf(R.string.light_common_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("medium")) {
            dietDescForDinner = String.valueOf(R.string.medium_common_dinner);
        }

        if(dietTypeForDinner.toLowerCase().equals("common") && dietIntensityForDinner.toLowerCase().equals("heavy")) {
            dietDescForDinner = String.valueOf(R.string.heavy_common_dinner);
        }
    }

    private void setInfoToLunchImageButton() {
        setDescForLunch(dietTypeForLunch,dietIntensityForLunch);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForLunch, Toast.LENGTH_LONG).show();
    }

    private void setDescForLunch(String dietTypeForLunch, String dietIntensityForLunch) {
        //for english diet
        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = String.valueOf(R.string.light_english_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = String.valueOf(R.string.medium_english_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("english") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = String.valueOf(R.string.heavy_english_lunch);
        }


        //for south indian diet
        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = String.valueOf(R.string.light_south_indian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = String.valueOf(R.string.medium_south_indian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("south indian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = String.valueOf(R.string.heavy_south_indian_lunch);
        }


        //for north indian diet
        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = String.valueOf(R.string.light_north_indian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = String.valueOf(R.string.medium_north_indian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("north indian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = String.valueOf(R.string.heavy_north_indian_lunch);
        }

        //for maharashtrian diet
        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = String.valueOf(R.string.light_maharashtrian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = String.valueOf(R.string.medium_maharashtrian_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("maharashtrian") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = String.valueOf(R.string.heavy_maharashtrian_lunch);
        }


        //for common diet
        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("light")) {
            dietDescForLunch = String.valueOf(R.string.light_common_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("medium")) {
            dietDescForLunch = String.valueOf(R.string.medium_common_lunch);
        }

        if(dietTypeForLunch.toLowerCase().equals("common") && dietIntensityForLunch.toLowerCase().equals("heavy")) {
            dietDescForLunch = String.valueOf(R.string.heavy_common_lunch);
        }
    }

    private void setInfoTosnackImageButton() {
        setDescForSnack(dietTypeForSnack,dietIntensityForSnack);
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForSNack, Toast.LENGTH_LONG).show();
    }

    private void setDescForSnack(String dietTypeForSnack, String dietIntensityForSnack) {
        //for english diet
        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = String.valueOf(R.string.light_english_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = String.valueOf(R.string.medium_english_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("english") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = String.valueOf(R.string.heavy_english_snack);
        }


        //for south indian diet
        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = String.valueOf(R.string.light_south_indian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = String.valueOf(R.string.medium_south_indian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("south indian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = String.valueOf(R.string.heavy_south_indian_snack);
        }


        //for north indian diet
        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = String.valueOf(R.string.light_north_indian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = String.valueOf(R.string.medium_north_indian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("north indian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = String.valueOf(R.string.heavy_north_indian_snack);
        }

        //for maharashtrian diet
        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = String.valueOf(R.string.light_maharashtrian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = String.valueOf(R.string.medium_maharashtrian_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("maharashtrian") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = String.valueOf(R.string.heavy_maharashtrian_snack);
        }


        //for common diet
        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("light")) {
            dietDescForSNack = String.valueOf(R.string.light_common_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("medium")) {
            dietDescForSNack = String.valueOf(R.string.medium_common_snack);
        }

        if(dietTypeForSnack.toLowerCase().equals("common") && dietIntensityForSnack.toLowerCase().equals("heavy")) {
            dietDescForSNack = String.valueOf(R.string.heavy_common_snack);
        }
    }

    private void setInfoToBreakFastImageButton() {
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, dietDescForBreakfast, Toast.LENGTH_LONG).show();
    }

    private void notifyUserToChooseOption() {
        Toast.makeText(SetCurrentCalorieConsmptionActivity.this, "choose one of the options!!", Toast.LENGTH_SHORT).show();
    }

    private void setStringsToSpinners() {
        ArrayAdapter<CharSequence> dietLevelArrayAdapter = ArrayAdapter.createFromResource(this, R.array.currnt_diet_intensity, android.R.layout.simple_spinner_item);
        dietLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfastIntensity.setPrompt("How heavy is it?");
        lunchIntensity.setPrompt("How heavy is it?");
        snackIntensity.setPrompt("How heavy is it?");
        dinnerIntensity.setPrompt("How heavy is it?");


        breakfastIntensity.setAdapter(dietLevelArrayAdapter);
        lunchIntensity.setAdapter(dietLevelArrayAdapter);
        snackIntensity.setAdapter(dietLevelArrayAdapter);
        dinnerIntensity.setAdapter(dietLevelArrayAdapter);


        ArrayAdapter<CharSequence> dietTypeArrayAdapter = ArrayAdapter.createFromResource(this, R.array.current_diet_types, android.R.layout.simple_spinner_item);
        dietLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfastDiet.setPrompt("What's the type?");
        snackDiet.setPrompt("What's the type?");
        lunchDiet.setPrompt("What's the type?");
        dinnerDiet.setPrompt("What's the type?");

        breakfastDiet.setAdapter(dietTypeArrayAdapter);
        lunchDiet.setAdapter(dietTypeArrayAdapter);
        snackDiet.setAdapter(dietTypeArrayAdapter);
        dinnerDiet.setAdapter(dietTypeArrayAdapter);
    }

    private void setIDs() {
        breakfastIntensity.findViewById(R.id.breakfast_calorie_consumption);
        breakfastIntensity.findViewById(R.id.breakfast_calorie_consumption);
        breakfastIntensity.findViewById(R.id.breakfast_calorie_consumption);
        breakfastIntensity.findViewById(R.id.breakfast_calorie_consumption);

        breakfastDiet.findViewById(R.id.breakfast_diet_type);
        breakfastDiet.findViewById(R.id.breakfast_diet_type);
        breakfastDiet.findViewById(R.id.breakfast_diet_type);
        breakfastDiet.findViewById(R.id.breakfast_diet_type);

        breakfastImageButton.findViewById(R.id.breakfast_diet_info);
        breakfastImageButton.findViewById(R.id.breakfast_diet_info);
        breakfastImageButton.findViewById(R.id.breakfast_diet_info);
        breakfastImageButton.findViewById(R.id.breakfast_diet_info);

        next.findViewById(R.id.btn_complete_profile_next);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view == breakfastIntensity) {
            dietIntensityForBreakfast = parent.getItemAtPosition(position).toString();
        }

        if (view == lunchIntensity) {
            dietIntensityForLunch = parent.getItemAtPosition(position).toString();
        }

        if (view == snackIntensity) {
            dietIntensityForSnack = parent.getItemAtPosition(position).toString();
        }

        if (view == dinnerIntensity) {
            dietIntensityForDinner = parent.getItemAtPosition(position).toString();
        }

        if (view == breakfastDiet) {
            dietTypeForBreakfast = parent.getItemAtPosition(position).toString();
        }

        if (view == lunchDiet) {
            dietTypeForLunch = parent.getItemAtPosition(position).toString();
        }

        if (view == snackDiet) {
            dietTypeForSnack = parent.getItemAtPosition(position).toString();
        }

        if (view == dinnerDiet) {
            dietTypeForDinner = parent.getItemAtPosition(position).toString();
        }

        if (view == breakfastImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForBreakfast.isEmpty() || dietTypeForBreakfast.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToBreakFastImageButton();
            }
        }

        if (view == lunchImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForLunch.isEmpty() || dietTypeForLunch.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToLunchImageButton();
            }
        }

        if (view == snackImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietTypeForSnack.isEmpty() || dietTypeForSnack.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoTosnackImageButton();
            }

        }

        if (view == dinnerImageButton) {
            dietIntensityForBreakfast = breakfastIntensity.getSelectedItem().toString();
            dietTypeForBreakfast = breakfastIntensity.getSelectedItem().toString();

            if (dietIntensityForDinner.isEmpty() || dietTypeForDinner.isEmpty()) {
                notifyUserToChooseOption();
            } else {
                setInfoToDinnerImageButton();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }
}
