package com.eatfit.view.basicProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.model.Validator;
import com.eatfit.model.updatePasswordModel.UpdatePasswordModel;
import com.eatfit.presenter.User;
import com.eatfit.view.menu.MainMenuActivity;

public class UpdatePassword extends AppCompatActivity {
    Intent intent;
    User user;
    Button submit;
    TextView newPassword;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);


        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");
        Log.d("username: ",username);

        newPassword = findViewById(R.id.newPasswordText);
        submit = findViewById(R.id.submitNewPassword);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = newPassword.getText().toString();
                if(password.isEmpty()){
                    showErrorOnEmpty();
                }
                else{
                    Validator validator = new Validator();
                    boolean res = validator.validatePassword(password);
                    if(res) {
                        UpdatePasswordModel updatePasswordModel = new UpdatePasswordModel(username,password,UpdatePassword.this);
                        updatePasswordModel.authenticate();
                    }
                    else{
                        Toast.makeText(UpdatePassword.this,"please enter valid password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showErrorOnEmpty(){
        Toast.makeText(UpdatePassword.this,"please enter password",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulUpdate(){
        Toast.makeText(UpdatePassword.this,"password updated",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdatePassword.this, MainMenuActivity.class);
        user.setPassword(password);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void onunSuccessfulUpdate(){
        Toast.makeText(UpdatePassword.this,"please try again later !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdatePassword.this, MainMenuActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void onError(String error){
        Toast.makeText(UpdatePassword.this,"error: "+error,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdatePassword.this, MainMenuActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}