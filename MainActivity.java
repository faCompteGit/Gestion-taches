package com.example.interventions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    EditText __edt_username, __edt_password;
    Button __btn_connexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.header_blue)));
        //Connexion
        __edt_username = (EditText) findViewById(R.id.edt_username);
        __edt_password = (EditText) findViewById(R.id.edt_password);
        __btn_connexion = (Button) findViewById(R.id.btn_connexion);



        __btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (__edt_username.getText().toString().equals("mrfa") &&  __edt_password.getText().toString().equals("123")){
                    Intent i =new Intent(getApplicationContext(),InterventionsActivity2.class);
                    startActivity(i);

                }
            }
        });


    }
}