package com.residencias.ficosec.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.residencias.ficosec.R;

public class SellerProductCategoryActivity extends AppCompatActivity {
    private ImageView tShirts, artesanias,cocina,pasteleria,reposteria,fertilizantes;

   // private Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_category);

        tShirts = (ImageView) findViewById(R.id.t_shirts);
        artesanias = (ImageView) findViewById(R.id.artesanias);
        cocina = (ImageView) findViewById(R.id.cocina);
        pasteleria = (ImageView) findViewById(R.id.pasteleria);
        reposteria = (ImageView) findViewById(R.id.reposteria);
        fertilizantes = (ImageView) findViewById(R.id.fertilizantes);


        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "ropa");
                startActivity(intent);
            }
        });
        artesanias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "artesanias");
                startActivity(intent);
            }
        });
        cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "cocina");
                startActivity(intent);
            }
        });
        pasteleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "pasteleria");
                startActivity(intent);
            }
        });
        reposteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "reposteria");
                startActivity(intent);
            }
        });
        fertilizantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "fertilizantes");
                startActivity(intent);
            }
        });

    }
}