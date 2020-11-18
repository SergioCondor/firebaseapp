package com.residencias.ficosec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AdminMaintainProductsActivity extends AppCompatActivity {

    private EditText name, price, description;
    private Button applyChangesBtn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);
        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        price = findViewById(R.id.product_price);
        name = findViewById(R.id.product_name);
        description = findViewById(R.id.product_description);
    }
}