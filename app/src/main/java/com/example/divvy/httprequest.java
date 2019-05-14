package com.example.divvy;


import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class httprequest {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    //http://ec2-34-226-139-149.compute-1.amazonaws.com/login   (get)
    //http://ec2-34-226-139-149.compute-1.amazonaws.com/reg     (post)
    //http://ec2-34-226-139-149.compute-1.amazonaws.com/newListing  (post)
    public static String get(Map<String,String> params, String uri) throws IOException {
        HttpUrl.Builder httpBuider = HttpUrl.parse(uri).newBuilder();
        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuider.addQueryParameter(param.getKey(),param.getValue());
            }
        }
        String url = httpBuider.build().toString();
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}