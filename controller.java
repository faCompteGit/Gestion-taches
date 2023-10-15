package com.example.interventions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.client;

public class controller extends SQLiteOpenHelper {


    public controller(@Nullable Context context) {
        super(context, "intervention", null, 1);
    }

    //hash du mot de passe
    @NonNull
    public static String sha256(String s) {
        try {
            //création du hash 256
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA256");
            digest.update(s.getBytes(StandardCharsets.UTF_8));
            byte tabDigest[] = digest.digest();
            StringBuffer hexadeString = new StringBuffer();
            for (int i = 0; i < tabDigest.length; i++) {
                hexadeString.append(Integer.toHexString(tabDigest[i] & 0xFF));
            }
            return hexadeString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS clients(" +
                "client_id integer(11) primary key not null," +
                "nom varchar(255) not null," +
                "adresse_client text not null," +
                "tel varchar(20) not null," +
                "fax varchar(20) not null," +
                "email varchar(255) not null," +
                "contact varchar(20) not null," +
                "telcontact varchar(20)not null," +
                "valsync integer(11) not null default 0)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS clients;");
        onCreate(db);

    }

    public void insererClient(client c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("client_id",c.getClient_id());
        cv.put("nom",c.getNom());
        cv.put("adresse_client",c.getAdresse_client());
        cv.put("tel",c.getTel());
        cv.put("fax",c.getFax());
        cv.put("email",c.getEmail());
        cv.put("contact",c.getContact());
        cv.put("telcontact",c.getTelcontact());
        cv.put("valsync",c.getValsync());

        db.insert("clients",null,cv);
        db.close();

    }



    public client afficherTache(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor c = db.rawQuery("select nom from clients wehre client_id= ?",new String[]{"1"});
        Cursor c = db.query("clients",new String[]{"client_id","nom"},"client_id=?",
                new String[]{String.valueOf(id)},null,null,null);
        c.moveToFirst();
        client cl=new client(c.getInt(0),c.getString(1),null,null,null,null,null,null,0);
        return cl;

    }





}

