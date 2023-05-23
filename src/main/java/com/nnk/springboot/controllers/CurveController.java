package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
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

@Controller
public class CurveController {
    @Autowired
    private CurvePointRepository curvePointRepository;

    @Autowired
    private CurvePointService curvePointService;

    private static Logger logger = LoggerFactory.getLogger(BidListController.class);

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        logger.info("Curve point list requested");

        model.addAttribute("curvePoints", curvePointService.getCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        logger.info("Curvepoint form requested");


        model.addAttribute("curvePointDTO", new CurvePointDTO());
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDTO curvePointDTO, BindingResult result, Model model) {
        logger.info("Curvepoint form sent");

        if (!result.hasErrors()) {
            logger.info("Curvepoint added");

            curvePointService.saveCurvePoint(curvePointDTO);
            model.addAttribute("curvePoints", curvePointService.getCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Update form for curvepoint id : " + id );

        model.addAttribute("curvePointDTO", curvePointService.getCurvePointById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDTO curvePointDTO,
                             BindingResult result, Model model) {
        logger.info("Update form for curvepoint id : " + id + " sent" );

        if (result.hasErrors())  return "/curvePoint/update";

        logger.info("Curvepoint updated" );

        curvePointDTO.setId(id);
        curvePointService.saveCurvePoint(curvePointDTO);
        model.addAttribute("curvePoints", curvePointService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("Curvepoint deleted" );

        CurvePointDTO curvePointDTO = curvePointService.getCurvePointById(id);
        curvePointService.deleteCurvePoint(curvePointDTO);

        return "redirect:/curvePoint/list";
    }
}
