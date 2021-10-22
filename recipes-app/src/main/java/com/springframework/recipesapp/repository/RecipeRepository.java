package com.springframework.recipesapp.repository;

import com.springframework.recipesapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}