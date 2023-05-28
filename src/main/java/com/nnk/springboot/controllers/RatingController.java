package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    private static Logger logger = LoggerFactory.getLogger(RatingController.class);

    @RequestMapping("/rating/list")
    public String home(Model model) {
        logger.info("Rating list requested");

        model.addAttribute("ratings", ratingService.getRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        logger.info("Rating form requested");

        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        logger.info("Rating form sent");

        if (!result.hasErrors()) {
            logger.info("Rating added");

            ratingService.saveRating(rating);
            model.addAttribute("ratings", ratingService.getRatings());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Update form for rating id : " + id );

        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        logger.info("Update form for rating id : " + id + " sent" );

        if (result.hasErrors())  return "/rating/update";

        logger.info("Rating updated");
        rating.setId(id);
        ratingService.saveRating(rating);
        model.addAttribute("curvePoints", ratingService.getRatings());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.info("Rating deleted" );

        Rating rating = ratingService.getRatingById(id);
        ratingService.deleteRating(rating);
        return "redirect:/rating/list";
    }
}
