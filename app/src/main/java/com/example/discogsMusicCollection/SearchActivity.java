package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {

    public static final String TAG = "SearchActivity";
    public static final String ARTIST_TO_SEARCH = "artist";
    public static final String TITLE_TO_SEARCH = "title";
    public static final String FORMAT_TO_SEARCH = "format";
    public static final String YEAR_TO_SEARCH = "year";

    MyAdapterRecycledView myAdapterRecycledView;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();

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
        EditText etFormat = (EditText)findViewById(R.id.etFormat);
        EditText etYear = (EditText)findViewById(R.id.etYear);

        String artist = etArtist.getText().toString();
        String title = etTitle.getText().toString();
        String format = etFormat.getText().toString();
        String year = etYear.getText().toString();

        intent.putExtra(ARTIST_TO_SEARCH,artist);
        intent.putExtra(TITLE_TO_SEARCH,title);
        intent.putExtra(FORMAT_TO_SEARCH,format);
        intent.putExtra(YEAR_TO_SEARCH,format);

        startActivity(intent);

    }
}
