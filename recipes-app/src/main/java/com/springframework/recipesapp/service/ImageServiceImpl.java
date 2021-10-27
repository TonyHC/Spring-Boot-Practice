package com.springframework.recipesapp.service;

import com.springframework.recipesapp.domain.Recipe;
import com.springframework.recipesapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImage(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] imageBytes = new Byte[file.getBytes().length];

            // MultipartFile use primitive byte[]
            int currentByte = 0;
            for (byte fileByte : file.getBytes()) {
                imageBytes[currentByte++] = fileByte;
            }

            recipe.setImage(imageBytes);

            recipeRepository.save(recipe);
        } catch (IOException exception) {
            log.error("Error: " + exception);
            exception.printStackTrace();;
        }
    }
}