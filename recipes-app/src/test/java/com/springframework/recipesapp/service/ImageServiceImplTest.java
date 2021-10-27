package com.springframework.recipesapp.service;

import com.springframework.recipesapp.domain.Recipe;
import com.springframework.recipesapp.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    ImageServiceImpl imageService;

    @Captor
    ArgumentCaptor<Recipe> recipeArgumentCaptor;

    @Test
    void saveImageTest() throws IOException {
        // Given
        Long recipeId = 1L;

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        MultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt",
                "text/plain", "Test String".getBytes());

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        // When
        imageService.saveImage(recipeId, multipartFile);

        // Then
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}