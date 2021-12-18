package com.example.projectgreenhouse.inv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgreenhouse.R;
import com.example.projectgreenhouse.db.Plant;

import java.util.List;
import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantHolder> {
    private List<Plant> plants = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PlantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_plant, parent, false);
        return new PlantHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantHolder holder, int position) {
        Plant currentPlant = plants.get(position);
        holder.textViewNickname.setText(currentPlant.getNickname());
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
        notifyDataSetChanged();
    }

    public Plant getPlantAt(int position) {
        return plants.get(position);
    }

    class PlantHolder extends RecyclerView.ViewHolder {
        private TextView textViewNickname;

        public PlantHolder(View itemView) {
            super(itemView);
            textViewNickname = itemView.findViewById(R.id.nickname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(plants.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Plant plant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}