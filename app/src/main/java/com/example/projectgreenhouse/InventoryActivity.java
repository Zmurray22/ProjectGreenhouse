package com.example.projectgreenhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.Objects;

public class InventoryActivity extends AppCompatActivity {

    //Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    //Arraylist of list items
    private final LinkedList<String> mPlantList = new LinkedList<>();
    //Variables for RecyclerView
    private RecyclerView mRecyclerView;
    private PlantListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Drawer navigation
        drawerLayout = findViewById(R.id.drawer_layout_inventory);
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
                Intent intent = new Intent(InventoryActivity.this, MainActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.home));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_inventory){
                Log.i(getString(R.string.nav_log), getString(R.string.inventory));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_map){
                Intent intent = new Intent(InventoryActivity.this, MapsActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.maps));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_id){
                Intent intent = new Intent(InventoryActivity.this, IdActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.id));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_social){
                Intent intent = new Intent(InventoryActivity.this, SocialActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.social));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_share){
                //TODO: Functionality
                Log.i(getString(R.string.nav_log), getString(R.string.share));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_settings){
                Intent intent = new Intent(InventoryActivity.this, SettingsActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.settings));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_about){
                Intent intent = new Intent(InventoryActivity.this, AboutActivity.class);
                startActivity(intent);
                Log.i(getString(R.string.nav_log), getString(R.string.about));
                drawerLayout.closeDrawer(GravityCompat.START);

            }else if(item.getItemId() == R.id.nav_exit){
                Log.i(getString(R.string.nav_log), getString(R.string.exit));
                drawerLayout.closeDrawer(GravityCompat.START);
                finishAffinity();
            }
            return true;
        });

        //Top nav buttons
        //Add a new item button
        ImageButton addItem = findViewById(R.id.addButton);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int listSize = mPlantList.size();
                //add new item
                mPlantList.addLast(" + Plant" + listSize);
                //notify adapter of change
                mRecyclerView.getAdapter().notifyItemInserted(listSize);
                //scroll to bottom
                mRecyclerView.smoothScrollToPosition(listSize);
            }
        });

        //Delete an item
        ImageButton deleteItem = findViewById(R.id.deleteButton);
        deleteItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int listSize = mPlantList.size();
                //delete item
                if(listSize != 0)
                    mPlantList.removeLast();
                //notify adapter of change
                mRecyclerView.getAdapter().notifyItemRemoved(listSize);
                //scroll to bottom
                mRecyclerView.smoothScrollToPosition(listSize);
            }
        });

        //Populate item arraylist
        for(int i = 0; i < 5; i++){
            mPlantList.add("Plant " + i);
        }

        //Get a handle to RecyclerView
        mRecyclerView = findViewById(R.id.inventory_recyclerView);
        //create adapter and supply the data to display
        mAdapter = new PlantListAdapter(this, mPlantList);
        //connect adapter with RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        //give RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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