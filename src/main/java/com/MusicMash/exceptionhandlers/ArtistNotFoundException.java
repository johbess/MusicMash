package com.MusicMash.exceptionhandlers;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(String mbid) {
        super("Could not find artist " + mbid);
    }
}
