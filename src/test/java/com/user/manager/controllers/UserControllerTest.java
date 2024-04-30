package com.user.manager.controllers;

import com.user.manager.entities.User;
import com.user.manager.services.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        ObjectId userId = new ObjectId();
        User user = new User();
        when(userService.updateUser(userId, user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(userId, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUserFields() {
        ObjectId userId = new ObjectId();
        User user = new User();
        when(userService.updateUserFields(userId, user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUserFields(userId, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testDeleteUser() {
        ObjectId userId = new ObjectId();

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testSearchUsersByDateOfBirth() {
        LocalDate fromDate = LocalDate.now().minusYears(20);
        LocalDate toDate = LocalDate.now().minusYears(18);

        List<User> users = Arrays.asList(new User(), new User());
        when(userService.searchUsersByDateOfBirth(fromDate, toDate)).thenReturn(users);

        ResponseEntity<List<User>> response = userController.searchUsersByDateOfBirth(fromDate, toDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }
}