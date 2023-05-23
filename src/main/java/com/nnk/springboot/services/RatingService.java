package com.nnk.springboot.services;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    private static Logger logger = LoggerFactory.getLogger(BidListController.class);

    public List<Rating> getRatings() {
        logger.info("Get ratings");
        return ratingRepository.findAll();
    }

    public Rating saveRating(Rating rating) {
        logger.info("Save rating");
        return ratingRepository.save(rating);
    }

    public Rating getRatingById(Integer id){
        logger.info("Get rating by id");

        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            return rating.get();
        } else {
            throw new IllegalArgumentException("Invalid rating Id:" + id);
        }
    }

    public void deleteRating(Rating rating){
        logger.info("Delete rating");

        ratingRepository.delete(rating);
    }
}
