package com.example.memes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context context;
    JSONArray array = null;
    RecyclerViewAdapter(Context context,JSONArray array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if(position == array.length()-1) {
            Toast.makeText(context,"50 memes loaded",Toast.LENGTH_SHORT).show();
            new MemeLoader(context).execute("https://meme-api.herokuapp.com/gimme/50");
        }

        try {
            JSONObject object = array.getJSONObject(position);
            String url = object.getString("url");
            holder.bind(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return array.length();
    }
}
