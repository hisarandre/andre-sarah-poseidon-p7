package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BidListControllerTest {

    @Mock
    private BidListService bidListService;

    @Mock
    private Model model;

    @InjectMocks
    private BidListController bidListController;

    @Test
    void testHome() {
        // Arrange
        List<BidListDTO> bidLists = new ArrayList<>();
        when(bidListService.getBidLists()).thenReturn(bidLists);

        // Act
        String viewName = bidListController.home(model);

        // Assert
        assertEquals("bidList/list", viewName);
        verify(model).addAttribute("bidLists", bidLists);
    }

    @Test
    void testAddBidForm() {
        // Arrange

        // Act
        String viewName = bidListController.addBidForm(model);

        // Assert
        assertEquals("bidList/add", viewName);
        verify(model).addAttribute(eq("bidListDTO"), any(BidListDTO.class));
    }

    @Test
    void testValidateWithValidBidListDTO() {
        // Arrange
        BidListDTO bidListDTO = new BidListDTO();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // Act
        String viewName = bidListController.validate(bidListDTO, result, model);

        // Assert
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService).saveBidList(bidListDTO);
        verify(model).addAttribute("bidLists", bidListService.getBidLists());
    }

    @Test
    void testValidateWithInvalidBidListDTO() {
        // Arrange
        BidListDTO bidListDTO = new BidListDTO();
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // Act
        String viewName = bidListController.validate(bidListDTO, result, model);

        // Assert
        assertEquals("bidList/add", viewName);
        verifyNoInteractions(bidListService);
    }

    @Test
    void testShowUpdateForm() {
        // Arrange
        Integer bidListId = 1;
        BidListDTO bidListDTO = new BidListDTO();
        when(bidListService.getBidListById(bidListId)).thenReturn(bidListDTO);

        // Act
        String viewName = bidListController.showUpdateForm(bidListId, model);

        // Assert
        assertEquals("bidList/update", viewName);
        verify(model).addAttribute("bidListDTO", bidListDTO);
    }

    @Test
    public void testUpdateBidWithValidBidListDTO() {
        // Arrange
        BindingResult result = Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setId(1);
        bidListDTO.setAccount("Test Account");

        // Act
        String viewName = bidListController.updateBid(1, bidListDTO, result, model);

        // Assert
        verify(bidListService).saveBidList(bidListDTO);
        assertEquals("redirect:/bidList/list", viewName);
    }

    @Test
    public void testDeleteBid() {
        // Arrange
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setId(1);
        when(bidListService.getBidListById(1)).thenReturn(bidListDTO);

        // Act
        String viewName = bidListController.deleteBid(1, model);

        // Assert
        verify(bidListService).deleteBidList(bidListDTO);
        assertEquals("redirect:/bidList/list", viewName);
    }

}