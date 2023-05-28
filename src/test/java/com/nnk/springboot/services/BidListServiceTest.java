package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BidListServiceTest {
    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @InjectMocks
    private BidListService bidListService;

    @Test
    public void testGetBidLists() {
        // Arrange
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(new BidList());
        bidLists.add(new BidList());

        when(bidListRepository.findAll()).thenReturn(bidLists);

        // Act
        List<BidListDTO> bidListDTOs = bidListService.getBidLists();
        assertEquals(bidLists.size(), bidListDTOs.size());
    }

    @Test
    public void testSaveBidList() {
        //Arrange
        BidListDTO bidListDTO = new BidListDTO();
        BidList bidList = new BidList();
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        // Act
        BidListDTO savedBidListDTO = bidListService.saveBidList(bidListDTO);

        // Assert
        assertNotNull(savedBidListDTO);
    }

    @Test
    public void testGetBidListByIdWithValidId() {
        // Arrange
        int id = 1;
        BidList bidList = new BidList();
        bidList.setBidListId(id);
        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        // Act
        BidListDTO bidListDTO = bidListService.getBidListById(id);

        // Assert
        assertNotNull(bidListDTO);
        assertEquals(id, bidListDTO.getId().intValue());
    }

    @Test
    public void testDeleteBidList() {
        // Arrange
        BidListDTO bidListDTO = new BidListDTO();
        BidList bidList = new BidList();

        // Act
        bidListService.deleteBidList(bidListDTO);

        // Assert
        verify(bidListRepository).delete(bidList);
    }
}
