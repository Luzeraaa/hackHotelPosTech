package com.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.user.domain.User;
import com.user.dto.UpdateUserDTO;
import com.user.dto.UserDTO;
import com.user.domain.Country;
import com.user.exception.ResourceAlreadyExistsException;
import com.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testCreateUser_Success() {
        UserDTO userDTO = new UserDTO(
                "John",
                "Doe",
                "john@example.com",
                "Password123@",
                123,
                "987654321",
                "1990/01/01",
                "123 Main St",
                Country.BRAZIL,
                "123.456.789-10",
                null
        );

        when(userRepository.findByEmailOrCpf("john@example.com", "123.456.789-10")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User createdUser = userService.createUser(userDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals("John", createdUser.getName());
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        // Mocking userDTO
        UserDTO userDTO = new UserDTO(
                "John",
                "Doe",
                "john@example.com",
                "Password123@",
                123,
                "987654321",
                "1990/01/01",
                "123 Main St",
                Country.BRAZIL,
                "123.456.789-10",
                null
        );

        when(userRepository.findByEmailOrCpf("john@example.com", "123.456.789-10")).thenReturn(Optional.of(new User()));

        assertThrows(ResourceAlreadyExistsException.class, () -> userService.createUser(userDTO));
    }

    @Test
    public void testDeleteUser_Success() {
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userService.deleteUser(1L));

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateUserById_Success() {
        String newName = "John";
        String newSurname = "Doe";
        String newEmail = "john.doe@example.com";
        String newPassword = "newPassword123";
        Integer newDdd = 123;
        Integer newPhone = 456789;
        String newBirthdate = "1990-01-01";
        String newAddress = "123 Main St";
        Country newCountry = Country.BRAZIL;
        Integer newCpf = 123456789;
        Integer newPassport = 987654321;

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                newName, newSurname, newEmail, newPassword, newDdd, newPhone,
                newBirthdate, newAddress, newCountry, newCpf, newPassport);

        assertNotNull(updateUserDTO);
        assertEquals(newName, updateUserDTO.name());
        assertEquals(newSurname, updateUserDTO.surname());
        assertEquals(newEmail, updateUserDTO.email());
        assertEquals(newPassword, updateUserDTO.password());
        assertEquals(newDdd, updateUserDTO.ddd());
        assertEquals(newPhone, updateUserDTO.phone());
        assertEquals(newBirthdate, updateUserDTO.birthdate());
        assertEquals(newAddress, updateUserDTO.address());
        assertEquals(newCountry, updateUserDTO.country());
        assertEquals(newCpf, updateUserDTO.cpf());
        assertEquals(newPassport, updateUserDTO.passport());
    }
}