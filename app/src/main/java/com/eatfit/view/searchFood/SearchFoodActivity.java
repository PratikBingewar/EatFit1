package com.eatfit.view.searchFood;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.food.CustomAdapter;
import com.eatfit.presenter.food.Food;
import com.eatfit.presenter.food.FoodRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SearchFoodActivity extends AppCompatActivity {

    InputStream inputStream;

    String[] ids;
    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    FoodRepo foodRepo ;
    static Integer NO_OF_DATA_ROWS = 276;

    private final static String TAG= SearchFoodActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        foodRepo = new FoodRepo(this);
        cursor=foodRepo.getFoodList();
        customAdapter = new CustomAdapter(SearchFoodActivity.this,  cursor, 0);
        listView = (ListView) findViewById(R.id.lstStudent);
        listView.setAdapter(customAdapter);

        if(cursor==null) {
            try {
                insertDummy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertDummy() throws IOException {

        Food food[] = new Food[500];

        inputStream = getResources().openRawResource(R.raw.sample);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {



                ids=csvLine.split(",");
                try{

                    for (int i = 0; i < NO_OF_DATA_ROWS; i++) {
                        food[i] = new Food();
                        Log.e("name ",""+ids[0]) ;
                        Log.d("caloires",ids[1]);
                        food[i].calories = Double.parseDouble(ids[1]);
                        food[i].name= ids[0];
                        foodRepo.insert(food[i]);
                    }


                }catch (Exception e){
                    Log.e("error : ",e.toString());
                }
            }




        }
        catch (IOException ex) {
            Log.e("error : ",ex.toString());
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor= foodRepo.getFoodByKeyword(s);
                    if (cursor==null){
                        Toast.makeText(SearchFoodActivity.this,"No records found!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SearchFoodActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor=foodRepo.getFoodByKeyword(s);
                    if (cursor!=null){
                        customAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });

        }

        return true;

    }

}
