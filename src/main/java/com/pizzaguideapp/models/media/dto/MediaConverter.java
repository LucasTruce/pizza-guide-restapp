package com.pizzaguideapp.models.media.dto;

import com.pizzaguideapp.models.media.Media;
import com.pizzaguideapp.models.recipes.dto.RecipeConverter;

import java.util.List;
import java.util.stream.Collectors;

public class MediaConverter {

    public MediaDto map(Media media) {
        RecipeConverter recipeConverter = new RecipeConverter();

        return new MediaDto(
                media.getName(),
                media.getLink()
                //recipeConverter.map(media.getRecipe())
        );
    }

    public Media map(MediaDto mediaDto) {
        Media media = new Media();
        media.setName(mediaDto.getName());
        media.setLink(mediaDto.getLink());
        return media;
    }


    public List<MediaDto> map(List<Media> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
