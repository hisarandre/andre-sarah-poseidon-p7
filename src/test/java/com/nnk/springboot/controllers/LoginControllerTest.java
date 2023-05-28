package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testLogin() {
        // Act
        ModelAndView mav = loginController.login();

        // Assert
        assertEquals("login", mav.getViewName());
    }

    @Test
    public void testGetAllUserArticles() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ModelAndView mav = loginController.getAllUserArticles();

        // Assert
        assertEquals("user/list", mav.getViewName());
        assertEquals(Collections.emptyList(), mav.getModel().get("users"));
    }

    @Test
    public void testError() {
        // Act
        ModelAndView mav = loginController.error();

        // Assert
        assertEquals("403", mav.getViewName());
        assertEquals("You are not authorized for the requested data.", mav.getModel().get("errorMsg"));
    }
}
