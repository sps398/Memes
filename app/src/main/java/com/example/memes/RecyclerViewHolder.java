package com.example.memes;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public void bind(String url) {
        Glide.with(itemView).load(url).placeholder(R.drawable.ic_arrow_downward_black_10dp).error(R.drawable.ic_error_black_10dp).into(imageView);
    }
}
