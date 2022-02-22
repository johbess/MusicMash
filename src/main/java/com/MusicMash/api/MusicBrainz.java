package com.MusicMash.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

public class MusicBrainz {
    private static String URL = "http://musicbrainz.org/ws/2/";

    public static JSONArray queryAlbums(String mbid, RestTemplate restTemplate) {
        String response = restTemplate.getForObject(URL+"artist/"+mbid+"?&f_mt=json&inc=release-groups",String.class);
        return parseAlbums(response);
    }

    @Nullable
    public static String queryWikiToken(String mbid, RestTemplate restTemplate) throws URISyntaxException {
        String response = restTemplate.getForObject(URL+"artist/"+mbid+"?&f_mt=json&inc=url-rels", String.class);
        return parseToken(response);
    }

    private static JSONArray parseAlbums(String response) {
        JSONObject tmp = new JSONObject(response);
        JSONArray jsonarray = tmp.getJSONArray("release-groups");
        JSONArray albums = new JSONArray();
        JSONObject newObj;
        for(int i = 0;i < jsonarray.length();i++) {
            JSONObject curr = jsonarray.getJSONObject(i);
            if (curr.getString("primary-type").equals("Album")) {
                newObj = new JSONObject();
                newObj
                    .append("image", "Could not be retrieved")
                    .append("title", curr.getString("title"))
                    .append("id", curr.getString("id"));
                albums.put(newObj);
            }
        }
        return albums;
    }

    @Nullable
    private static String parseToken(String response) throws URISyntaxException {
        JSONObject tmp = new JSONObject(response);
        JSONArray jsonArray = new JSONArray(tmp.getJSONArray("relations"));
        String token;
        for (int i = 0;i < jsonArray.length();i++) {
            JSONObject curr = jsonArray.getJSONObject(i);
            if (curr.getString("type").equals("wikidata")) {
                String url = curr.getJSONObject("url").getString("resource");
                URI uri = new URI(url);
                String path = uri.getPath();
                token = path.substring(path.lastIndexOf("/")+1);
                return token;
            }
        }
        return null;
    }
}
