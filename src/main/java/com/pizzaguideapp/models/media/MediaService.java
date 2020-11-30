package com.pizzaguideapp.models.media;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pizzaguideapp.models.media.dto.MediaConverter;
import com.pizzaguideapp.models.media.dto.MediaDto;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.recipes.dto.RecipeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaConverter mediaConverter;
    private final RecipeConverter recipeConverter;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        this.mediaConverter = new MediaConverter();
        this.recipeConverter = new RecipeConverter();
    }

    public List<MediaDto> getAllForRecipe(Long recipeId){
        return mediaConverter.map(mediaRepository.findAllByRecipeId(recipeId));
    }

    //@Transactional
    public List<MediaDto> saveMediaUploadImage(List<MultipartFile> files, Recipe recipe) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "verdoux",
                "api_key", "677878282762623",
                "api_secret", "qbzuQ9ZE19e8w4y1RDuFzXOMqOI"));

//        String url = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url").toString();
//        Media media1 = new Media();
//        media1.setName(file.getOriginalFilename());
//        media1.setLink(url);
//        media1.setRecipe(recipe);

        List<Media> mediaList = new ArrayList<>();
        for(MultipartFile file : files){
            String url2 = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url").toString();
            mediaList.add(new Media(null, file.getOriginalFilename(), url2, recipe));
        }

        mediaList = mediaRepository.saveAll(mediaList);
        //recipe.getMedia().add(media1);

        return mediaConverter.map(mediaList);
    }



}
