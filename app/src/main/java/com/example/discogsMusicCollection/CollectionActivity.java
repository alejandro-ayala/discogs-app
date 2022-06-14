package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discogsMusicCollection.memoryManager.FavouriteMusicEntity;
import com.example.discogsMusicCollection.memoryManager.FavouriteMusicViewModel;

public class CollectionActivity extends AppCompatActivity
{
    public static final String TAG = "CollectionActivity";
    public static final String MUSIC_COLLECTION = FavouriteMusicEntity.TABLA + ".db";
    adapterRecyclerViewFavourite favouriteRecycledView;

    int position = 0;
    FavouriteMusicViewModel favouriteMusicViewModel;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);

        Intent intent = getIntent();

        favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);

        // Observer to async read the database
        favouriteMusicViewModel.getAll().observe(this, musicCollection -> {

            Log.d(TAG, "Reading Collection music");

                final RecyclerView recyclerView = findViewById(R.id.recyclerViewFavourite);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                favouriteRecycledView = new adapterRecyclerViewFavourite(this,musicCollection);
            adapterRecyclerViewFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    position = recyclerView.getChildLayoutPosition(view);

                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_msg, null);

                    int width = 1200;
                    int height = 1200;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    popupWindow = new PopupWindow(popupView, width, height, focusable);
                    TextView releaseInfo = (TextView) popupView.findViewById(R.id.tvReleaseInfo);
                    String title = musicCollection.get(position).getTitle();
                    String year = musicCollection.get(position).getYear();
                    String format = musicCollection.get(position).getFormat();
                    String label = musicCollection.get(position).getLabel();
                    String country = musicCollection.get(position).getCountry();
                    releaseInfo.setText(title + "\n" + year + "\n" + format + "\n" + label + "\n" + country);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                }
            });
                recyclerView.setAdapter(favouriteRecycledView);


        });
    }

    public void deleteRelease (View v) {
        Log.d(TAG, "onClick --> Delete release");
        FavouriteMusicEntity itemToDelete = favouriteRecycledView.getItemByPosition(position);
        favouriteMusicViewModel.delete(itemToDelete);
        popupWindow.dismiss();
    }
}
