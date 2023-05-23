package com.nnk.springboot.domain;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.TradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MapstructMapper {

    @Mapping(target="id", source="curvePoint.id")
    CurvePointDTO curvePointToDTO(CurvePoint curvePoint);

    List<CurvePointDTO> curvePointsToDTO(List<CurvePoint> curvePoints);

    @Mapping(target="id", source="curvePointDTO.id")
    CurvePoint curvePointDTOToCurvePoint(CurvePointDTO curvePointDTO);

    @Mapping(target="id", source="bidlist.bidListId")
    BidListDTO bidListToDTO(BidList bidlist);

    List<BidListDTO> bidlistsToDTO(List<BidList> bidlists);

    @Mapping(target="bidListId", source="bidListDTO.id")
    BidList bidListDTOToBidList(BidListDTO bidListDTO);

    @Mapping(target="id", source="trade.tradeId")
    TradeDTO tradeToDTO(Trade trade);

    List<TradeDTO> tradesToDTO(List<Trade> trade);

    @Mapping(target="tradeId", source="tradeDTO.id")
    Trade tradeDTOToTrade(TradeDTO tradeDTO);

}
