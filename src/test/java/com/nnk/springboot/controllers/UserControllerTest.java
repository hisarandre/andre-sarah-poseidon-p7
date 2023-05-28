package com.nnk.springboot.controllers;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;
    @Test
    public void testHome() {
        //Arrange
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        //Act
        String viewName = userController.home(model);

        //Assert
        assertEquals("user/list", viewName);
        verify(model).addAttribute("users", users);
    }

    @Test
    public void testAddUser() {
        //Arrange
        User bid = new User();

        //Act
        String viewName = userController.addUser(bid);

        //Assert
        assertEquals("user/add", viewName);
    }

    @Test
    public void testValidateWithValidUser() {
        //Arrange
        User user = new User();
        user.setPassword("Password1!");
        user.setRole("USER");
        user.setFullname("john doe");
        user.setId(1);
        user.setUsername("john");
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String viewName = userController.validate(user, bindingResult, model);

        //Assert
        assertEquals("redirect:/user/list", viewName);
        verify(userRepository).save(user);
        verify(model).addAttribute("users", userRepository.findAll());
    }

    @Test
    public void testValidateWithInvalidUser() {
        //Arrange
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);

        //Act
        String viewName = userController.validate(user, bindingResult, model);

        //Assert
        assertEquals("user/add", viewName);
        verifyNoInteractions(userRepository);
        verify(model, never()).addAttribute(eq("users"), any());
    }

    @Test
    public void testShowUpdateForm() {
        //Arrange
        Integer id = 1;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //Act
        String viewName = userController.showUpdateForm(id, model);

        //Assert
        assertEquals("user/update", viewName);
        verify(model).addAttribute("user", user);
    }

    @Test
    public void testUpdateUserWithValidUser() {
        //Arrange
        Integer id = 1;
        User user = new User();
        user.setPassword("Password1!");
        user.setRole("USER");
        user.setFullname("john doe");
        user.setId(1);
        user.setUsername("john");
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String viewName = userController.updateUser(id, user, bindingResult, model);

        //Assert
        assertEquals("redirect:/user/list", viewName);
        verify(userRepository).save(user);
        verify(model).addAttribute("users", userRepository.findAll());
    }

    @Test
    public void testUpdateUserWithInvalidUser() {
        //Arrange
        Integer id = 1;
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);

        //Act
        String viewName = userController.updateUser(id, user, bindingResult, model);

        //Assert
        assertEquals("user/update", viewName);
        verifyNoInteractions(userRepository);
        verify(model, never()).addAttribute(eq("users"), any());
    }

    @Test
    public void testDeleteUser() {
        //Arrange
        Integer id = 1;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //Act
        String viewName = userController.deleteUser(id, model);

        //Assert
        assertEquals("redirect:/user/list", viewName);
        verify(userRepository).delete(user);
    }
}
