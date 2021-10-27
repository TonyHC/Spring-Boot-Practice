package com.springframework.recipesapp.service;

import com.springframework.recipesapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredientById(Long recipeId, Long ingredientId);
}