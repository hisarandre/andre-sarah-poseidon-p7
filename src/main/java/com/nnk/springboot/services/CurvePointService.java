package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePointDTO> getCurvePoints() {
        List<CurvePoint> curvePoints = curvePointRepository.findAll();
        List<CurvePointDTO> curvePointDTO = mapper.curvePointsToDTO(curvePoints);
        return curvePointDTO;
    }

    public CurvePointDTO saveCurvePoint(CurvePointDTO curvePointDTO) {
        CurvePoint curvePoint = mapper.curvePointDTOToCurvePoint(curvePointDTO);

        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));

        curvePoint = curvePointRepository.save(curvePoint);
        return mapper.curvePointToDTO(curvePoint);
    }

    public CurvePointDTO getCurvePointById(Integer id){
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);

        if(curvePoint.isPresent()) {
            CurvePointDTO curvePointDTO = mapper.curvePointToDTO(curvePoint.get());
            return curvePointDTO;
        } else {
            throw new IllegalArgumentException("Invalid curvepoint Id:" + id);
        }
    }

    public void deleteCurvePoint(CurvePointDTO curvePointDTO){
        CurvePoint curvePoint = mapper.curvePointDTOToCurvePoint(curvePointDTO);
        curvePointRepository.delete(curvePoint);
    }
}
