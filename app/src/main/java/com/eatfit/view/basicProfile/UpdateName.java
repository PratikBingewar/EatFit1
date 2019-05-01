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
import com.eatfit.presenter.User;
import com.eatfit.view.menu.MainMenuActivity;

public class UpdateName extends AppCompatActivity {
    Intent intent;
    User user;
    Button submit;
    TextView newText;
    String username;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);


        intent = getIntent();
        username = (String) intent.getSerializableExtra("username");

        Log.d("username: ",username);

        newText = findViewById(R.id.newNameText);
        submit = findViewById(R.id.submitNewName);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = newText.getText().toString();
                if(name.isEmpty()){
                    showErrorOnEmpty();
                }
                else{
                    Validator validator = new Validator();
                    boolean res = validator.validateName(name);
                    if(res) {
                        UpdateNameModel updateNameModel = new UpdateNameModel(username,name,UpdateName.this);
                        updateNameModel.authenticate();
                    }
                    else{
                        Toast.makeText(UpdateName.this,"please enter valid name",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showErrorOnEmpty(){
        Toast.makeText(UpdateName.this,"please enter name",Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulUpdate(){
        Toast.makeText(UpdateName.this,"name updated",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateName.this, MainMenuActivity.class);
        user.setName(name);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void onunSuccessfulUpdate(){
        Toast.makeText(UpdateName.this,"please try again later !!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateName.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void onError(String error){
        Toast.makeText(UpdateName.this,"error: "+error,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateName.this, MainMenuActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
