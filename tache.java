package com.example.interventions;

import java.sql.Time;

public class tache {
    String nom_intervention;
    String nom_client;
    String adresse_client;
    String heure_interventio;
    Boolean tache_terminer;

    public tache(String nom_intervention, String nom_client, String adresse_client, String heure_interventio, Boolean tache_terminer) {
        this.nom_intervention = nom_intervention;
        this.nom_client = nom_client;
        this.adresse_client = adresse_client;
        this.heure_interventio = heure_interventio;
        this.tache_terminer = tache_terminer;
    }

    public String getNom_intervention() {
        return nom_intervention;
    }

    public void setNom_intervention(String nom_intervention) {
        this.nom_intervention = nom_intervention;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getAdresse_client() {
        return adresse_client;
    }

    public void setAdresse_client(String adresse_client) {
        this.adresse_client = adresse_client;
    }

    public String getHeure_interventio() {
        return heure_interventio;
    }

    public void setHeur_interventio(String heur_interventio) {
        this.heure_interventio = heure_interventio;
    }

    public Boolean getTache_terminer() {
        return tache_terminer;
    }

    public void setTache_terminer(Boolean tache_terminer) {
        this.tache_terminer = tache_terminer;
    }
}
