package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    MyAdapterRecycledView myAdapterRecycledView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<DiscogsViewModel> requestList = new ArrayList<DiscogsViewModel>();

        DiscogsViewModel nirvana = new DiscogsViewModel("Nirvana");
        requestList.add(nirvana);
        DiscogsViewModel acdc = new DiscogsViewModel("ACDC");
        requestList.add(acdc);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapterRecycledView = new MyAdapterRecycledView(this, requestList);
        //myAdapterRecycledView.setClickListener(this);
        recyclerView.setAdapter(myAdapterRecycledView);


        Controller controller = new Controller();
        controller.start();

        myAdapterRecycledView.setDiscogsData(requestList);
    }
}