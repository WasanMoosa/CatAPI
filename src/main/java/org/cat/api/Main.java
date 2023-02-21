package org.cat.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
    final static String directoryName = "data";

    public static void main(String[] args) {
        String url = "https://api.thecatapi.com/v1/images/search";
        // Create an HTTP client
        OkHttpClient client = new OkHttpClient();
        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            // Get response of API
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();

            // Deserialize json response
            ArrayList<Result> results = gson.fromJson(responseBody, new TypeToken<ArrayList<Result>>() {
            }.getType());

            // Print the details of cat
            results.forEach(
                    (r) -> {
                        System.out.println("This is your cat!");
                        System.out.println(r.getUrl());
                        // Save image in folder
                        SaveImage saveImage = new SaveImage();
                        saveImage.ImageDownloader(r.getUrl(), r.getId());
                    }
            );


        } catch (IOException e) {
            System.out.println("No connection!");
        }


    }
}