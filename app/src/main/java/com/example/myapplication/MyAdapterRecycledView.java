package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterRecycledView extends RecyclerView.Adapter <MyAdapterRecycledView.ViewHolder>
         {
    private static final String TAG = "MyAdapterRecycledView";
    private LayoutInflater mInflater;
    private List<DiscogsViewModel> mRequestList;
    private static Context mContext;
    //private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private final TextView title;
        private final TextView year;
        private final TextView label;
        private final TextView format;
        private final TextView country;
        private ImageView cover;


        public ViewHolder (View view) {
            super(view);
            Context context = view.getContext();
            title = (TextView) view.findViewById((R.id.tvTitle));
            year = (TextView) view.findViewById((R.id.tvYear));
            label = (TextView) view.findViewById((R.id.tvLabel));
            format = (TextView) view.findViewById((R.id.tvFormat));
            country = (TextView) view.findViewById((R.id.tvCountry));
            cover = view.findViewById(R.id.ivCover);

        }

    }
    public MyAdapterRecycledView(Context context, List<DiscogsViewModel> requestList) {
        this.mInflater = LayoutInflater.from(context);
        this.mRequestList = requestList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_row, viewGroup , false);

        //view.setOnClickListener(this);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        String title = mRequestList.get(position).getTitle();
        String year = mRequestList.get(position).getYearRelease();
        String cover = mRequestList.get(position).getCover();
        String label = mRequestList.get(position).getLabel();
        String format = mRequestList.get(position).getFormat();
        String country = mRequestList.get(position).getCountry();

        TextView titleNameTextView = viewHolder.title;
        titleNameTextView.setText(title);
        TextView yearRelease = viewHolder.year;
        yearRelease.setText(year);
        TextView labelTextView = viewHolder.label;
        labelTextView.setText(label);
        TextView formatTextView = viewHolder.format;
        formatTextView.setText(format);
        TextView countryTextView = viewHolder.country;
        countryTextView.setText(country);
        Picasso.get()
                .load(cover).resize(700, 700).centerCrop()
                .into(viewHolder.cover);
    }

    @Override
    public int getItemCount() {
        return mRequestList.size();
    }

    void setDiscogsData(List<DiscogsViewModel> discogsViewModel){
        this.mRequestList = discogsViewModel;
        notifyDataSetChanged();
    }

/*
    @Override
    public void addToCollection(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
*/

}
