package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> getRuleNames() {
        return ruleNameRepository.findAll();
    }

    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName getRuleNameById(Integer id){
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);

        if(ruleName.isPresent()) {
            return ruleName.get();
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
    }

    public void deleteRuleName(RuleName ruleName){
        ruleNameRepository.delete(ruleName);
    }
}
