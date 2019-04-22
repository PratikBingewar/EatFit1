package com.eatfit.view.searchFood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.User;
import com.eatfit.presenter.holder.FoodHolder;
import com.eatfit.view.addFood.AddFoodActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchFoodActivity extends AppCompatActivity implements SearchView.OnSuggestionListener, AdapterView.OnItemClickListener {

    SearchView searchView;
    ListView listView;
    ArrayList<String> listNames;
    ArrayList<FoodHolder> listObject;
    ArrayAdapter<String > adapter;
    InputStream inputStream;
    String[] ids;
    FoodHolder selectedFood;
    User user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.lv1);

        setList();

        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        searchView.setOnSuggestionListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (listNames.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(SearchFoodActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (listNames.contains(newText)) {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }
    private void setList() {
        inputStream = getResources().openRawResource(R.raw.data);
        listNames = new ArrayList<String>();
        listObject = new ArrayList<FoodHolder>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            bufferedReader.readLine();
            while((csvLine = bufferedReader.readLine()) != null) {

                ids = csvLine.split(",");
                listNames.add(ids[1].toLowerCase());
                Log.d("data: ",ids[0]+" "+ids[1]+" "+ids[2]+" "+ids[3]+" "+ids[4]+" "+ids[5]+" "+ids[6]+" "+ids[7]);

                FoodHolder foodHolder = new FoodHolder(Integer.valueOf(ids[0]),ids[1].toLowerCase(),Double.valueOf(ids[2]),Double.valueOf(ids[3]),Double.valueOf(ids[4]),ids[5],Double.valueOf(ids[6]),Double.valueOf(ids[7]));
                Log.d("Object: ",foodHolder.toString());
                listObject.add(foodHolder);
            }
        }
        catch (Exception e){
            Log.d("exp: ",e+"");
        }
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        Cursor cursor= searchView.getSuggestionsAdapter().getCursor();
        cursor.moveToPosition(position);
        String suggestion =cursor.getString(position);
        Toast.makeText(SearchFoodActivity.this,"name "+suggestion,Toast.LENGTH_SHORT).show();

        Set<FoodHolder> set = new HashSet<>(listObject);
        for (FoodHolder temp : set) {
            if(temp.getName().equals(suggestion)){
                Log.d("info: ",temp.toString());
            }
        }
        searchView.setQuery(suggestion,true);//setting suggestion
        return true;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        Cursor cursor= searchView.getSuggestionsAdapter().getCursor();
        cursor.moveToPosition(position);
        String suggestion =cursor.getString(position);
        Toast.makeText(SearchFoodActivity.this,"name "+suggestion,Toast.LENGTH_SHORT).show();


        searchView.setQuery(suggestion,true);//setting suggestion
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = listView.getItemAtPosition(position).toString();

        Toast.makeText(SearchFoodActivity.this,"selected: "+value,Toast.LENGTH_SHORT).show();
        Set<FoodHolder> set = new HashSet<>(listObject);
        for (FoodHolder selected : set) {
            if(selected.getName().equals(value)){
                Log.d("info: ",selected.toString());
                selectedFood = selected;
            }
        }
        Toast.makeText(SearchFoodActivity.this,"selected: "+selectedFood.getName()+" "
                +selectedFood.getCarbs()+" "
                +selectedFood.getProtein()+" "
                +selectedFood.getFat()+" "
                +selectedFood.getUnitCal()+" "
                +selectedFood.getWeight()+" "
                +selectedFood.getServingUnit()+" ",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SearchFoodActivity.this, AddFoodActivity.class);
        intent.putExtra("name",selectedFood.getName());
        intent.putExtra("carbs",selectedFood.getCarbs());
        intent.putExtra("fats",selectedFood.getFat());
        intent.putExtra("proteins",selectedFood.getProtein());
        intent.putExtra("unit_cal",selectedFood.getUnitCal());
        intent.putExtra("unit",selectedFood.getServingUnit());
        intent.putExtra("user",user);
        String[] splited = selectedFood.getServingUnit().split("\\s+");

        String split_one=splited[0];
        String split_second=splited[1];

        Log.d("Splited String ", "Splited String" + split_one+split_second);

        intent.putExtra("unit",split_second);
        startActivity(intent);
    }
}
