package com.springframework.recipesapp.service;


import com.springframework.recipesapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}