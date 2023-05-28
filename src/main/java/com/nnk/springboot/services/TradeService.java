package com.nnk.springboot.services;

import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @Autowired
    private TradeRepository tradeRepository;

    public List<TradeDTO> getTrades() {
        List<Trade> trades = tradeRepository.findAll();
        List<TradeDTO> tradeDTO = mapper.tradesToDTO(trades);
        return tradeDTO;
    }

    public TradeDTO saveTrade(TradeDTO tradeDTO) {
        Trade trade = mapper.tradeDTOToTrade(tradeDTO);
        trade = tradeRepository.save(trade);
        return mapper.tradeToDTO(trade);
    }

    public TradeDTO getTradeById(Integer id){
        Optional<Trade> trade = tradeRepository.findById(id);

        if(trade.isPresent()) {
            TradeDTO tradeDTO = mapper.tradeToDTO(trade.get());
            return tradeDTO;
        } else {
            throw new IllegalArgumentException("Invalid trade Id:" + id);
        }
    }

    public void deleteTrade(TradeDTO tradeDTO){
        Trade trade = mapper.tradeDTOToTrade(tradeDTO);
        tradeRepository.delete(trade);
    }

}
