package com.eatfit.view.basicProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.UpdateNameModel.UpdateNameModel;
import com.eatfit.model.Validator;
import com.eatfit.model.updateUsername.UpdateUsernameModel;
import com.eatfit.presenter.User;
import com.eatfit.view.menu.MainMenuActivity;

public class UpdateUserName extends AppCompatActivity {

    Intent intent;
    User user;
    Button submit;
    TextView newText;
    String username;
    String newUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_name);


        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");
        Log.d("username: ",username);


        newText = findViewById(R.id.newUsernameText);
        submit = findViewById(R.id.submitNewUsername);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUsername = newText.getText().toString();
                if(newUsername.isEmpty()){
                    showErrorOnEmpty();
                }
                else{
                    Validator validator = new Validator();
                    boolean res = validator.validateEmail(newUsername);
                    if(res) {
                        UpdateUsernameModel updateUsernameModel = new UpdateUsernameModel(username,newUsername,UpdateUserName.this);
                        updateUsernameModel.authenticate();
                    }
                    else{
                        Toast.makeText(UpdateUserName.this,"please enter valid name",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showErrorOnEmpty(){
        Toast.makeText(UpdateUserName.this,"please enter name",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulUpdate(){
        Toast.makeText(UpdateUserName.this,"name updated",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateUserName.this, MainMenuActivity.class);
        user.setName(newUsername);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void onunSuccessfulUpdate(){
        Toast.makeText(UpdateUserName.this,"please try again later !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateUserName.this, MainMenuActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void onError(String error){
        Toast.makeText(UpdateUserName.this,"error: "+error,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateUserName.this, MainMenuActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
