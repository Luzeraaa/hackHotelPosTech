package com.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.user.domain.Country;
import com.user.domain.SearchParams;
import com.user.domain.User;
import com.user.dto.UpdateUserDTO;
import com.user.dto.UserDTO;
import com.user.service.UserService;
import com.user.util.Pagination;
import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO(
                "John",
                "Doe",
                "john.doe@example.com",
                "password123",
                123,
                "4567890",
                "1990-01-01",
                "123 Main St",
                Country.BRAZIL,
                "123456789",
                987654321);
        User user = new User();
        when(userService.createUser(userDTO)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.createUser(userDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testFindUserByParam() {
        SearchParams searchParams = new SearchParams();
        searchParams.setName("John");
        searchParams.setSurname("Doe");
        searchParams.setEmail("john.doe@example.com");
        searchParams.setDdd(123);
        searchParams.setPhone(4567890);
        searchParams.setBirthdate("1990-01-01");
        searchParams.setAddress("123 Main St");
        searchParams.setCountry(Country.BRAZIL);
        searchParams.setCpf("123456789");
        searchParams.setPassport(987654321);

        int limit = 10;
        int offset = 0;
        List<User> expectedItemsList = List.of(new User(), new User());
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> page = new PageImpl<>(expectedItemsList, pageable, expectedItemsList.size());
        Pagination<User> users = new Pagination<>(page);
        when(userService.findUserByParam(searchParams)).thenReturn(users);

        ResponseEntity<Pagination<User>> responseEntity = userController.findUserByParam(searchParams);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = userController.deleteUser(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateUserById() {
        Long id = 1L;
        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                "John",
                "Doe",
                "john.doe@example.com",
                "password123",
                123,
                4567890,
                "1990-01-01",
                "123 Main St",
                Country.BRAZIL,
                123456789,
                987654321
        );
        User user = new User(
                "John",
                "Doe",
                "john.doe@example.com",
                "password123",
                123,
                "4567890",
                "1990-01-01",
                "123 Main St",
                Country.BRAZIL,
                "123456789",
                987654321
        );
        when(userService.updateUserById(id, updateUserDTO)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.updateUserById(id, updateUserDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }
}