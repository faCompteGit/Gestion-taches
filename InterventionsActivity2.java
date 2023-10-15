package com.example.interventions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.sql.Time;
import java.util.ArrayList;

import models.client;

public class InterventionsActivity2 extends AppCompatActivity {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    LinearLayout __linear_laout_tache, __layout_global, __layout_externe_tache, __layout_interne_taches;
    CheckBox __chkbox_tache;
    //liste des taches
    ArrayList<tache> tache =new ArrayList<tache>();
    ListView __tache_view_liste;

    SQLiteDatabase db;
    Cursor curs_client,curs_contrats;
    controller c = new controller(InterventionsActivity2.this);
    client cl;

    //VARIABLES TABLAYOUTES
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    adapteurFrgments adp_frag;
    FrameLayout frameLayout;



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interventions2);
        //************CREATION DE LA BASE DE DONNEE***********
        db=openOrCreateDatabase("intervention",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS  clients(" +
                "id integer(11) primary key not null, " +
                "nom varchar(255) not null," +
                "adresse_client text not null," +
                "tel varchar(20) not null," +
                "fax varchar(20) not null," +
                "email varchar(255) not null," +
                "contact varchar(20) not null," +
                "telcontact varchar(20)not null," +
                "valsync integer(11) not null default 0)");

        db.execSQL("CREATE TABLE IF NOT EXISTS  contrats(" +
                "id integer(11) primary key not null, " +
                "datedebut varchar(255) not null," +
                "datefin varchar(255) not null," +
                "redevence reel(10,0) not null," +
                "client_id integer(11) not null," +
                "valsync integer(11) not null default 0," +
                "CONSTRAINT fk_contrat_client FOREIGN KEY (client_id) REFERENCES clients(id) )");

        db.execSQL("CREATE TABLE IF NOT EXISTS  employes(" +
                "id integer(11) primary key not null, " +
                "login varchar(255) not null," +
                "pwd varchar(255)not null," +
                "prenom varchar(255) not null," +
                "nom varchar(255) not null," +
                "email varchar(255) not null," +
                "actif interger not null," +
                "valsync integer(11) not null default 0)");

        db.execSQL("CREATE TABLE IF NOT EXISTS  priorites(" +
                "id integer(11) primary key not null, " +
                "nom varchar(255) not null," +
                "valsync integer(11) not null default 0)");

        //PAS TESTER
        db.execSQL("CREATE TABLE IF NOT EXISTS  sites(" +
                "id integer(11) primary key not null, " +
                "longitude reel(10,0) not null," +
                "latitude reel(10,0) not null," +
                "adresse_site text not null," +
                "rue varchar(255) not null," +
                "codepostal integer(4) not null," +
                "ville varchar(255) not null," +
                "contact varchar(255) not null," +
                "telcontact varchar(255) not null," +
                "client_id not null," +
                "valsync integer(11) not null default 0," +
                "CONSTRAINT fk_site_client FOREIGN KEY (client_id) REFERENCES clients(id))");

        //PAS TESTER
        db.execSQL("CREATE TABLE IF NOT EXISTS  interventions(" +
                "id integer(11) primary key not null, " +
                "titre varchar(255) not null," +
                "datedebut varchar(255) not null," +
                "datefin varchar(255) not null," +
                "heuredebutplan varchar(255) not null," +
                "heurefinplan varchar(255) not null," +
                "commentaires text not null," +
                "dateplanification varchar(255) not null," +
                "heuredebuteffect varchar(255) not null," +
                "heurefineffect varchar(255) not null," +
                "terminee interger(1) not null," +
                "dateterminaison varchar(255) not null," +
                "validee integer(1) not null," +
                "datevalidation varchar(255) not null," +
                "priorite_id integer(11) not null," +
                "site_id integer(11) not null," +
                "valsync integer(11) not null default 0," +
                "CONSTRAINT fk_intervention_priorite FOREIGN KEY (priorite_id) REFERENCES priorites(id)," +
                "CONSTRAINT fk_intervention_site FOREIGN KEY (site_id) REFERENCES sites(id))");

        //PAS TESTER
        db.execSQL("CREATE TABLE IF NOT EXISTS  employes_interventions(" +
                "id integer(11) primary key not null, " +
                "employe_id integer(11) not null," +
                "intervention_id integer(11) not null," +
                "CONSTRAINT fk_emplo_employes_intervention FOREIGN KEY (employe_id) REFERENCES employes(id), " +
                "CONSTRAINT fk__interv_employes_intervention FOREIGN KEY (intervention_id) REFERENCES interventions(id))");

        //PAS TESTER

        db.execSQL("CREATE TABLE IF NOT EXISTS  images(" +
                "id integer(11) primary key not null, " +
                "nom text not null," +
                "img blob not null," +
                "datecapture varchar(255) not null," +
                "intervention_id interger(11) not null," +
                "valsync integer(11) not null default 0," +
                "CONSTRAINT fk_image_intervention FOREIGN KEY (intervention_id) REFERENCES interventions(id))");

        //PAS TESTER
        db.execSQL("CREATE TABLE IF NOT EXISTS  taches(" +
                "id integer(11) primary key not null, " +
                "reference varchar(255) not null," +
                "nom varchar(255) not null," +
                "duree reel(10,0) not null," +
                "prixheure reel(10,0)  not null," +
                "dateaction varchar(255)  not null," +
                "intervention_id integer(11)  not null," +
                "valsync integer(11) not null default 0," +
                "CONSTRAINT fk_tache_intervention FOREIGN KEY (intervention_id) REFERENCES interventions(id))");




        //************INSERTION DES DONNEE***********
        SQLiteStatement s= db.compileStatement("select count(*) from clients,contrats,employes,priorites,interventions;");
        long  c = s.simpleQueryForLong();
        if (c==0){
            //db.execSQL("insert into clients(id,nom,adresse_client,tel,fax,email,contact,telcontact,valsync) values(1,?,?,?,?,?,?,?,0)",new String[]{"Sorani","Rue Libreville 15505","50202456","fax","sorani@gmail.com","2584679","15487456",});
            //db.execSQL("insert into contrats(id,datedebut,datefin,redevence,client_id,valsync) values(1,?,?,10,1,0)",new  String[]{"13/04/2023","13/05/2023"});
            //db.execSQL("insert into employes(id,login,pwd,prenom,nom,email,actif,valsync) values(1,?,?,?,?,?,0,0)", new String[]{"emp1","emp1","Amour","Mabicka","Amourmabicka@gmail.com"});
            //db.execSQL("insert into priorites(id,nom,valsync) values(1,?,0)", new  String[]{"élevée"});
            db.execSQL("insert into sites(id,longitude,latitude,adresse_site,rue,codepostal,ville,contact,telcontact,client_id,valsync) values(1,11.609444,-0.803689,?,?,241,?,?,?,1,0)",new String[]{"19 street","lbv","Libreville","54582512","54582512"});
            db.execSQL("insert into interventions(id,titre,datedebut,datefin,heuredebutplan,heurefinplan,commentaires,dateplanification,heuredebuteffect,heurefineffect,terminee,dateterminaison,validee,datevalidation,priorite_id,site_id,valsync) values(1,?,?,?,?,?,?,?,?,?,1,?,1,?,1,1,0)",new String[]{"Intervention Mobile 1","14/04/2023","14/04/2023","09:00","1:00","difficultées rencontrés","13/04/2023","15:00","18:00","14/04/2023","14/04/2023"});
            db.execSQL("insert into employes_interventions(id,employe_id,intervention_id) values(1,1,1)");
            db.execSQL("insert into images(id,nom,img,datecapture,intervention_id,valsync) values(1,?,?,?,1,0)",new  String[]{"image 1","DEMANDER A MONSIEUR","14/04/2023"});
            db.execSQL("insert into taches(id,reference,nom,duree,prixheure,dateaction,intervention_id,valsync) values(1,?,?,50,10.00,?,1,0)",new String[]{"AGBD4","Changer écran","14/04/2023"});

            Toast.makeText(this, "insérer", Toast.LENGTH_SHORT).show();

        }

        //************INTERROGATION DE LA BASE***********

        curs_client = db.rawQuery("select nom from priorites where id=? ;",new String[]{"1"});
        curs_contrats = db.rawQuery("select datedebut from contrats where id=? ;",new String[]{"1"});
        try {
            curs_client.moveToFirst();
            curs_contrats.moveToFirst();

            TextView test = (TextView) findViewById(R.id.txt_test);
            TextView test1 = (TextView) findViewById(R.id.txt_test1);
            test.setText(curs_client.getString(0));
            test1.setText(curs_contrats.getString(0));
            //test.setText("normal");
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Aucun élément", Toast.LENGTH_SHORT).show();
        }











        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.header_blue)));

        //Ajout dynamique des taches
        __tache_view_liste = (ListView) findViewById(R.id.liste_view_tache);
        tache.add(new tache("Intervention Mobile 2","Maison AKOMA","Rue de Paradis 75010 Paris","15:00 - 18:00",false ));
        tache.add(new tache("Intervention Mobile 1","Sorani Production","Lambarene 75010 Schweitzer","07:00 - 10:00",true ));
        Adaptateur_tache adp_tache = new Adaptateur_tache(this,tache);
        __tache_view_liste.setAdapter(adp_tache);

        __tache_view_liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent b =new Intent(InterventionsActivity2.this,tacheActivity.class);
                b.putExtra("position",i);
                startActivity(b);
            }
        });


        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.open_menu, R.string.close_menu);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.icon_intervention:
                        Log.i("MENU_DRAWER_TAG", "icon intervention");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.icon_intervention_ass:
                        Log.i("MENU_DRAWER_TAG", "icon intervention_ass");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.icon_message:
                        Log.i("MENU_DRAWER_TAG", "icon message");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.icon_client:
                        Log.i("MENU_DRAWER_TAG", "icon client");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.icon_addresse:
                        Log.i("MENU_DRAWER_TAG", "icon adresse");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.icon_setting:
                        Log.i("MENU_DRAWER_TAG", "icon setting");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.icon_about:
                        Log.i("MENU_DRAWER_TAG", "icon about");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.icon_logout:
                        Log.i("MENU_DRAWER_TAG", "icon logout");
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


    }



}