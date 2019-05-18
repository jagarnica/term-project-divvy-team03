package com.example.divvy.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divvy.NetworkReceiver;
import com.example.divvy.R;
import com.example.divvy.models.Listing;

import java.util.ArrayList;

public class DetailedListingController extends AppCompatActivity {

    Listing listing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        listing = ((ArrayList<Listing>)intent.getSerializableExtra("data")).get(0);
        System.out.println("Detailed listings: " + listing.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_listings_controller);

        setUI();
    }

    public void setUI(){
        TextView titleText = findViewById(R.id.detailed_lst_title);
        TextView descText = findViewById(R.id.detailed_desc);
        TextView ownerText = findViewById(R.id.detailed_owner);
        ImageView ownerImg = findViewById(R.id.detailed_owner_img);
        if(listing != null){
            titleText.setText(listing.getTitle());
            descText.setText(listing.getDescr());
            ownerText.setText(listing.getOwner());
        }
    }
}
