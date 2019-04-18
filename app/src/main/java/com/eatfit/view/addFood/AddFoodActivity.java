package com.eatfit.view.addFood;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import com.eatfit.R;
import com.eatfit.presenter.holder.FoodHolder;

public class AddFoodActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Boolean EditTextEmptyHold;
    SearchView searchFood;
    ListView listView;
    ArrayList<String> listNames;
    ArrayList<FoodHolder> listObject;
    ArrayAdapter<String > adapter;
    InputStream inputStream;
    String[] ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        searchFood = findViewById(R.id.food_search_bar);

        listView = (ListView) findViewById(R.id.lv1);

        inputStream = getResources().openRawResource(R.raw.data);
        listNames = new ArrayList<String>();
        listObject = new ArrayList<FoodHolder>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;

            while((csvLine = bufferedReader.readLine()) != null) {

                ids = csvLine.split(",");
                listNames.add(ids[1]);
                Log.d("data: ",ids[0]+" "+ids[1]+" "+ids[2]+" "+ids[3]+" "+ids[4]+" "+ids[5]+" "+ids[6]+" "+ids[7]);

                FoodHolder foodHolder = new FoodHolder(Integer.valueOf(ids[0]),ids[1],Double.valueOf(ids[2]),Double.valueOf(ids[3]),Double.valueOf(ids[4]),ids[5],Double.valueOf(ids[6]),Double.valueOf(ids[7]));
                Log.d("Object: ",foodHolder.toString());
                listObject.add(foodHolder);
            }
        }
        catch (Exception e){
            Log.d("exp: ",e+"");
        }



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNames);
        listView.setAdapter(adapter);

        searchFood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(listNames.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(AddFoodActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}
