package com.example.divvy;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.divvy.models.Review;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class CreateRatingService extends IntentService {
    public CreateRatingService() {
        super("Get Rating Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int type = intent.getIntExtra("type",-1);
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        System.out.println(intent.getStringExtra("uri"));
        System.out.println(intent.getStringExtra("data"));
        try {
            String response = httprequest.post(intent.getStringExtra("uri"), intent.getStringExtra("data"));
            bundle.putString("response",response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiver.send(1,bundle);
    }

}