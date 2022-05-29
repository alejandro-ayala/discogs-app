package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.Discogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SearchActivity extends AppCompatActivity implements Observer {

    public static final String TAG = "SearchActivity";
    MyAdapterRecycledView myAdapterRecycledView;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String artist = intent.getStringExtra(MainActivity.ARTIST_TO_SEARCH);
        String title = intent.getStringExtra(MainActivity.TITLE_TO_SEARCH);

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

        List<DiscogsViewModel> requestList = new ArrayList<DiscogsViewModel>();

        for (Discogs.Result element : response) {
            DiscogsViewModel newItem = new DiscogsViewModel(element.getTitle(),element.getCountry(), element.getYear(),element.getCoverImage());
            requestList.add(newItem);
        }


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        myAdapterRecycledView = new MyAdapterRecycledView(this, requestList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapterRecycledView);

        //myAdapterRecycledView.setDiscogsData(requestList);
        // make your changes here
    }    
}