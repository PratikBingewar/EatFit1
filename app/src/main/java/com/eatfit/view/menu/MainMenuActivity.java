package com.eatfit.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.getUserData.GetUserDataModel;
import com.eatfit.model.getUserInfo.GetUserInfoModel;
import com.eatfit.presenter.User;
import com.eatfit.view.basicProfile.BasicProfileActivity;
import com.eatfit.view.changeFitnessGoal.ChangeFitnessGoalActivity;
import com.eatfit.view.changeWeight.ChangeWeightActivity;
import com.eatfit.view.login.LoginActivity;
import com.eatfit.view.searchFood.SearchFoodActivity;
import com.eatfit.view.suggestFood.FoodSuggestionActivity;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {


    Intent intent;
    TextView nameOnMainMenu,emailOnMainMenu;
    String name, email;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    String username;
    User user;
    Integer progress;
    ProgressBar progressBar;
    TextView progressInPercentage;
    Double userCalorieGoal,Increment;
    int percentage;
    TextView totalCalories;
    FloatingActionButton fab;
    String isNewUser;
    TextView userNameOnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");
        isNewUser = (String) intent.getSerializableExtra("new user");

        Log.d("username in main menu: ",username);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GetUserDataModel getUserDataModel = new GetUserDataModel(username,this);
        getUserDataModel.getUerInfo();

        Toast.makeText(MainMenuActivity.this,"Got all foods !!! ", Toast.LENGTH_SHORT);

    }

    private void setProgressOgBar(Integer progress) {

        userCalorieGoal = user.calorieGoal;

        percentage = (int) ((progress/userCalorieGoal)*100);
        progressBar.setProgress(percentage);
        progressInPercentage.setText(percentage+" %");
        totalCalories.setText(progress+" out of "+userCalorieGoal+" Cal");
    }


    public void onfailedInternetConnection() {
        Toast.makeText(MainMenuActivity.this,"Couldnt create usernaem in food table!!",Toast.LENGTH_SHORT).show();

    }

    public void setUserInfo(String[] token, double calorie_consumption) {
        list = new ArrayList<>(500);

        if(calorie_consumption != 0) {
            Log.d("length ", token.length + "");
            for (int i = 0; i < token.length ; i++) {
                list.add(token[i]);
                Log.d("food name in main menu:" + i, token[i]);
            }
        }
        progress = (int) calorie_consumption;
        Log.d("progress ", progress+"");

        GetUserInfoModel getUserInfoModel = new GetUserInfoModel(username,this);
        getUserInfoModel.getUserObject();

        Toast.makeText(MainMenuActivity.this,"Got user info !!! ", Toast.LENGTH_SHORT);
        fab.setEnabled(true);
        Log.d("fab enabled: ","now add");
    }

    @Override
    public void onBackPressed() {
        nameOnMainMenu.setText(name);
        emailOnMainMenu.setText(username);
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
            Intent intent = new Intent(MainMenuActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
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
            intent.putExtra("list",list);
            intent.putExtra("fitnessGoalID",user.getFitnessGoalID());
            intent.putExtra("calorie_goal",user.getCalorieGoal());
            intent.putExtra("progress",progress);
            startActivity(intent);
        } else if (id == R.id.add_food_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
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


    public void onWelComeToast(User user) {
        this.user = user;
        Log.d("RESPONSE ",name+" "+user.gender+" "+user.age+" "+user.height+" "+user.weight+" "+
                user.BMI+" "+user.BMR+
                " "+user.calorieGoal+" "+user.incrementInCalorieGoal+" "+user.breakfastIntake+" "+
                user.lunchIntake+" "+user.snackIntake+
                " "+user.dinnerIntake+
                " "+user.timeToReachGoal+" "+user.intensityID+" "+user.fitnessGoalID+" "+user.activityID);

        Log.d("user created: ","now add");

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        nameOnMainMenu = findViewById(R.id.name_on_main_menu);
        userNameOnMainMenu = findViewById(R.id.username_on_main_menu);

        nameOnMainMenu.setText(user.getName());
        userNameOnMainMenu.setText(user.getUsername());

        totalCalories = findViewById(R.id.total_calories);
        nameOnMainMenu = findViewById(R.id.name_on_main_menu);
        emailOnMainMenu = findViewById(R.id.username_on_main_menu);
        progressBar = findViewById(R.id.progressBar);

        progressInPercentage = findViewById(R.id.progress_in_percentage);

        if(progress == null) {
            Log.d("null progress","0");
            progress = 0;
        }

        setProgressOgBar(progress);
    }



    public void printAllStrings(String[] token) {
//        for (int i=0 ; i<token.length ; i++){
//            Toast.makeText(MainMenuActivity.this,(i+1)+" "+token[i],Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {
            Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        }
    }
}
