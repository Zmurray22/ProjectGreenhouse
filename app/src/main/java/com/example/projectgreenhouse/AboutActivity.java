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

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {
    //Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Drawer navigation
        drawerLayout = findViewById(R.id.drawer_layout_about);
        navigationView = findViewById(R.id.drawer_navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        //adds toggle bar to the drawer layout and sync the state of the action
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //back arrow
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //Nav drawer intents
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_home){
                    Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Home");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_inventory){
                    Intent intent = new Intent(AboutActivity.this, InventoryActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Inventory");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_map){
                    Intent intent = new Intent(AboutActivity.this, MapsActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Maps");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_id){
                    Intent intent = new Intent(AboutActivity.this, IdActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Identify");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_social){
                    Intent intent = new Intent(AboutActivity.this, SocialActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Social");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_share){
                    //TODO: Functionality
                    Log.i("DRAWER_NAV_TAG", "Share");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_settings){
                    Intent intent = new Intent(AboutActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    Log.i("DRAWER_NAV_TAG", "Settings");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_about){
                    Log.i("DRAWER_NAV_TAG", "About");
                    drawerLayout.closeDrawer(GravityCompat.START);

                }else if(item.getItemId() == R.id.nav_exit){
                    Log.i("DRAWER_NAV_TAG", "Exit");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    finish();
                }

                return true;
            }
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