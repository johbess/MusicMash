package com.MusicMash.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CovertArtArchive {
    private static final String URL = "https://coverartarchive.org/release-group/";

    public static JSONArray queryImages(JSONArray albums) {
        ArrayList<CompletableFuture<JSONObject>> albumFutures = new ArrayList<>();
        for(int i = 0;i < albums.length();i++) {
            int index = i;
            albumFutures.add(CompletableFuture.supplyAsync(() -> {
                RestTemplate restTemplate = new RestTemplate();
                JSONObject curr = albums.getJSONObject(index);
                JSONArray id = curr.getJSONArray("id");
                try {
                    ResponseEntity<String> response = restTemplate.getForEntity(URL+id.getString(0), String.class);
                    if (response.getStatusCodeValue() == 404) {
                        curr.put("image", "Not found");
                    }
                    else {
                        JSONObject images = new JSONObject(response.getBody());
                        JSONArray imageArray = images.getJSONArray("images");
                        JSONObject image = (JSONObject) imageArray.get(0);
                        curr.put("image", image.getString("image"));
                    }
                }
                catch (HttpClientErrorException e) {
                    // No image found
                }
                return curr;
            }));
        }
        JSONArray result = new JSONArray();
        for (CompletableFuture<JSONObject> albumFuture : albumFutures) {
            JSONObject curr = albumFuture.join();
            result.put(curr);
        }
        return result;
    }
}
