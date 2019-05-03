package com.eatfit.view.suggestFood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.holder.FoodHolder;
import com.eatfit.view.addFood.AddFoodActivity;

import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FoodSuggestionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView viewCondition;
    ListView listView;
    ArrayList<String> currentFoodList;
    int goalID;
    Double totalCal;
    String FitnessGoal;
    int carbPart, proteinPart, fatPart;
    double carbCal=0,proteinCal=0,fatCal=0;
    int progress;
    int sector;
    double difference;
    InputStream inputStream;
    double currentGoalCal;
    ArrayList<String> foodListNames;
    ArrayList<FoodHolder> listObject;
    String[] ids;
    ArrayList<FoodHolder> modifiedList;
    Double carbSuggestedPortion, proteinSuggestedPortion, fatSuggestedPortion;
    Double carbCurrentPortion, proteinCurrentPortion, fatCurrentPortion;
    Double carbDifference, proteinDifference, fatDifference;
    ArrayAdapter<String > adapter;
    ArrayList<String> finalList = new ArrayList<>();
    Intent intent;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_suggestion);
        listView = findViewById(R.id.listOfFoods);
        viewCondition = findViewById(R.id.addressToUser);

        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");
        Log.d("suggest food username: ",username);

        currentFoodList = (ArrayList<String>) getIntent().getSerializableExtra("list");
        goalID = (Integer) getIntent().getSerializableExtra("fitnessGoalID");
        setFitnessGoal(goalID);
        totalCal = (Double) getIntent().getSerializableExtra("calorie_goal");
        progress = (int) getIntent().getSerializableExtra("progress");

        calcualteCaloriesOfNutrients();
        getDataFromList();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, finalList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void getDataFromList() {
        inputStream = getResources().openRawResource(R.raw.data);
        foodListNames = new ArrayList<String>();
        listObject = new ArrayList<FoodHolder>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            bufferedReader.readLine();
            while((csvLine = bufferedReader.readLine()) != null) {

                ids = csvLine.split(",");
                foodListNames.add(ids[1].toLowerCase());
                FoodHolder foodHolder = new FoodHolder(Integer.valueOf(ids[0]),ids[1].toLowerCase(),Double.valueOf(ids[2]),Double.valueOf(ids[3]),Double.valueOf(ids[4]),ids[5],Double.valueOf(ids[6]),Double.valueOf(ids[7]));
                listObject.add(foodHolder);
            }

        }
        catch (Exception e){
            Log.d("exp: ",e+"");
        }

        ArrayList<String> commonList = (ArrayList) CollectionUtils.retainAll(foodListNames,currentFoodList);

        Log.d("final list: ",commonList.toString());


        Set<String> set = new HashSet<>(commonList);
        commonList.clear();
        commonList.addAll(set);
        Log.d("with dup list: ",commonList.toString());

        for (int i = 0 ; i < commonList.size(); i++) {
            for (int j=0; j < listObject.size(); j++) {
                FoodHolder foodHolder = listObject.get(j);
                if(foodHolder.getName().equals(commonList.get(i))) {
                    Log.d("food matched: ",foodHolder.getName());
                    carbCal = carbCal + Double.valueOf(foodHolder.getCarbs());
                    proteinCal = proteinCal + Double.valueOf(foodHolder.getProtein());
                    fatCal = fatCal + Double.valueOf(foodHolder.getFat());
                    break;
                }
            }
        }
        Log.d("indiv cal: ",carbCal+" "+proteinCal+" "+fatCal);
        carbCurrentPortion = carbCal*4;
        proteinCurrentPortion = proteinCal*4;
        fatCurrentPortion = fatCal*4;

        Log.d("current","carbs "+carbCurrentPortion);
        Log.d("current","protein "+proteinCurrentPortion);
        Log.d("current","fat "+fatCurrentPortion);

        Log.d("total curent",carbCurrentPortion+proteinCurrentPortion+fatCurrentPortion+"");
        calculateGapsPerNutrient();
    }

    private void calculateGapsPerNutrient() {
        if(goalID ==1 || goalID == 2) {
            carbSuggestedPortion = (currentGoalCal / 100) * 40;
            proteinSuggestedPortion = (currentGoalCal / 100) * 40;
            fatSuggestedPortion = (currentGoalCal / 100) * 20;
        }
        else {
            carbSuggestedPortion = (currentGoalCal / 100) * 35;
            proteinSuggestedPortion = (currentGoalCal / 100) * 35;
            fatSuggestedPortion = (currentGoalCal / 100) * 20;
        }

        Log.d("suggested","carbs "+carbSuggestedPortion);
        Log.d("suggested","protein "+proteinSuggestedPortion);
        Log.d("suggested","fat "+fatSuggestedPortion);

        carbDifference = carbSuggestedPortion - carbCurrentPortion;
        proteinDifference = proteinSuggestedPortion - proteinCurrentPortion;
        fatDifference = fatSuggestedPortion - fatCurrentPortion;

        Log.d("differences","carbs "+carbDifference);
        Log.d("differences","protein "+proteinDifference);
        Log.d("differences","fat "+fatDifference);

        if(proteinDifference > fatDifference && proteinDifference > carbDifference) {
            getProteinRichFoods();
        } else if(fatDifference > proteinDifference && fatDifference > carbDifference) {
            getFatRichFoods();
        }else {
            getCarbRichFoods();
        }
    }

    private void getCarbRichFoods() {
        viewCondition.setText("Your body is \n carb deficient right now\nTry these foods");
        modifiedList = listObject;
        for (int i = 0 ; i < modifiedList.size()-1 ; i++) {
            for (int j = 0 ; j  < (modifiedList.size() - i - 1) ; j++) {
                FoodHolder foodHolder1 = listObject.get(j);
                FoodHolder foodHolder2 = listObject.get(j+1);

                if(foodHolder1.getCarbs() < foodHolder2.getCarbs()){
                    FoodHolder foodHolder = listObject.get(j);
                    listObject.set(j,listObject.get(j+1));
                    listObject.set(j+1,foodHolder);
                }
            }
        }

        for(int i = 0 ;i  < modifiedList.size(); i ++) {
            finalList.add(modifiedList.get(i).getName());
            Log.d("Sorted carbs: ",modifiedList.get(i).getCarbs()+" "+ modifiedList.get(i).getName() );
        }
    }

    private void getFatRichFoods() {
        viewCondition.setText("Your body is \n fat deficient right now\nTry these foods");
        modifiedList = listObject;
        for (int i = 0 ; i < modifiedList.size()-1 ; i++) {
            for (int j = 0 ; j  < (modifiedList.size() - i - 1) ; j++) {
                FoodHolder foodHolder1 = listObject.get(j);
                FoodHolder foodHolder2 = listObject.get(j+1);

                if(foodHolder1.getFat() < foodHolder2.getFat()){
                    FoodHolder foodHolder = listObject.get(j);
                    listObject.set(j,listObject.get(j+1));
                    listObject.set(j+1,foodHolder);
                }
            }
        }

        for(int i = 0 ;i  < modifiedList.size(); i ++) {
            finalList.add(modifiedList.get(i).getName());
            Log.d("Sorted carbs: ",modifiedList.get(i).getFat()+" "+ modifiedList.get(i).getName() );
        }
    }

    private void getProteinRichFoods() {
        viewCondition.setText("Your body is \n protein deficient right now\nTry these foods");
        modifiedList = listObject;
        for (int i = 0 ; i < modifiedList.size()-1 ; i++) {
            for (int j = 0 ; j  < (modifiedList.size() - i - 1) ; j++) {
                FoodHolder foodHolder1 = listObject.get(j);
                FoodHolder foodHolder2 = listObject.get(j+1);

                if(foodHolder1.getProtein() < foodHolder2.getProtein()){
                    FoodHolder foodHolder = listObject.get(j);
                    listObject.set(j,listObject.get(j+1));
                    listObject.set(j+1,foodHolder);
                }
            }
        }

        for(int i = 0 ;i  < modifiedList.size(); i ++) {
            finalList.add(modifiedList.get(i).getName());
            Log.d("Sorted carbs: ",modifiedList.get(i).getProtein()+" "+ modifiedList.get(i).getName() );
        }
    }

    private void calcualteCaloriesOfNutrients() {
        double currentCalPart = totalCal/4;
        if(currentCalPart > progress) {
            sector = 1;
            currentGoalCal = currentCalPart;
        }
        else if(currentCalPart < progress && currentCalPart*2  > progress) {
            sector = 2;
            currentGoalCal = currentCalPart * 2;
        }
        else if(currentCalPart*2 < progress && currentCalPart*3  > progress) {
            sector = 3;
            currentGoalCal = currentCalPart * 3;
        }
        else if(currentCalPart*3 < progress && currentCalPart*4  > progress) {
            sector = 4;
            currentGoalCal = totalCal;
        }
        difference = currentGoalCal - progress;

        Log.d("calculations: ","totalCal "+totalCal+" progress"+progress+" current goal"+currentGoalCal+" currentCalPart"+currentCalPart+" sector"+sector);
    }


    private void setFitnessGoal(int goalID) {
        if(goalID == 1) {
            FitnessGoal = "weight loss";
            carbPart = 40;
            proteinPart = 40;
            fatPart = 20;
        } else if(goalID == 2) {
            FitnessGoal = "muscle gain";
            carbPart = 40;
            proteinPart = 40;
            fatPart = 20;
        }
        else{
            FitnessGoal = "stay fit";
            carbPart = 35;
            proteinPart = 35;
            fatPart = 30;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = listView.getItemAtPosition(position).toString();

        Toast.makeText(FoodSuggestionActivity.this,"selected: "+value,Toast.LENGTH_SHORT).show();

        String name = "";
        Double carbs = 0.0;
        Double protein = 0.0;
        Double fat = 0.0;
        String unit = "";

        for(int i = 0 ; i < listObject.size() ; i ++) {

            if(listObject.get(i).getName().equals(value)) {
                FoodHolder foodHolder = listObject.get(i);
                name = foodHolder.getName();
                Log.d("carbs: ",foodHolder.getCarbs()+"");
                carbs = foodHolder.getCarbs();
                Log.d("protein: ",foodHolder.getProtein()+"");
                protein = foodHolder.getProtein();
                Log.d("fat: ",foodHolder.getFat()+"");
                fat = foodHolder.getFat();
                Log.d("unit: ",foodHolder.getServingUnit()+"");
                unit = foodHolder.getServingUnit();
                break;
            }
        }

        Intent intent = new Intent(FoodSuggestionActivity.this, AddFoodActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("carbs",carbs);
        intent.putExtra("fats",fat);
        intent.putExtra("proteins",protein);
        intent.putExtra("username",username);
        String[] splited = unit.split("\\s+");

        String split_one=splited[0];
        String split_second=splited[1];

        Log.d("Splited String ", "Splited String" + split_one+split_second);

        intent.putExtra("unit",split_second);
        startActivity(intent);
    }

}
