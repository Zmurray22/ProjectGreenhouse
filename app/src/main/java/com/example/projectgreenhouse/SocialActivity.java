package com.example.projectgreenhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projectgreenhouse.inv.InventoryActivity;
import com.example.projectgreenhouse.settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class SocialActivity extends AppCompatActivity {
    //Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        //Drawer navigation
        drawerLayout = findViewById(R.id.drawer_layout_social);
        navigationView = findViewById(R.id.drawer_navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        //adds toggle bar to the drawer layout and sync the state of the action
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //back arrow
        //Nav drawer intents
        navigationView.setNavigationItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){
                Intent intent = new Intent(SocialActivity.this, MainActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.home));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_inventory){
                Intent intent = new Intent(SocialActivity.this, InventoryActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.inventory));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_map){
                Intent intent = new Intent(SocialActivity.this, MapsActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.maps));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_id){
                Intent intent = new Intent(SocialActivity.this, IdActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.id));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_social){
                Log.i(getString(R.string.nav_log), getString(R.string.social));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_share){
                //TODO: Functionality
                Toast.makeText(SocialActivity.this, "Share feature under construction", Toast.LENGTH_SHORT).show();
                Log.i(getString(R.string.nav_log), getString(R.string.share));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_settings){
                Intent intent = new Intent(SocialActivity.this, SettingsActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.settings));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_about){
                Intent intent = new Intent(SocialActivity.this, AboutActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.about));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_exit){
                Log.i(getString(R.string.nav_log), "Exit");
                drawerLayout.closeDrawer(GravityCompat.START);
                finishAffinity();
            }

            return true;
        });
    }
    //Drawer Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}