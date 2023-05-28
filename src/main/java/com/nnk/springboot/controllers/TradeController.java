package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    @Autowired
    TradeService tradeService;

    private static Logger logger = LoggerFactory.getLogger(TradeController.class);

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        logger.info("Trade list requested");

        model.addAttribute("trades", tradeService.getTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        logger.info("Trade form requested");

        model.addAttribute("tradeDTO", new TradeDTO());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDTO tradeDTO, BindingResult result, Model model) {
        logger.info("Trade form sent");

        if (!result.hasErrors()) {
            logger.info("Trade added");

            tradeService.saveTrade(tradeDTO);
            model.addAttribute("trades", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Update form for Trade id : " + id );

        model.addAttribute("tradeDTO", tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDTO tradeDTO,
                             BindingResult result, Model model) {
        logger.info("Update form for trade id : " + id + " sent" );

        if (result.hasErrors())  return "/trade/update";

        logger.info("Trade updated" );
        tradeDTO.setId(id);
        tradeService.saveTrade(tradeDTO);
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.info("Trade deleted" );

        TradeDTO tradeDTO = tradeService.getTradeById(id);
        tradeService.deleteTrade(tradeDTO);
        return "redirect:/trade/list";
    }
}
