package com.residencias.ficosec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainProductsActivity extends AppCompatActivity {

    private EditText name, price, description;
    private Button applyChangesBtn;
    private ImageView imageView;

    private String productID = "";
    private DatabaseReference productsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);

        productID = getIntent().getStringExtra("pid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        price = findViewById(R.id.product_price_maintain);
        name = findViewById(R.id.product_name_maintain);
        description = findViewById(R.id.product_description_maintain);
        imageView = findViewById(R.id.product_image_maintain);

        displaySpecificProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });
    }

    private void applyChanges() {
        String putoName = name.getText().toString();
        String putoPrice = price.getText().toString();
        String putoDescription = description.getText().toString();

        if (putoName.equals("")){
            Toast.makeText(this, "Porfavor escriba el nombre del producto", Toast.LENGTH_SHORT).show();
        }else if (putoPrice.equals("")){
            Toast.makeText(this, "Porfavor escriba el precio del producto", Toast.LENGTH_SHORT).show();
        }else if (putoDescription.equals("")){
            Toast.makeText(this, "Porfavor escriba la descripción del producto", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("description", putoDescription);
            productMap.put("price", putoPrice);
            productMap.put("pname", putoName);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AdminMaintainProductsActivity.this, "Cambios aplicados correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void displaySpecificProductInfo() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String pinchiName = snapshot.child("pname").getValue().toString();
                    String pinchiPrice = snapshot.child("price").getValue().toString();
                    String pinchiDescription = snapshot.child("description").getValue().toString();
                    String pinchiImage = snapshot.child("image").getValue().toString();

                    name.setText(pinchiName);
                    price.setText(pinchiPrice);
                    description.setText(pinchiDescription);
                    Picasso.get().load(pinchiImage).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}