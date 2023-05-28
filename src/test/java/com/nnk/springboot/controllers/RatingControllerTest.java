package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RatingControllerTest {
    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @Test
    public void testHome() {
        // Arrange
        List<Rating> ratings = new ArrayList<>();
        when(ratingService.getRatings()).thenReturn(ratings);

        // Act
        String viewName = ratingController.home(model);

        // Assert
        assertEquals("rating/list", viewName);
        verify(model).addAttribute("ratings", ratings);
    }

    @Test
    public void testAddRatingForm() {
        // Act
        String viewName = ratingController.addRatingForm(model);

        //Assert
        assertEquals("rating/add", viewName);
        verify(model).addAttribute("rating", new Rating());
    }

    @Test
    public void testValidateWithValidRating() {
        // Arrange
        Rating rating = new Rating();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ratingService.getRatings()).thenReturn(new ArrayList<>());

        // Act
        String viewName = ratingController.validate(rating, result, model);

        // Assert
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService).saveRating(rating);
        verify(model).addAttribute("ratings", new ArrayList<>());
    }

    @Test
    public void testValidateWithInvalidRating() {
        // Arrange
        Rating rating = new Rating();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // Act
        String viewName = ratingController.validate(rating, result, model);

        // Assert
        assertEquals("rating/add", viewName);
        verify(model, never()).addAttribute(eq("ratings"), any());
    }

    @Test
    public void testShowUpdateForm() {
        // Arrange
        Integer id = 1;
        Rating rating = new Rating();
        when(ratingService.getRatingById(id)).thenReturn(rating);

        // Act
        String viewName = ratingController.showUpdateForm(id, model);

        // Assert
        assertEquals("rating/update", viewName);
        verify(model).addAttribute("rating", rating);
    }

    @Test
    public void testUpdateRatingWithValidRating() {
        // Arrange
        Integer id = 1;
        Rating rating = new Rating();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ratingService.getRatings()).thenReturn(new ArrayList<>());

        // Act
        String viewName = ratingController.updateRating(id, rating, result, model);

        // Assert
        verify(ratingService).saveRating(rating);
        assertEquals("redirect:/rating/list", viewName);
    }

    @Test
    public void testUpdateRatingWithInvalidRating() {
        // Arrange
        Integer id = 1;
        Rating rating = new Rating();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // Act
        String viewName = ratingController.updateRating(id, rating, result, model);

        // Assert
        assertEquals("/rating/update", viewName);
    }

    @Test
    public void testDeleteRating() {
        Integer id = 1;
        Rating rating = new Rating();
        when(ratingService.getRatingById(id)).thenReturn(rating);

        String viewName = ratingController.deleteRating(id, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService).deleteRating(rating);
    }

}
