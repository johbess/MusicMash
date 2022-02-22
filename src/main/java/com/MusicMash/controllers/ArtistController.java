package com.MusicMash.controllers;

import com.MusicMash.apihandlers.RequestHandler;
import com.MusicMash.assemblers.ArtistModelAssembler;
import org.json.JSONObject;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {
    private ArtistModelAssembler artistModelAssembler;

    public ArtistController(ArtistModelAssembler artistModelAssembler) {
        this.artistModelAssembler = artistModelAssembler;
    }

    @GetMapping("/artist/{mbid}")
    public EntityModel<JSONObject> one(@PathVariable String mbid) {
        JSONObject result = RequestHandler.queryArtist(mbid);
        return artistModelAssembler.toModel(result);
    }
}
