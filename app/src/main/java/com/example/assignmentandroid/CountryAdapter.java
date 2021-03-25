package com.example.assignmentandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.internal.Util;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countries ;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;

    public CountryAdapter(Context context , List<Country> countries , Activity activity)
    {
        layoutInflater = LayoutInflater.from(context);
        this.countries = countries;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.adapter_view,parent,false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {


        SvgLoader.pluck()
                .with(activity)
                .load(countries.get(position).getFlag(),holder.imageView)
                .setPlaceHolder(R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        holder.name.setText("Country  - " + countries.get(position).getName());
        holder.capital.setText("Capital  - " + countries.get(position).getCapital());
        holder.region.setText("Region  -  " + countries.get(position).getRegion());
        holder.subRegion.setText("Sub Region  -  " + countries.get(position).getSubRegion());
        holder.population.setText("Population  -  " + String.format("%d",countries.get(position).getPopulation()));
        holder.borders.setText("Borders  -  " + DataConverter.writingStringFromList(countries.get(position).getBorders()));

        LanguageAdapter languageAdapter = new LanguageAdapter(countries.get(position).getLanguages(),context);
        holder.recyclerView.setAdapter(languageAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, capital, region, subRegion, population, borders;
        ImageView imageView;
        RecyclerView recyclerView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subRegion = itemView.findViewById(R.id.subRegion);
            population = itemView.findViewById(R.id.population);
            borders = itemView.findViewById(R.id.borders);
            recyclerView = itemView.findViewById(R.id.recyclerView2);

        }
    }
}
