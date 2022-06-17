package com.example.discogsMusicCollection.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discogsMusicCollection.discogsManager.retrofit.ControllerDiscogsAPI;
import com.example.discogsMusicCollection.discogsManager.retrofit.DiscogsSearchParameter;
import com.example.discogsMusicCollection.discogsManager.DiscogsViewModel;
import com.example.discogsMusicCollection.userInterface.adapterRecycledViewSearch;
import com.example.discogsMusicCollection.R;
import com.example.discogsMusicCollection.discogsManager.retrofit.RetrofitObservable;
import com.example.discogsMusicCollection.discogsManager.model.Discogs;
import com.example.discogsMusicCollection.memoryManager.FavouriteMusicEntity;
import com.example.discogsMusicCollection.memoryManager.FavouriteMusicViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ResultsActivity extends AppCompatActivity implements Observer {

    public static final String TAG = "SearchActivity";
    adapterRecycledViewSearch adapterRecycledViewSearch;
    FavouriteMusicViewModel favouriteMusicViewModel;
    List<DiscogsViewModel> requestList = new ArrayList<DiscogsViewModel>();
    private LinearLayoutManager layoutManager;

    RecyclerView recyclerView;
    ControllerDiscogsAPI controllerDiscogsAPI = new ControllerDiscogsAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String artist = intent.getStringExtra(SearchActivity.ARTIST_TO_SEARCH);
        String title = intent.getStringExtra(SearchActivity.TITLE_TO_SEARCH);
        String format = intent.getStringExtra(SearchActivity.FORMAT_TO_SEARCH);
        String year = intent.getStringExtra(SearchActivity.YEAR_TO_SEARCH);
        String genre = intent.getStringExtra(SearchActivity.GENRE_TO_SEARCH);


        favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);

        controllerDiscogsAPI.startRetrofitService();

        DiscogsSearchParameter searchRequest = new DiscogsSearchParameter(artist,title,format,year,genre);
        controllerDiscogsAPI.requestDiscogsSearch(searchRequest);


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

        try {
                List<Discogs.Result> response = (List<Discogs.Result>) arg;
                Log.d(TAG, "Observable update");
                Log.d(TAG, response.get(0).getTitle());

                for (Discogs.Result element : response) {
                    String country = "Country: " + element.getCountry();
                    String year = "Year: " + element.getYear();
                    String label = "Label: " + element.getLabel().get(0);
                    String format = "Format: " + element.getFormat().get(0);
                    DiscogsViewModel newItem = new DiscogsViewModel(element.getTitle(), country, year, label, element.getCoverImage(), format);
                    requestList.add(newItem);
                }

                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(this);
                adapterRecycledViewSearch = new adapterRecycledViewSearch(this, requestList);

                DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        layoutManager.getOrientation());

                recyclerView.addItemDecoration(mDividerItemDecoration);

                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(adapterRecycledViewSearch);
        }
        catch (Exception exception) {
            popupErrorMessage();
        }

    }

     public void addToCollection(View view) {
         Log.d(TAG, "onClick --> addToCollection: " );

         View parentRow = (View) view.getParent();
         View parent2Row = (View) parentRow.getParent();
         int position = layoutManager.getPosition(parent2Row);

         String title = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvTitle)).getText().toString();
         String year = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvYear)).getText().toString();
         String cover = requestList.get(position).getCover();
         String country = requestList.get(position).getCountry();
         String label = requestList.get(position).getLabel();
         String format = requestList.get(position).getFormat();
         Log.d(TAG, "onClick --> Save release to collection: " + position);

         FavouriteMusicEntity music = new FavouriteMusicEntity(title,country,year,cover,label,format);
         favouriteMusicViewModel.insert(music);

     }

    public void popupErrorMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Search music release can not be found. Please review the request or add more information\"");
        alertDialogBuilder.setTitle("Search Failed");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // add these two lines, if you wish to close the app:
                Intent intent = new Intent(ResultsActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}