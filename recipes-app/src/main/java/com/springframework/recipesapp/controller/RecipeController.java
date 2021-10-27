package com.springframework.recipesapp.controller;

import com.springframework.recipesapp.commands.RecipeCommand;
import com.springframework.recipesapp.domain.Recipe;
import com.springframework.recipesapp.exceptions.NotFoundException;
import com.springframework.recipesapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(Long.valueOf(id));

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @GetMapping("/recipe/form")
    public String showRecipeForm(Model model) {
        Recipe recipe = new Recipe();

        model.addAttribute("recipe", recipe);

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipeForm(@PathVariable String id, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(id));

        model.addAttribute("recipe", recipe);

        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipeForm(@Valid @ModelAttribute("recipe") RecipeCommand command,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return "recipe/recipeform";
        }

        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        Long recipeId = Long.valueOf(id);

        recipeService.deleteRecipeById(recipeId);

        return "redirect:/";
    }

    // We need to add @ResponseStatus(HttpStatus.NOT_FOUND) because
    // @ExceptionHandler has higher precedence than @ResponseBody (it will return 200 instead of 404)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // If any Controller Method in RecipeController throws an NotFoundException,
    // then display the custom 404Error.html page to the User with an HTTP Status of 404
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception exception) {
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView handleNumberFormatException(Exception exception) {
//        log.error("Handling Number Format Exception");
//        log.error(exception.getMessage());
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("400Error");
//        modelAndView.addObject("exception", exception);
//
//        return modelAndView;
//    }
}