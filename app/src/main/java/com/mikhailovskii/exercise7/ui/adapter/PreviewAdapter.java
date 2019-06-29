package com.mikhailovskii.exercise7.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.data.entities.Photo;

import java.util.ArrayList;
import java.util.List;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {

    private List<Photo> photos;

    public PreviewAdapter() {
        photos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cell, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Photo photo = photos.get(i);
        Glide.with(viewHolder.mImageView.getContext())
                .load(photo.urls.getRegular())
                .into(viewHolder.mImageView);
    }


    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<Photo> photos){
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_cell);
        }

    }

}
