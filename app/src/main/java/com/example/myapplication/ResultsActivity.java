package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.Discogs;
import com.example.myapplication.memoryManager.FavouriteMusicEntity;
import com.example.myapplication.memoryManager.FavouriteMusicViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ResultsActivity extends AppCompatActivity implements Observer {

    public static final String TAG = "SearchActivity";
    MyAdapterRecycledView myAdapterRecycledView;
    FavouriteMusicViewModel favouriteMusicViewModel;
    List<DiscogsViewModel> requestList = new ArrayList<DiscogsViewModel>();
    private LinearLayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String artist = intent.getStringExtra(SearchActivity.ARTIST_TO_SEARCH);
        String title = intent.getStringExtra(SearchActivity.TITLE_TO_SEARCH);

        favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);

        controller.startRetrofitService();

        DiscogsSearchParameter searchRequest = new DiscogsSearchParameter(artist,title);
        controller.requestDiscogsSearch(searchRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        RetrofitObservable.getInstance().addObserver(this);
    }

    @Override
    public void onPause() {
        super.onResume();
        RetrofitObservable.getInstance().deleteObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        List<Discogs.Result> response = (List<Discogs.Result>) arg;
        Log.d(TAG, "Observable update");
        Log.d(TAG,response.get(0).getTitle());

        for (Discogs.Result element : response) {
            DiscogsViewModel newItem = new DiscogsViewModel(element.getTitle(),element.getCountry(), element.getYear(),element.getCoverImage());
            requestList.add(newItem);
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        myAdapterRecycledView = new MyAdapterRecycledView(this, requestList);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(mDividerItemDecoration);
        
        recyclerView.setLayoutManager(layoutManager);

        myAdapterRecycledView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = recyclerView.getChildLayoutPosition(view);
                String title = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvTitle)).getText().toString();
                String year = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvYear)).getText().toString();
                String cover = requestList.get(position).getCover();
                String country = requestList.get(position).getCountry();
                Log.d(TAG, "onClick --> Save release to collection: " + position);

                FavouriteMusicEntity music = new FavouriteMusicEntity(title,country,year,cover);
                favouriteMusicViewModel.insert(music);
            }
        });
        recyclerView.setAdapter(myAdapterRecycledView);


    }

}