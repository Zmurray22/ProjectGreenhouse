package com.example.projectgreenhouse.inv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectgreenhouse.AboutActivity;
import com.example.projectgreenhouse.IdActivity;
import com.example.projectgreenhouse.MainActivity;
import com.example.projectgreenhouse.MapsActivity;
import com.example.projectgreenhouse.PlantProfileActivity;
import com.example.projectgreenhouse.R;
import com.example.projectgreenhouse.SettingsActivity;
import com.example.projectgreenhouse.SocialActivity;
import com.example.projectgreenhouse.db.Plant;
import com.example.projectgreenhouse.viewmodel.GreenhouseViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class InventoryActivity extends AppCompatActivity {

    //Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    //DB
    private GreenhouseViewModel greenhouseViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Drawer navigation----------------------------
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

        //Top nav buttons-------------------------
        //Add a new item button
        ImageButton addItem = findViewById(R.id.addButton);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this, AddPlantActivity.class);
                //TODO: Refactor to androidx activity result implementation
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        //RecyclerView Block----------------------
        //Find recyclerView in the layout
        RecyclerView recyclerView = findViewById(R.id.inventory_recyclerView);
        //give RecyclerView a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Create adapter to pass data to
        //connect adapter with RecyclerView
        final PlantAdapter adapter = new PlantAdapter();
        recyclerView.setAdapter(adapter);

        //ViewModel-------------------------
        greenhouseViewModel = new ViewModelProvider(this).get(GreenhouseViewModel.class);
        greenhouseViewModel.getAllPlants().observe(this, new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plants) {
                //update cache
                adapter.setPlants(plants);
            }
        });
        //Go to selected plant profile-------------------------
        adapter.setOnItemClickListener(new PlantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Plant plant) {
                Intent intent = new Intent(InventoryActivity.this, PlantProfileActivity.class);
                intent.putExtra("newNickname", plant.getNickname());
                startActivity(intent);
            }
        });

        //Swipe to delete----------------------------------
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                greenhouseViewModel.delete(adapter.getPlantAt(viewHolder.getAdapterPosition()));
                Toast.makeText(InventoryActivity.this, "Plant deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    //Drawer Menu-----------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Add Item ----------------------
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Plant plant = new Plant(data.getStringExtra(AddPlantActivity.EXTRA_REPLY));
            greenhouseViewModel.insert(plant);
        } else{
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}