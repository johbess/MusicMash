package com.MusicMash.apihandlers;

import com.MusicMash.api.CovertArtArchive;
import com.MusicMash.api.MusicBrainz;
import com.MusicMash.api.WikiData;
import com.MusicMash.api.Wikipedia;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class RequestHandler {

    public static JSONObject queryArtist(String mbid) {
        CompletableFuture<JSONArray> albumsFuture = CompletableFuture
                .supplyAsync(() -> MusicBrainz.queryAlbums(mbid,new RestTemplate()));

        CompletableFuture<String> tokenFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return MusicBrainz.queryWikiToken(mbid,new RestTemplate());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
        String token = tokenFuture.join();
        CompletableFuture<String> urlFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return WikiData.queryWikiUrl(token,new RestTemplate());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return null;
                });

        String url = urlFuture.join();
        JSONArray albums = albumsFuture.join();
        JSONArray albumsWithImage = CovertArtArchive.queryImages(albums);
        String description = Wikipedia.queryDescription(url);
        JSONObject result = new JSONObject();
        result.append("mbid", mbid);
        result.append("description", description);
        result.append("albums", albumsWithImage);
        return result;
    }
}
