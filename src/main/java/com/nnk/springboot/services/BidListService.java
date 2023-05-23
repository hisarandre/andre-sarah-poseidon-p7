package com.nnk.springboot.services;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    private static Logger logger = LoggerFactory.getLogger(BidListController.class);

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidListDTO> getBidLists() {
        logger.info("Get bidlists and return bidlists DTO");

        List<BidList> bidLists = bidListRepository.findAll();
        List<BidListDTO> bidListDTO = mapper.bidlistsToDTO(bidLists);
        return bidListDTO;
    }

    public BidListDTO saveBidList(BidListDTO bidListDTO) {
        logger.info("Save bidlist and return DTO");

        BidList bidList = mapper.bidListDTOToBidList(bidListDTO);

        bidList = bidListRepository.save(bidList);
        return mapper.bidListToDTO(bidList);
    }

    public BidListDTO getBidListById(Integer id){
        logger.info("Get bidlist by id and return DTO");

        Optional<BidList> bidList = bidListRepository.findById(id);

        if(bidList.isPresent()) {
            BidListDTO bidListDTO = mapper.bidListToDTO(bidList.get());
            return bidListDTO;
        } else {
            throw new IllegalArgumentException("Invalid bidlist Id:" + id);
        }
    }

    public void deleteBidList(BidListDTO bidListDTO){
        logger.info("Delete bidlist");

        BidList bidList = mapper.bidListDTOToBidList(bidListDTO);
        bidListRepository.delete(bidList);
    }
}
