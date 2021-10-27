package com.springframework.recipesapp.service;

import com.springframework.recipesapp.commands.RecipeCommand;
import com.springframework.recipesapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    void deleteRecipeById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
}