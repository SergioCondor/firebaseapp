package com.residencias.ficosec.Buyers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.residencias.ficosec.Admin.AdminMaintainProductsActivity;
import com.residencias.ficosec.Model.Products;
import com.residencias.ficosec.Prevalent.Prevalent;
import com.residencias.ficosec.R;
import com.residencias.ficosec.ViewHolder.ProductViewHolder;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

import static com.residencias.ficosec.R.id.nav_cart;
import static com.residencias.ficosec.R.id.nav_logout;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    private AppBarConfiguration mAppBarConfiguration;

    private String type = "";

    //private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            type = getIntent().getExtras().get("Admin").toString();
        }

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Paper.init(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Inicio");
        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       // profileImageView = findViewById(R.id.user_profile_image);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("Admin")){
                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);
                }

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        ImageView profileImageView = headerView.findViewById(R.id.user_profile_image);
        if (!type.equals("Admin")){
            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        }
      // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case nav_cart:
                        if (!type.equals("Admin")){
                            Intent intentos = new Intent(HomeActivity.this, CartActivity.class);
                            startActivity(intentos);
                        }

                        break;
                    case R.id.nav_search:
                        if (!type.equals("Admin")){
                            Intent intentarse = new Intent(HomeActivity.this, SearchProductsActivity.class);
                            startActivity(intentarse);
                        }

                        break;
                    case R.id.nav_categories:


                        break;
                    case R.id.nav_settings:
                        if (!type.equals("Admin")){
                            Intent intentar = new Intent(HomeActivity.this, SettingsActivity.class);
                            startActivity(intentar);
                        }

                        break;
                    case nav_logout:
                        if (!type.equals("Admin")){
                            Paper.book().destroy();
                            Intent intento = new Intent(HomeActivity.this, MainActivity.class);
                            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intento);
                            finish();
                        }
                        //Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>().setQuery(ProductsRef, Products.class).build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull final Products model) {
               holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText("Precio = " + model.getPrice()+"$");
                Picasso.get().load(model.getImage()).into(holder.imageView);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type.equals("Admin")){
                            Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                            intent.putExtra("pid",model.getPid());
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("pid",model.getPid());
                            startActivity(intent);
                        }

                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

   @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item){

       // int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.nav_search :
               // Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_categories:
               // Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
               // Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();
                break;
            case nav_logout:
               // Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();
               // Paper.book().destroy();
               // Intent intent = new Intent(HomeActivity.this, MainActivity.class);
              //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
              //  startActivity(intent);
              //  finish();
                break;
            default:
                break;
        /*if (id == R.id.nav_cart){

        }else if(id == R.id.nav_orders){

        }else if(id == R.id.nav_categories){

        }else if(id == R.id.nav_settings){

        }else if(id == R.id.nav_logout){
            Toast.makeText(this, "login out", Toast.LENGTH_SHORT).show();
                Paper.book().destroy();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();*/
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
