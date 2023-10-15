package com.example.interventions;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Adaptateur_tache extends ArrayAdapter<tache> {

    CheckBox __chb_test;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View tacheview = convertView;

        //Si la liste des taches est vide on insert les taches
        if (tacheview == null) {
            tacheview = LayoutInflater.from(getContext()).inflate(R.layout.taches_vue, parent, false);
            tache tacheCourante = getItem(position);

            TextView __txt_nom_intervention, __txt_nom_societe, __txt_adresse_client, __txt_heure_intervention;
            CheckBox __tache_valider;

            __txt_nom_intervention = (TextView) tacheview.findViewById(R.id.txt_nom_intervention);
            __txt_nom_intervention.setText(tacheCourante.getNom_intervention());

            __txt_nom_societe = (TextView) tacheview.findViewById(R.id.txt_nom_societe);
            __txt_nom_societe.setText(tacheCourante.getNom_client());

            __txt_adresse_client = (TextView) tacheview.findViewById(R.id.txt_rue);
            __txt_adresse_client.setText(tacheCourante.getAdresse_client());

            __txt_heure_intervention = (TextView) tacheview.findViewById(R.id.txt_heure);
            __txt_heure_intervention.setText(tacheCourante.getHeure_interventio());

            __tache_valider = (CheckBox) tacheview.findViewById(R.id.chkbox_valider);
            if (tacheCourante.getTache_terminer() == false) {
                __tache_valider.setChecked(false);
            } else {
                __tache_valider.setChecked(true);
            }

            // les évènements checkbox
            __tache_valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!__tache_valider.isChecked()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Etes-vous sur de vouloir décocher cette intervention ?");
                        builder.setCancelable(false);
                        builder.setTitle("Décocher ?");

                        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                __tache_valider.setChecked(false);
                            }
                        });

                        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                __tache_valider.setChecked(true);

                                dialog.cancel();
                            }
                        });

                        AlertDialog mDialog = builder.create();
                        mDialog.show();

                    }
                }

            });


        }



        return tacheview;
    }



    public Adaptateur_tache(@NonNull Context context, ArrayList<tache> taches) {
        super(context, 0,taches);


    }
}
