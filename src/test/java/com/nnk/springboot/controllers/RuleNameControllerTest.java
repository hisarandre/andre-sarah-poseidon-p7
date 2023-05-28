package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RuleNameControllerTest {

    @InjectMocks
    private RuleNameController ruleNameController;

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testHome() {
        // Arrange
        List<RuleName> ruleNames = new ArrayList<>();
        when(ruleNameService.getRuleNames()).thenReturn(ruleNames);

        // Act
        String viewName = ruleNameController.home(model);

        // Assert
        assertEquals("ruleName/list", viewName);
        verify(model).addAttribute("ruleNames", ruleNames);
    }

    @Test
    public void testAddRuleForm() {
        // Act
        String viewName = ruleNameController.addRuleForm(model);

        // Assert
        assertEquals("ruleName/add", viewName);
        verify(model).addAttribute("ruleName", new RuleName());
    }

    @Test
    public void testValidateWithValidRuleName() {
        // Arrange
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ruleNameService.getRuleNames()).thenReturn(new ArrayList<>());

        // Act
        String viewName = ruleNameController.validate(ruleName, bindingResult, model);

        // Assert
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService).saveRuleName(ruleName);
        verify(model).addAttribute("ruleNames", new ArrayList<>());
    }

    @Test
    public void testValidateWithInvalidRuleName() {
        // Arrange
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String viewName = ruleNameController.validate(ruleName, bindingResult, model);

        // Assert
        assertEquals("ruleName/add", viewName);
        verify(model, never()).addAttribute(eq("ruleNames"), any());
    }

    @Test
    public void testShowUpdateForm() {
        // Arrange
        Integer id = 1;
        RuleName ruleName = new RuleName();
        when(ruleNameService.getRuleNameById(id)).thenReturn(ruleName);

        // Act
        String viewName = ruleNameController.showUpdateForm(id, model);

        // Assert
        assertEquals("ruleName/update", viewName);
        verify(model).addAttribute("ruleName", ruleName);
    }

    @Test
    public void testUpdateRuleNameWithValidRuleName() {
        // Arrange
        Integer id = 1;
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ruleNameService.getRuleNames()).thenReturn(new ArrayList<>());

        // Act
        String viewName = ruleNameController.updateRuleName(id, ruleName, bindingResult, model);

        // Assert
        assertEquals("redirect:/ruleName/list", viewName);
        assertEquals(id, ruleName.getId());
        verify(ruleNameService).saveRuleName(ruleName);
        verify(model).addAttribute("ruleNames", new ArrayList<>());
    }

    @Test
    public void testUpdateRuleNameWithInvalidRuleName() {
        // Arrange
        Integer id = 1;
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Assert
        String viewName = ruleNameController.updateRuleName(id, ruleName, bindingResult, model);

        // Act
        assertEquals("/ruleName/update", viewName);
        verify(model, never()).addAttribute(eq("ruleNames"), any());
    }

    @Test
    public void testDeleteRuleName() {
        // Arrange
        Integer id = 1;
        RuleName ruleName = new RuleName();
        when(ruleNameService.getRuleNameById(id)).thenReturn(ruleName);

        // Act
        String viewName = ruleNameController.deleteRuleName(id, model);

        // Assert
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService).deleteRuleName(ruleName);
    }
}
