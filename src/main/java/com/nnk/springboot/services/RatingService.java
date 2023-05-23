package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating getRatingById(Integer id){
        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            return rating.get();
        } else {
            throw new IllegalArgumentException("Invalid rating Id:" + id);
        }
    }

    public void deleteRating(Rating rating){
        ratingRepository.delete(rating);
    }
}
