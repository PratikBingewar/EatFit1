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
import android.widget.TextView;
import android.widget.Toast;

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

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    User user;
    TextView nameOnMainMenu,emailOnMainMenu;
    String name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        nameOnMainMenu = findViewById(R.id.name_on_main_menu);
        emailOnMainMenu = findViewById(R.id.username_on_main_menu);

        intent = getIntent();

        user = (User) intent.getSerializableExtra("user");
        Log.d("username ",user.getUsername());
        Log.d("password ",user.getPassword());
        getUserInfoViaModel(user);
//        intent = getIntent();
//        user = (User) intent.getSerializableExtra("user");
//        name = user.getName().toString();
//        email = user.getUsername().toString();
//        Log.d("name and email",name+" "+email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
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

    private void getUserInfoViaModel(User user) {

        GetUserInfoModel getUserInfoModel = new GetUserInfoModel(user,this);
        getUserInfoModel.getUserObject();
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
            intent.putExtra("user",user);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.suggest_food_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, FoodSuggestionActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.add_food_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, SearchFoodActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.details_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, DetailsActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.reminder_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, SetReminderActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.change_current_weight_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, ChangeWeightActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.change_current_fitness_goal_on_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, ChangeFitnessGoalActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        } else if (id == R.id.logout_main_menu_drawer) {
            Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
//        nameOnMainMenu.setText(name);
//        emailOnMainMenu.setText(email);
        return true;
    }

    public void onWelComeToast() {
        Toast.makeText(MainMenuActivity.this,"!! Welcome to EatFit !!", Toast.LENGTH_SHORT).show();
    }
}
