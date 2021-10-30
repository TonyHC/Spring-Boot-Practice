package com.springframework.recipesapp.controller;

import com.springframework.recipesapp.commands.IngredientCommand;
import com.springframework.recipesapp.commands.RecipeCommand;
import com.springframework.recipesapp.commands.UnitOfMeasureCommand;
import com.springframework.recipesapp.service.IngredientService;
import com.springframework.recipesapp.service.RecipeService;
import com.springframework.recipesapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        model.addAttribute("recipe", recipeCommand);

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String ingredientId, Model model) {
        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/form")
    public String showIngredientForm(@PathVariable String recipeId, Model model) {
        // Make sure we have good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        // Need to return parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUnitOfMeasures());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredientForm(@PathVariable String recipeId,
                                       @PathVariable String ingredientId, Model model) {
        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        Set<UnitOfMeasureCommand> unitOfMeasureCommands = unitOfMeasureService.listAllUnitOfMeasures();

        model.addAttribute("recipeId", recipeId);

        model.addAttribute("ingredient", ingredientCommand);

        model.addAttribute("uomList", unitOfMeasureCommands);

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("Saved recipe id: " + savedCommand.getRecipeId());
        log.debug("Saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId) {
        ingredientService.deleteIngredientById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}