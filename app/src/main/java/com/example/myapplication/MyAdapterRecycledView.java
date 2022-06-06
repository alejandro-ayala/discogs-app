package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.memoryManager.MemoryManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterRecycledView extends RecyclerView.Adapter <MyAdapterRecycledView.ViewHolder> {
    private static final String TAG = "MyAdapterRecycledView";
    private LayoutInflater mInflater;
    private List<DiscogsViewModel> mRequestList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView year;
        private ImageView cover;

        public ViewHolder (View view) {
            super(view);
            Context context = view.getContext();
            title = (TextView) view.findViewById((R.id.tvTitle));
            year = (TextView) view.findViewById((R.id.tvYear));
            cover = view.findViewById(R.id.ivCover);
            // Attach a click listener to the entire row view
            view.setOnClickListener(this);
        }
        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition(); // gets item position
            Log.d(TAG, "Onclick " + position );
            MemoryManager memoryManager = new MemoryManager();
            String title = String.valueOf(this.title.getText());
            String url = "www.com";

           memoryManager.saveFavouriteRelease(title,url);

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

        String title = mRequestList.get(position).getTitle();
        String year = mRequestList.get(position).getYearRelease();
        String cover = mRequestList.get(position).getCover();

        TextView titleNameTextView = viewHolder.title;
        titleNameTextView.setText(title);

        TextView yearRelease = viewHolder.year;
        yearRelease.setText(year);

        Picasso.get().load(cover).into(viewHolder.cover);
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
