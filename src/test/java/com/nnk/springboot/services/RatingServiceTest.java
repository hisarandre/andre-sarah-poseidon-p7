package com.nnk.springboot.services;

import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RatingServiceTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @InjectMocks
    private RatingService ratingService;

    @Test
    void testGetRatings() {
        // Arrange
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(new Rating());
        when(ratingRepository.findAll()).thenReturn(ratingList);

        // Act
        List<Rating> result = ratingService.getRatings();

        // Assert
        assertEquals(ratingList.size(), result.size());
    }

    @Test
    void testSaveRating() {
        // Arrange
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        // Act
        Rating result = ratingService.saveRating(rating);

        // Assert
        assertNotNull(result);
        assertEquals(rating.getId(), result.getId());
    }

    @Test
    void testGetRatingByIdWithValidId() {
        // Arrange
        Rating rating = new Rating();
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        // Act
        Rating result = ratingService.getRatingById(1);

        // Assert
        assertNotNull(result);
        assertEquals(rating, result);
    }

    @Test
    void testGetRatingByIdWithInvalidId() {
        // Arrange
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ratingService.getRatingById(1));
    }

    @Test
    void testDeleteRating() {
        // Arrange
        Rating rating = new Rating();

        // Act
        ratingService.deleteRating(rating);

        // Assert
        verify(ratingRepository, times(1)).delete(eq(rating));
    }

}
