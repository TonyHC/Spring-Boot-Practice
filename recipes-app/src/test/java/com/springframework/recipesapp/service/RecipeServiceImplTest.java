package com.springframework.recipesapp.service;

import com.springframework.recipesapp.domain.Recipe;
import com.springframework.recipesapp.exceptions.NotFoundException;
import com.springframework.recipesapp.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Test
    void getRecipes() {
        // Given
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        // When
        Set<Recipe> recipes = recipeService.getRecipes();

        // Then
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void getRecipeByIdNotFound() {
        Optional<Recipe> optionalRecipe = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        assertThrows(NotFoundException.class, () -> recipeService.findById(anyLong()));
    }

    @Test
    void deleteRecipeById() {
        Long recipeIdToDelete = 2L;

        recipeService.deleteRecipeById(recipeIdToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}