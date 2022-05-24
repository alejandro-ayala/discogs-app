package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterRecycledView extends RecyclerView.Adapter <MyAdapterRecycledView.ViewHolder> {
    private static final String TAG = "MyAdapterRecycledView";
    private LayoutInflater mInflater;
    private List<DiscogsViewModel> mRequestList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView artistName;

        public ViewHolder (View view) {
            super(view);

            artistName = (TextView) view.findViewById((R.id.tvArtistName));
        }

        public TextView getTextView() {
            return artistName;
        }
    }
    public MyAdapterRecycledView(Context context, List<DiscogsViewModel> requestList) {
        this.mInflater = LayoutInflater.from(context);
        this.mRequestList = requestList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_row, viewGroup , false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + "set.");
        String artist = mRequestList.get(position).getArtistName();

        TextView artistNameTextView = viewHolder.artistName;
        artistNameTextView.setText(artist);
    }

    @Override
    public int getItemCount() {
        return mRequestList.size();
    }

    void setDiscogsData(List<DiscogsViewModel> discogsViewModel){
        this.mRequestList = discogsViewModel;
        notifyDataSetChanged();
    }
}
