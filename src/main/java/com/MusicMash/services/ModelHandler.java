package com.MusicMash.services;

import com.MusicMash.models.Album;
import com.MusicMash.models.Artist;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelHandler {

    /**
     * Loads an artist with the results from
     * the API mesh
     *
     * @param json json file with mbid, description of artist and albums.
     * @return an artist
     */
    public static Artist loadArtist(JSONObject json) {
        String mbid = json.getJSONArray("mbid").getString(0);
        String description = json.getJSONArray("description").getString(0);
        JSONArray albums = json.getJSONArray("albums").getJSONArray(0);
        int albumSize = albums.length();
        Album[] albumArray = new Album[albumSize];
        for (int i = 0;i < albumSize;i++) {
            JSONObject curr = albums.getJSONObject(i);
            Album album = new Album();
            String title = curr.getJSONArray("title").getString(0);
            String id = curr.getJSONArray("id").getString(0);
            String image;
            if (curr.get("image") instanceof String) {
                image = curr.getString("image");
            }
            else {
                image = curr.getJSONArray("image").getString(0);
            }
            album.setTitle(title);
            album.setId(id);
            album.setImageUrl(image);
            albumArray[i] = album;
        }
        Artist artist = new Artist(mbid);
        artist.setDescription(description);
        artist.setAlbums(albumArray);
        return artist;
    }
}
