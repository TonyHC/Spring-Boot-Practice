package com.springframework.recipesapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    // Recipe owns the bi-directional relationship between Ingredient through mappedBy
    // If we delete a Recipe, it also deletes all Ingredients
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    // DB should store the property as Large Object,
    // so we won't have to worry about the size limit
    @Lob
    private Byte[] image;

    // Persisted Enum Property as a String
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // Recipe owns the uni-directional relationship between Notes
    // If we delete a Recipe, it also deletes the Notes
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        // Establish the One To One Relationship
        notes.setRecipe(this);
    }

    // Helper Method: To Add Bi-Directional Relationship
    public Recipe addIngredient(Ingredient ingredient) {
        // Establish the One To Many Relationship
        ingredient.setRecipe(this);
        // Add Ingredient to Ingredient List
        this.ingredients.add(ingredient);
        return this;
    }

    // Helper Method: To Remove Bi-Directional Relationship
    public void removeIngredients() {
        // Remove the One To Many Relationship
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(null);
        }
    }
}