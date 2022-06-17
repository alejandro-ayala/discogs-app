package com.example.discogsMusicCollection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discogsMusicCollection.R;
import com.example.discogsMusicCollection.discogsManager.retrofit.ControllerDiscogsAPI;
import com.example.discogsMusicCollection.userInterface.adapterRecycledViewSearch;

public class SearchActivity extends AppCompatActivity {

    public static final String TAG = "SearchActivity";
    public static final String ARTIST_TO_SEARCH = "artist";
    public static final String TITLE_TO_SEARCH = "title";
    public static final String FORMAT_TO_SEARCH = "format";
    public static final String YEAR_TO_SEARCH = "year";
    public static final String GENRE_TO_SEARCH = "genre";
    adapterRecycledViewSearch adapterRecycledViewSearch;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    ControllerDiscogsAPI controllerDiscogsAPI = new ControllerDiscogsAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        Intent intent = getIntent();

    }
    public void searchDisc(View view) {
        // Do something in response to button click
        Log.d(TAG, "Search disc!!");

        Intent intent = new Intent(this,ResultsActivity.class);

        EditText etArtist = (EditText)findViewById(R.id.etArtist);
        EditText etTitle = (EditText)findViewById(R.id.etTitle);

        EditText etYear = (EditText)findViewById(R.id.etYear);


        Spinner spMusicGenre = (Spinner)findViewById(R.id.genre_spinner);
        String musicGenre = spMusicGenre.getSelectedItem().toString();
        String artist = etArtist.getText().toString();
        String title = etTitle.getText().toString();
        String format = "";

        boolean isCheckedCD = ((CheckBox) findViewById(R.id.cbFormatCD)).isChecked();
        boolean isCheckedVinyl = ((CheckBox) findViewById(R.id.cbFormatVinyl)).isChecked();

        if(isCheckedVinyl) {
            format = "vinyl";
        } else if (isCheckedCD) {
            format = "CD";
        }
        String year = etYear.getText().toString();

        intent.putExtra(ARTIST_TO_SEARCH,artist);
        intent.putExtra(TITLE_TO_SEARCH,title);
        intent.putExtra(FORMAT_TO_SEARCH,format);
        intent.putExtra(YEAR_TO_SEARCH,year);
        intent.putExtra(GENRE_TO_SEARCH,musicGenre);

        startActivity(intent);

    }

    public void onCheckboxClicked(View view) {

        boolean isCheckedCD = ((CheckBox) findViewById(R.id.cbFormatCD)).isChecked();
        boolean isCheckedVinyl = ((CheckBox) findViewById(R.id.cbFormatVinyl)).isChecked();
        Log.d(TAG, "onCheckboxClicked!!");
        //if(isCheckedCD)
    }
}
