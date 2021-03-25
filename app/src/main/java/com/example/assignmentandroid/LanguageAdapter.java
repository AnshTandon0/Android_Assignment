package com.example.assignmentandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private List<Language> languages;
    private LayoutInflater layoutInflater;

    public LanguageAdapter (List<Language> languages , Context context)
    {
        this.languages = languages;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.language_view,parent,false);
        return new LanguageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {

        holder.iso639_1.setText("Iso639_1  -  " + languages.get(position).getIso639_1());
        holder.iso639_2.setText("Iso639_2  -  " + languages.get(position).getIso639_2());
        holder.name.setText("Name  -  " + languages.get(position).getName());
        holder.nativeName.setText("Native Name  -  " + languages.get(position).getNativeName());

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder
    {
        TextView iso639_1 , iso639_2 , name , nativeName;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            iso639_1 = itemView.findViewById(R.id.iso639_1);
            iso639_2 = itemView.findViewById(R.id.iso639_2);
            name = itemView.findViewById(R.id.name);
            nativeName = itemView.findViewById(R.id.nativeName);
        }
    }
}
