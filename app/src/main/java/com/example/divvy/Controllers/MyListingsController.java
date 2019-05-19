package com.example.divvy.Controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.divvy.ListingService;
import com.example.divvy.NetworkReceiver;
import com.example.divvy.R;
import com.example.divvy.models.Listing;
import com.example.divvy.models.RecyclerViewAdapter;

import java.util.ArrayList;

public class MyListingsController extends DisplayListingsController{
    public final String MY_LISTING_CODE = "mylistings";
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.listings_view);
        NavBarController.setUpListners(findViewById(R.id.navigation), this);
        if(intent.getSerializableExtra("data") != null) {
            listings = (ArrayList<Listing>) intent.getSerializableExtra("data");
        }
        UpdateListings();


    }

    @Override
    public void UpdateListingsView() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listings);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginAuthenticator authenticator = LoginAuthenticator.getInstance();
        ListingService.GetListingsByUsername(this,mReceiver,authenticator.getUser(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listings);
        recyclerView = findViewById(R.id.listings_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        listings = (ArrayList<Listing>)resultData.get("data");
        UpdateListingsView();
    }

    private void UpdateListings(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listings);
        RecyclerView recyclerView = findViewById(R.id.listings_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}