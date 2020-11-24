package com.residencias.ficosec.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.residencias.ficosec.R;

public class SellerProductCategoryActivity extends AppCompatActivity {
    private ImageView tShirts, artesanias,cocina,pasteleria,reposteria,fertilizantes,saludbelleza,electronicos,hogar;

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
        hogar = (ImageView) findViewById(R.id.articulos_hogar);
        saludbelleza = (ImageView) findViewById(R.id.salud_belleza);
        electronicos = (ImageView) findViewById(R.id.electronica);


        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Ropa");
                startActivity(intent);
            }
        });
        artesanias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Artesanías");
                startActivity(intent);
            }
        });
        cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Cocina");
                startActivity(intent);
            }
        });
        pasteleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Pastelería");
                startActivity(intent);
            }
        });
        reposteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Repostería");
                startActivity(intent);
            }
        });
        fertilizantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Fertilizantes");
                startActivity(intent);
            }
        });
        hogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Artículos para el hogar");
                startActivity(intent);
            }
        });
        saludbelleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Salud y belleza");
                startActivity(intent);
            }
        });
        electronicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Electrónicos");
                startActivity(intent);
            }
        });

    }
}