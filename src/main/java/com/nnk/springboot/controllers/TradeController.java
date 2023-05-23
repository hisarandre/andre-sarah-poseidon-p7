package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
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

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.getTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        model.addAttribute("tradeDTO", new TradeDTO());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDTO tradeDTO, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.saveTrade(tradeDTO);
            model.addAttribute("trades", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tradeDTO", tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDTO tradeDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors())  return "/trade/update";

        tradeDTO.setId(id);
        tradeService.saveTrade(tradeDTO);
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        TradeDTO tradeDTO = tradeService.getTradeById(id);
        tradeService.deleteTrade(tradeDTO);
        return "redirect:/trade/list";
    }
}
