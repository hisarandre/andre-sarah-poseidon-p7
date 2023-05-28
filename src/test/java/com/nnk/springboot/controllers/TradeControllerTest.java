package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class TradeControllerTest {

    @InjectMocks
    private TradeController tradeController;

    @Mock
    private TradeService tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testHome() {
        //Arrange
        List<TradeDTO> trades = new ArrayList<>();
        when(tradeService.getTrades()).thenReturn(trades);

        //Act
        String viewName = tradeController.home(model);

        //Assert
        assertEquals("trade/list", viewName);
        verify(model).addAttribute("trades", trades);
    }

    @Test
    public void testAddUser() {
        //Act
        String viewName = tradeController.addUser(model);

        //Assert
        assertEquals("trade/add", viewName);
        verify(model).addAttribute(eq("tradeDTO"), any(TradeDTO.class));
    }

    @Test
    public void testValidateWithValidTradeDTO() {
        //Arrange
        TradeDTO tradeDTO = new TradeDTO();
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String viewName = tradeController.validate(tradeDTO, bindingResult, model);

        //Assert
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).saveTrade(tradeDTO);
        verify(model).addAttribute("trades", tradeService.getTrades());
    }

    @Test
    public void testValidateWithInvalidTradeDTO() {
        //Arrange
        TradeDTO tradeDTO = new TradeDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        //Act
        String viewName = tradeController.validate(tradeDTO, bindingResult, model);

        //Assert
        assertEquals("trade/add", viewName);
        verify(model, never()).addAttribute(eq("trades"), any());
    }

    @Test
    public void testShowUpdateForm() {
        //Arrange
        Integer id = 1;
        TradeDTO tradeDTO = new TradeDTO();
        when(tradeService.getTradeById(id)).thenReturn(tradeDTO);

        //Act
        String viewName = tradeController.showUpdateForm(id, model);

        //Assert
        assertEquals("trade/update", viewName);
        verify(model).addAttribute("tradeDTO", tradeDTO);
    }

    @Test
    public void testUpdateTradeWithValidTradeDTO() {
        //Arrange
        Integer id = 1;
        TradeDTO tradeDTO = new TradeDTO();
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String viewName = tradeController.updateTrade(id, tradeDTO, bindingResult, model);

        //Assert
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).saveTrade(tradeDTO);
        verify(model).addAttribute("trades", tradeService.getTrades());
    }

    @Test
    public void testUpdateTradeWithInvalidTradeDTO() {
        //Arrange
        Integer id = 1;
        TradeDTO tradeDTO = new TradeDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        //Act
        String viewName = tradeController.updateTrade(id, tradeDTO, bindingResult, model);

        //Assert
        assertEquals("/trade/update", viewName);
        verify(model, never()).addAttribute(eq("trades"), any());
    }

    @Test
    public void testDeleteTrade() {
        //Arrange
        Integer id = 1;
        TradeDTO tradeDTO = new TradeDTO();
        when(tradeService.getTradeById(id)).thenReturn(tradeDTO);

        //Act
        String viewName = tradeController.deleteTrade(id, model);

        //Assert
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).deleteTrade(tradeDTO);
    }
}
