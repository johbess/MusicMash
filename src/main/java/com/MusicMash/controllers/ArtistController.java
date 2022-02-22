package com.MusicMash.controllers;

import com.MusicMash.models.Artist;
import com.MusicMash.services.ModelHandler;
import com.MusicMash.services.RequestHandler;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    /**
     * GET request for querying
     * an artist with a mbid.
     * @param mbid the id of the artist
     * @return returns a POJO Artist object as JSON to client.
     */
    @GetMapping("/artist/{mbid}")
    public Artist one(@PathVariable String mbid) {
        JSONObject result = RequestHandler.queryArtist(mbid);
        return ModelHandler.loadArtist(result);
    }
}
