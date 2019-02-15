package com.eatfit.view.registration.setHeightAndWeight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eatfit.R;
import com.eatfit.presenter.heightAndWeight.HWPresenter;
import com.eatfit.presenter.heightAndWeight.IHWPresenter;

public class SetHeightAndWeightActivity extends AppCompatActivity implements ISetHeightAndWeightView, View.OnClickListener {

    EditText edtHeight,edtWeight;
    Button btnNext;
    IHWPresenter ihwPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_height_and_weight);

        edtHeight = findViewById(R.id.input_height);
        edtWeight = findViewById(R.id.input_weight);
        btnNext = findViewById(R.id.btn_height_weight_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onHeightError() {
        edtHeight.setError("Invalid Height");
    }

    @Override
    public void onWeightError() {
        edtWeight.setError("Invalid Weight");
    }

    @Override
    public void onEmptyFieldsError() {
        Toast.makeText(SetHeightAndWeightActivity.this,"Invalid Height or Weight",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessFulInput() {
        Intent intent = getIntent();
        intent.putExtra("height",edtHeight.getText());
        intent.putExtra("weight",edtWeight.getText());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(edtWeight.getText().toString().isEmpty() || edtHeight.getText().toString().isEmpty()) {
            onEmptyFieldsError();
        }
        int height = Integer.parseInt(edtHeight.getText().toString());
        int weight = Integer.parseInt(edtWeight.getText().toString());
        ihwPresenter = new HWPresenter(this);
        ihwPresenter.validate(height,weight);
    }
}
