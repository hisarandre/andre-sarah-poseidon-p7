package com.nnk.springboot.services;

import com.nnk.springboot.domain.MapstructMapper;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RuleNameServiceTest {
    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    MapstructMapper mapper= Mappers.getMapper(MapstructMapper.class);

    @InjectMocks
    private RuleNameService ruleNameService;

    @Test
    void testGetRuleNames() {
        // Arrange
        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(new RuleName());
        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);

        // Act
        List<RuleName> result = ruleNameService.getRuleNames();

        // Assert
        assertEquals(ruleNameList.size(), result.size());
    }

    @Test
    void testSaveRuleName() {
        // Arrange
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        // Act
        RuleName result = ruleNameService.saveRuleName(ruleName);

        // Assert
        assertNotNull(result);
        assertEquals(ruleName.getId(), result.getId());
    }

    @Test
    void testGetRuleNameByIdWithValidId() {
        // Arrange
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        // Act
        RuleName result = ruleNameService.getRuleNameById(1);

        // Assert
        assertNotNull(result);
        assertEquals(ruleName, result);
    }

    @Test
    void testGetRuleNameByIdWithInvalidId() {
        // Arrange
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ruleNameService.getRuleNameById(1));
    }

    @Test
    void testDeleteRuleName() {
        // Arrange
        RuleName ruleName = new RuleName();

        // Act
        ruleNameService.deleteRuleName(ruleName);

        // Assert
        verify(ruleNameRepository, times(1)).delete(eq(ruleName));
    }
}
