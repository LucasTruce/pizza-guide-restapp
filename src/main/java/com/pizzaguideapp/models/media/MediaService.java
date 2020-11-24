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

    //@Transactional
    public MediaDto saveMediaUploadImage(MultipartFile file, Recipe recipe) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "verdoux",
                "api_key", "677878282762623",
                "api_secret", "qbzuQ9ZE19e8w4y1RDuFzXOMqOI"));

        String url = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url").toString();

        Media media1 = new Media();
        media1.setName(file.getOriginalFilename());
        media1.setLink(url);
        media1.setRecipe(recipe);

        media1 = mediaRepository.save(media1);
        //recipe.getMedia().add(media1);

        return mediaConverter.map(media1);
    }



}
