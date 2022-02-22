package com.MusicMash.models;


/**
 * POJO for an artist holding
 * an array of albums, a description
 * and a mbid.
 */
public class Artist {
    private Album[] albums;
    private String description;
    private String mbid;

    public Artist(String mbid) {
        this.mbid = mbid;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Album[] getAlbums() {
        return albums;
    }

    public void setAlbums(Album[] albums) {
        this.albums = albums;
    }
}
