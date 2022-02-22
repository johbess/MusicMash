package com.MusicMash.services;

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

    /**
     * Service for querying the mesh of APIs from
     * MusicBrainz to find all albums of an artist.
     * Within MusicBrainz we can collect the token
     * which identifies the Wikipedia URL from
     * WikiData.
     *
     * The service invokes CompletableFutures to
     * be able to query each respective API in
     * parallel as there might take time to receive
     * a response, such as CovertArtArchive where
     * one request is made for each album.
     *
     * @param mbid id for the artist
     * @return an artist
     */
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
