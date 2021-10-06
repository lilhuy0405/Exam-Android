package com.t1908e.examandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private Button saveBtn;
    private Button viewBtn;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        saveBtn = findViewById(R.id.btnAdd);
        viewBtn = findViewById(R.id.btnView);
        saveBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnView:
                ViewProducts();
                break;
            case R.id.btnAdd:
                AddProduct();
                break;
            default:
                break;
        }
    }

    private void AddProduct() {
        if(edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter product name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter product quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity = -1;
        try {
            quantity = Integer.parseInt(edQuantity.getText().toString());
        } catch (Exception exception) {
            quantity = -1;
        }
        if(quantity < 0) {
            Toast.makeText(this, "Plz enter an valid qty", Toast.LENGTH_SHORT).show();
            return;
        }
        String isAdd = db.addProduct(edName.getText().toString(), quantity);
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();


    }

    private void ViewProducts() {
        Intent intent = new Intent(MainActivity.this, ActivityListProduct.class);
        startActivity(intent);
    }
}