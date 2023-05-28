package com.nnk.springboot.services;


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
public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @InjectMocks
    private CurvePointService curvePointService;

    @Test
    void testGetCurvePoints() {
        // Arrange
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(new CurvePoint());
        when(curvePointRepository.findAll()).thenReturn(curvePointList);

        // Act
        List<CurvePointDTO> result = curvePointService.getCurvePoints();

        // Assert
        assertEquals(curvePointList.size(), result.size());
    }

    @Test
    void testSaveCurvePoint() {
        // Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        CurvePoint savedCurvePoint = new CurvePoint();
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(savedCurvePoint);

        // Act
        CurvePointDTO result = curvePointService.saveCurvePoint(curvePointDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetCurvePointByIdWithValidId() {
        // Arrange
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        // Act
        CurvePointDTO result = curvePointService.getCurvePointById(1);

        // Assert
        assertNotNull(result);
        assertEquals(curvePoint.getId(), result.getId());
    }

    @Test
    void testGetCurvePointByIdWithInvalidId() {
        // Arrange
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> curvePointService.getCurvePointById(1));
    }

    @Test
    void testDeleteCurvePoint() {
        // Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        CurvePoint curvePoint = new CurvePoint();

        // Act
        curvePointService.deleteCurvePoint(curvePointDTO);

        // Assert
        verify(curvePointRepository, times(1)).delete(eq(curvePoint));
    }
}
