package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.memoryManager.FavouriteMusicEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterRecyclerViewFavourite extends RecyclerView.Adapter <adapterRecyclerViewFavourite.ViewHolder>
        implements View.OnClickListener {
    private static final String TAG = "adapterRecyclerViewFavourite";
    private LayoutInflater mInflater;
    private List<FavouriteMusicEntity> mFavouriteMusicCollection;
    private static Context mContext;
    private static View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView cover;


        public ViewHolder (View view) {
            super(view);
            Context context = view.getContext();
            cover = (ImageView) view.findViewById((R.id.ivFavouriteRelease));
        }

    }
    public adapterRecyclerViewFavourite(Context context, List<FavouriteMusicEntity> musicCollection) {
        this.mInflater = LayoutInflater.from(context);
        this.mFavouriteMusicCollection = musicCollection;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.favourite_layout, viewGroup , false);

        view.setOnClickListener(this);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        String cover = mFavouriteMusicCollection.get(position).getCover();

        Picasso.get()
                .load(cover).resize(700, 700).centerCrop()
                .into(viewHolder.cover);
    }

    @Override
    public int getItemCount() {
        return mFavouriteMusicCollection.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public FavouriteMusicEntity getItemByPosition(int position){
        return mFavouriteMusicCollection.get(position);
    }
    public static void setOnClickListener(View.OnClickListener onClickListener) {
        listener = onClickListener;
    }
}
