package com.example.flexiload_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        //fragment
        firebaseAuth= FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        CardView card_view8=findViewById(R.id.card_view8);
        card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Sim_CardList.class));

            }
        });
    }
    @Override
    public void onBackPressed()   {
        AlertDialog.Builder warning = new AlertDialog.Builder(this)
                .setTitle("Are you Exit ?")
                .setMessage("You are logged in with Temporary Account. Exiting  will Delete All the data.")
                .setPositiveButton("Sync Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Data Sync Successfully", Toast.LENGTH_SHORT).show();

                            }
                        },2000);


                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ToDO: delete all the notes created by the Anon user

                        // TODO: delete the anon user
                        dialog.dismiss();
                        finishAffinity();

                    }
                });

        warning.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addNote:
                Toast.makeText(this, "You are now home now.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notes1:
                startActivity(new Intent(getApplicationContext(),Buy_Coin.class));
                Toast.makeText(this, "Buy Coin", Toast.LENGTH_SHORT).show();
                break;

            case R.id.notes:
                startActivity(new Intent(getApplicationContext(),Profile.class));
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shareapp1:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                AlertDialog.Builder warning = new AlertDialog.Builder(this)
                        .setTitle("Are you Logout ?")
                        .setMessage("You are logged in with Temporary Account. Exiting  will Delete All the data.")
                        .setPositiveButton("Stay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Data Sync Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                },2000);


                            }
                        }).setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user

                                // TODO: delete the anon user
                                firebaseAuth.signOut();
                                dialog.dismiss();
                                finishAffinity();

                            }
                        });

                warning.show();
                break;



        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}