package com.nnk.springboot.services;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;

    private static Logger logger = LoggerFactory.getLogger(BidListController.class);

    public List<RuleName> getRuleNames() {
        logger.info("Get rule names");

        return ruleNameRepository.findAll();
    }

    public RuleName saveRuleName(RuleName ruleName) {
        logger.info("Save rule name");

        return ruleNameRepository.save(ruleName);
    }

    public RuleName getRuleNameById(Integer id){
        logger.info("Get rule name by id");

        Optional<RuleName> ruleName = ruleNameRepository.findById(id);

        if(ruleName.isPresent()) {
            return ruleName.get();
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
    }

    public void deleteRuleName(RuleName ruleName){
        logger.info("Delete rule name");

        ruleNameRepository.delete(ruleName);
    }
}
