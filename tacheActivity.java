package com.example.interventions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;

public class tacheActivity extends AppCompatActivity {

    Switch __switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        __switch1 = (Switch) findViewById(R.id.switch1);

        __switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!__switch1.isChecked()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(tacheActivity.this);

                    builder.setMessage("Etes-vous sur de vouloir décocher cette intervention ?");
                    builder.setCancelable(false);
                    builder.setTitle("Décocher ?");

                    builder.setPositiveButton("OUI",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    ((Switch) findViewById(R.id.switch1)).setChecked(false);
                                }
                            });

                    builder.setNegativeButton("Annuler",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ((Switch) findViewById(R.id.switch1)).setChecked(true);

                                    dialog.cancel();
                                }
                            });

                    AlertDialog mDialog = builder.create();
                    mDialog.show();

                }

            }});
    }
}