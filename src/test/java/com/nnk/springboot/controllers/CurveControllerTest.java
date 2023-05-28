package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CurveControllerTest {

    @Mock
    private CurvePointService curvePointService;

    @Mock
    private Model model;

    @InjectMocks
    private CurveController curveController;


    @Test
    public void testHome() {
        // Arrange
        List<CurvePointDTO> curvePoints = new ArrayList<>();
        when(curvePointService.getCurvePoints()).thenReturn(curvePoints);

        // Act
        String viewName = curveController.home(model);

        // Assert
        verify(curvePointService).getCurvePoints();
        assertEquals("curvePoint/list", viewName);
    }

    @Test
    public void testAddCurvePointForm() {
        // Arrange

        // Act
        String viewName = curveController.addCurvePointForm(model);

        // Assert
        assertEquals("curvePoint/add", viewName);
        verify(model).addAttribute(eq("curvePointDTO"), any(CurvePointDTO.class));

    }

    @Test
    public void testValidateWithValidCurvePointDTO() {
        //Arrange
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        curvePointDTO.setCurveId(1);

        //Act
        String viewName = curveController.validate(curvePointDTO, result, model);

        // Assert
        verify(curvePointService).saveCurvePoint(curvePointDTO);
        assertEquals("redirect:/curvePoint/list", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        //Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        when(curvePointService.getCurvePointById(1)).thenReturn(curvePointDTO);

        //Act
        String viewName = curveController.showUpdateForm(1, model);

        // Assert
        assertEquals("curvePoint/update", viewName);
    }

    @Test
    public void testUpdateBidWithValidCurvePointDTO() {
        //Arrange
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        curvePointDTO.setCurveId(1);

        // Act
        String viewName = curveController.updateBid(1, curvePointDTO, result, model);

        // Assert
        verify(curvePointService).saveCurvePoint(curvePointDTO);
        assertEquals("redirect:/curvePoint/list", viewName);
    }

    @Test
    public void testDeleteBid() {
        // Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        when(curvePointService.getCurvePointById(1)).thenReturn(curvePointDTO);

        // Act
        String viewName = curveController.deleteBid(1, model);

        // Assert
        verify(curvePointService).deleteCurvePoint(curvePointDTO);
        assertEquals("redirect:/curvePoint/list", viewName);
    }
}
