package com.eatfit.view.searchFood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.eatfit.R;

import java.util.ArrayList;

public class SearchFoodActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        searchView =  findViewById(R.id.searchView);
        listView =  findViewById(R.id.lv1);

        getList();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(SearchFoodActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                String selectedItem = (String)adapter.getItem(position);
                Toast.makeText(SearchFoodActivity.this,"Item Seleceted: "+selectedItem,Toast.LENGTH_SHORT).show();
                return true;
            }
        })  ;
    }

    private void getList() {
        list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("pineapple");
        list.add("orange");
        list.add("lychee");
        list.add("gavava");
        list.add("peech");
        list.add("melon");
        list.add("watermelon");
        list.add("papaya");
    }
}
