package com.eatfit.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eatfit.R;
import com.eatfit.model.getUserInfo.GetUserInfoModel;
import com.eatfit.presenter.User;
import com.eatfit.view.addFood.AddFoodActivity;
import com.eatfit.view.basicProfile.BasicProfileActivity;
import com.eatfit.view.changeFitnessGoal.ChangeFitnessGoalActivity;
import com.eatfit.view.changeWeight.ChangeWeightActivity;
import com.eatfit.view.details.DetailsActivity;
import com.eatfit.view.login.LoginActivity;
import com.eatfit.view.reminder.SetReminderActivity;
import com.eatfit.view.searchFood.SearchFoodActivity;
import com.eatfit.view.suggestFood.FoodSuggestionActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final String  LOGIN_URL = "https://eatfit223.000webhostapp.com/volley/getUsreInfo.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;

    Intent intent;
    TextView nameOnMainMenu,emailOnMainMenu;
    String name, email;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        nameOnMainMenu = findViewById(R.id.name_on_main_menu);
        emailOnMainMenu = findViewById(R.id.username_on_main_menu);


        intent = getIntent();
        username = (String) intent.getSerializableExtra("user");
        Log.d("main menu username: ",username);


        getUserInfo();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void getUserInfo() {
        requestQueue = Volley.newRequestQueue( this);
        getContent();
    }

    private void getContent() {
        Log.d("pratik","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Pratik","In volley error");
                        changeConnectionStatus(false);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",username);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void changeConnectionStatus(boolean val) {
        CONNECTION_STATUS = val;
    }

    public void checkResponse(String response){
        try {
            JSONObject obj = new JSONObject(response);

            String id = obj.getString("user_id");
            Log.d("user id: ", id);

            String password = obj.getString("password");
            Log.d("user password: ", password);

            String gender = obj.getString("gender");
            Log.d("user gender: ", gender);

            String age = obj.getString("age");
            Log.d("user age: ", age);

            String weight = obj.getString("weight");
            Log.d("user weight: ", weight);

            String height = obj.getString("height");
            Log.d("user height: ", height);

            String BMI = obj.getString("BMI");
            Log.d("user BMI: ", BMI);

            String BMR = obj.getString("BMR");
            Log.d("user BMR: ", BMR);

            String calorie_goal = obj.getString("calorie_goal");
            Log.d("user calorie_goal: ", calorie_goal);

            String increment = obj.getString("increment");
            Log.d("user increment: ", increment);

            String breakfast_intake = obj.getString("breakfast_intake");
            Log.d("user breakfast_intake: ", breakfast_intake);

            String lunch_intake = obj.getString("lunch_intake");
            Log.d("user lunch_intake: ", lunch_intake);

            String snack_intake = obj.getString("snack_intake");
            Log.d("user snack_intake: ", snack_intake);

            String dinner_intake = obj.getString("dinner_intake");
            Log.d("user dinner_intake: ", dinner_intake);

            String time_to_reach_goal = obj.getString("time_to_reach_goal");
            Log.d("user time", time_to_reach_goal);

            String intensity_id = obj.getString("intensity_id");
            Log.d("user intensity_id: ", intensity_id);

            String fitness_goal_id = obj.getString("fitness_goal_id");
            Log.d("user fitness_goal_id: ", fitness_goal_id);

            String activity_id = obj.getString("activity_id");
            Log.d("user activity_id: ", activity_id);


        } catch (Exception ex) {
            Log.d("Pratik : ","In exception "+ex);
            changeConnectionStatus(false);
        }
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

    @Override
    public void onBackPressed() {
        nameOnMainMenu.setText(name);
        emailOnMainMenu.setText(email);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.basic_profile_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, BasicProfileActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.suggest_food_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, FoodSuggestionActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.add_food_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.details_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, DetailsActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.reminder_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, SetReminderActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.change_current_weight_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, ChangeWeightActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.change_current_fitness_goal_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, ChangeFitnessGoalActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        } else if (id == R.id.logout_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onWelComeToast() {
        Toast.makeText(MainMenuActivity.this,"!! Welcome to EatFit !!", Toast.LENGTH_SHORT).show();
    }
}
