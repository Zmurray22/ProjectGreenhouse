package com.example.projectgreenhouse.inv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgreenhouse.R;
import com.example.projectgreenhouse.db.PlantItem;

import java.util.LinkedList;
import java.util.List;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.InventoryViewHolder>{
    //private final LinkedList<String> mPlantList;
    private final LayoutInflater mInflater;
    private List<PlantItem> mPlantList;

     PlantListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
     }

    @Override
    public PlantListAdapter.InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.plant_item_layout, parent, false);
        return new InventoryViewHolder(mItemView, this);
    }

     //Connect data to the view holder
    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int position) {
        if(mPlantList != null){
            PlantItem current = mPlantList.get(position);
            holder.nickNameTextView.setText(current.getPlant());
        } else{
            //If still loading
            holder.nickNameTextView.setText("No Items");
        }
    }

    void setItems(List<PlantItem> items){
        mPlantList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mPlantList != null)
            return mPlantList.size();
        else return 0;
    }

    class InventoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nickNameTextView;
        private TextView speciesNameTextView;
        private ImageView profileImageView;
        private ImageView freezeImageView;
        private ImageView heartImageView;
        private ImageView stormImageView;
        private ImageView toxicImageView;
        private ImageView waterImageView;
        final PlantListAdapter mAdapter;

        //Constructor
        private InventoryViewHolder(View itemView, PlantListAdapter adapter){
            super(itemView);
            nickNameTextView = itemView.findViewById(R.id.nickname);
            /*speciesNameTextView = itemView.findViewById(R.id.speciesName);
            profileImageView = itemView.findViewById(R.id.profileImage);
            freezeImageView = itemView.findViewById(R.id.freeze_icon);
            heartImageView = itemView.findViewById(R.id.heart_icon);
            stormImageView = itemView.findViewById(R.id.storm_icon);
            toxicImageView = itemView.findViewById(R.id.toxic_icon2);
            waterImageView = itemView.findViewById(R.id.water_icon);*/

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Get position of the item clicked
            int mPosition = getLayoutPosition();
            //Use above to access teh affected item in mPlantList
            PlantItem element = mPlantList.get(mPosition);
            //Change the plant in the mPlantList

            //Notify adapter of change to update the RecyclerView
            mAdapter.notifyDataSetChanged();
        }
    }
}
