package com.residencias.ficosec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.residencias.ficosec.Model.Users;

public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;

    private String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

          if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"porfavor rellene todos los campos", Toast.LENGTH_LONG);
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"porfavor rellene todos los campos", Toast.LENGTH_LONG);
        }
        else{
              loadingBar.setTitle("Iniciando sesión");
              loadingBar.setMessage("Porfavor espere");
              loadingBar.setCanceledOnTouchOutside(false);
              loadingBar.show();

              AllowAccessToAccount(phone,password);
          }
    }

    private void AllowAccessToAccount(final String phone, final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists()){
                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone)){
                        if (usersData.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Contraseña incorrecta ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "La cuenta con el numero "+ phone + " no existe", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Necesitas crear una cuenta nueva", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}