package com.MusicMash.models;

/**
 * POJO for an album containing
 * a field for the album title,
 * an id for the album id
 * and possible an image URL
 * if it was found during the
 * API mesh queries.
 */
public class Album {
    private String title;
    private String id;
    private String image;

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }
}
