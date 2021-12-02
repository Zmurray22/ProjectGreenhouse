package com.example.projectgreenhouse.inv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.projectgreenhouse.R;
import com.example.projectgreenhouse.SettingsActivity;
import com.example.projectgreenhouse.SocialActivity;
import com.example.projectgreenhouse.db.PlantItem;
import com.example.projectgreenhouse.viewmodel.GreenhouseViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class InventoryActivity extends AppCompatActivity {

    //Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    //DB
    private GreenhouseViewModel mGreenhouseViewModel;
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

        //RecyclerView Block----------------------
        RecyclerView recyclerView = findViewById(R.id.inventory_recyclerView);
        final PlantListAdapter adapter = new PlantListAdapter(this);
        //connect adapter with RecyclerView
        recyclerView.setAdapter(adapter);
        //give RecyclerView a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ViewModel-------------------------
        mGreenhouseViewModel = new ViewModelProvider(this).get(GreenhouseViewModel.class);
        GreenhouseViewModel.getAllPlants().observe(this, new Observer<List<PlantItem>>() {
            @Override
            public void onChanged(@Nullable final List<PlantItem> plantItems) {
                //update cache
                adapter.setItems(plantItems);
            }
        });

        //Top nav buttons-------------------------
        //Add a new item button
        ImageButton addItem = findViewById(R.id.addButton);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, AddPlantActivity.class);
                //TODO: Refactor to androidx activity result implementation
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        /*//Delete an item
        ImageButton deleteItem = findViewById(R.id.deleteButton);
        deleteItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int listSize = mPlantList.size();
                //delete item
                if(listSize != 0)
                    mPlantList.removeLast();
                //notify adapter of change
                try{
                    Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRemoved(listSize);
                }catch(NullPointerException e){
                Toast.makeText(getApplicationContext(), "Removal error", Toast.LENGTH_SHORT).show();
            }
                //scroll to bottom
                recyclerView.smoothScrollToPosition(listSize);
            }
        });*/

    }

    //Drawer Menu-----------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ------------------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            PlantItem item = new PlantItem(data.getStringExtra(AddPlantActivity.EXTRA_REPLY));
            mGreenhouseViewModel.insert(item);
        } else{
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}