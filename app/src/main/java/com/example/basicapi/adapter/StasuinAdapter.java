package com.example.basicapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapi.R;
import com.example.basicapi.data.StasiunData;

import java.util.List;

public class StasuinAdapter extends RecyclerView.Adapter<StasuinAdapter.StasiunViewHolder>{
    private List<StasiunData> stasiunData;
    public StasuinAdapter(List<StasiunData> stasiunData) {
        this.stasiunData = stasiunData;
    }

    @NonNull
    @Override
    public StasuinAdapter.StasiunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stasiun, parent, false);
        return new StasiunViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StasuinAdapter.StasiunViewHolder holder, int position) {
        StasiunData stasiunDta = stasiunData.get(position);

        // Set data ke tampilan holder
//        holder.textViewFoto.setText(place.foto);
        holder.code.setText("Code Stasiun : " + stasiunDta.code);
        holder.name.setText("Nama Stasiun : " + stasiunDta.name);
        holder.city.setText("Kota Stasiun : " + stasiunDta.city);
        holder.cityname.setText("Nama Kota Stasiun : " + stasiunDta.cityName);
    }

    @Override
    public int getItemCount() {
        return stasiunData.size();
    }
    public static class StasiunViewHolder extends RecyclerView.ViewHolder {
        TextView code,name,city,cityname;

        public StasiunViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            city = itemView.findViewById(R.id.city);
//            textViewFoto = itemView.findViewById(R.id.textViewFoto);
            name = itemView.findViewById(R.id.name);
            cityname = itemView.findViewById(R.id.cityname);
        }
    }
}
