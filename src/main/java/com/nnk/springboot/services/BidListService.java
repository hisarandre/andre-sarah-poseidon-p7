package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidListDTO> getBidLists() {
        List<BidList> bidLists = bidListRepository.findAll();
        List<BidListDTO> bidListDTO = mapper.bidlistsToDTO(bidLists);
        return bidListDTO;
    }

    public BidListDTO saveBidList(BidListDTO bidListDTO) {
        BidList bidList = mapper.bidListDTOToBidList(bidListDTO);

        bidList = bidListRepository.save(bidList);
        return mapper.bidListToDTO(bidList);
    }

    public BidListDTO getBidListById(Integer id){
        Optional<BidList> bidList = bidListRepository.findById(id);

        if(bidList.isPresent()) {
            BidListDTO bidListDTO = mapper.bidListToDTO(bidList.get());
            return bidListDTO;
        } else {
            throw new IllegalArgumentException("Invalid bidlist Id:" + id);
        }
    }

    public void deleteBidList(BidListDTO bidListDTO){
        BidList bidList = mapper.bidListDTOToBidList(bidListDTO);
        bidListRepository.delete(bidList);
    }
}
