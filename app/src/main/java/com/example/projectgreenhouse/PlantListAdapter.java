package com.example.projectgreenhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.InventoryViewHolder>{
    private final LinkedList<String> mPlantList;
    private LayoutInflater mInflator;
    //Constructor
    public PlantListAdapter(Context context, LinkedList<String> itemList){
        mInflator = LayoutInflater.from(context);
        this.mPlantList = itemList;
    }

    //inflates the item layout and returns ViewHolder
    @NonNull
    @Override
    public PlantListAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.plant_item_layout, parent, false);
        return new InventoryViewHolder(mItemView);
    }
    //Connect data to the view holder
    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        String mCurrent = mPlantList.get(position);
    }

    @Override
    public int getItemCount() {
        return mPlantList.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder{

        private TextView nickNameTextView;
        private TextView speciesNameTextView;
        private ImageView profileImageView;
        private ImageView freezeImageView;
        private ImageView heartImageView;
        private ImageView stormImageView;
        private ImageView toxicImageView;
        private ImageView waterImageView;

        //Constructor
        public InventoryViewHolder(View itemView){
            super(itemView);
            nickNameTextView = itemView.findViewById(R.id.nickname);
            speciesNameTextView = itemView.findViewById(R.id.speciesName);
            profileImageView = itemView.findViewById(R.id.profileImage);
            freezeImageView = itemView.findViewById(R.id.freeze_icon);
            heartImageView = itemView.findViewById(R.id.heart_icon);
            stormImageView = itemView.findViewById(R.id.storm_icon);
            toxicImageView = itemView.findViewById(R.id.toxic_icon2);
            waterImageView = itemView.findViewById(R.id.water_icon);
        }
    }
}
