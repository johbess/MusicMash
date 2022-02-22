package com.MusicMash.assemblers;

import org.json.JSONObject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ArtistModelAssembler implements RepresentationModelAssembler<JSONObject, EntityModel<JSONObject>> {
    @Override
    public EntityModel<JSONObject> toModel(JSONObject entity) {
        return EntityModel.of(entity);
    }
}
