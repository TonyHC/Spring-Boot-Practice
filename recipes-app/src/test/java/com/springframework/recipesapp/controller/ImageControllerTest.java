package com.springframework.recipesapp.controller;

import com.springframework.recipesapp.commands.RecipeCommand;
import com.springframework.recipesapp.service.ImageService;
import com.springframework.recipesapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {
    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    void showUploadFormTest() throws Exception {
        // Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        // When
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageUploadForm"))
                .andExpect(model().attributeExists("recipe"));

        // Then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void showUploadFormNumberFormatException() throws Exception {
        mockMvc.perform(get("/recipe/test/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400Error"));
    }

    @Test
    void handleImageUploadTest() throws Exception {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt",
                "text/plain", "Some String to Test".getBytes());

        // WHen
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImage(anyLong(), any());
    }

    @Test
    void renderImageFromDBTest() throws Exception {
        // Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String testString = "Some image text";
        Byte[] imageBytes = new Byte[testString.getBytes().length];

        int currentByte = 0;
        for (byte primitiveByte : testString.getBytes()) {
            imageBytes[currentByte++] = primitiveByte;
        }

        recipeCommand.setImage(imageBytes);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        // Then
        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(testString.getBytes().length, responseBytes.length);
    }
}