package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceTest {
    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @InjectMocks
    private TradeService tradeService;

    @Test
    void testGetTrades() {
        // Arrange
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(new Trade());
        when(tradeRepository.findAll()).thenReturn(tradeList);

        // Act
        List<TradeDTO> result = tradeService.getTrades();

        // Assert
        assertEquals(tradeList.size(), result.size());
    }

    @Test
    void testSaveTrade() {
        // Arrange
        Trade trade = new Trade();
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        // Act
        TradeDTO result = tradeService.saveTrade(new TradeDTO());

        // Assert
        assertNotNull(result);
        assertEquals(trade.getTradeId(), result.getId());
    }

    @Test
    void testGetTradeByIdWithValidId() {
        // Arrange
        Trade trade = new Trade();
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        // Act
        TradeDTO result = tradeService.getTradeById(1);

        // Assert
        assertNotNull(result);
        assertEquals(trade.getTradeId(), result.getId());
    }

    @Test
    void testGetTradeByIdWithInvalidId() {
        // Arrange
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> tradeService.getTradeById(1));
    }

    @Test
    void testDeleteTrade() {
        // Arrange
        Trade trade = new Trade();

        // Act
        tradeService.deleteTrade(new TradeDTO());

        // Assert
        verify(tradeRepository, times(1)).delete(eq(trade));
    }
}
