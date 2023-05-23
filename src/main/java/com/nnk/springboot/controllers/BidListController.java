package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class BidListController {

    @Autowired
    BidListService bidListService;

    private static Logger logger = LoggerFactory.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        logger.info("BidList list requested");

        model.addAttribute("bidLists", bidListService.getBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        logger.info("BidList form requested");

        model.addAttribute("bidListDTO", new BidListDTO());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDTO bidListDTO, BindingResult result, Model model) {
        logger.info("BidList form sent");

        if (!result.hasErrors()) {
            logger.info("BidList added");

            bidListService.saveBidList(bidListDTO);
            model.addAttribute("bidLists", bidListService.getBidLists());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Update form for bidlist id : " + id );

        model.addAttribute("bidListDTO", bidListService.getBidListById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDTO bidListDTO,
                             BindingResult result, Model model) {
        logger.info("Update form for bidlist id : " + id + " sent" );

        if (result.hasErrors())  return "bidList/update";

        logger.info("Bidlist updated" );
        bidListDTO.setId(id);
        bidListService.saveBidList(bidListDTO);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("Bidlist deleted" );

        BidListDTO bidListDTO = bidListService.getBidListById(id);
        bidListService.deleteBidList(bidListDTO);
        return "redirect:/bidList/list";
    }
}
