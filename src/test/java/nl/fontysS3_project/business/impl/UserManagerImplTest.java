package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {
    @Mock
    private UserRepository userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserManagerImpl userManager;



    private final UserEntity userentity = UserEntity.builder()
                .id(1)
                .password("pass")
                .location("Somewhere")
                .bio("I love this")
                .age(23)
                .email("email")
                .username("Steve")
                .name("Steven")
                .build();
    private final UserEntity userentity2 = UserEntity.builder()
            .id(1)
            .password("pass")
            .location("Somewhere")
            .bio("I love this")
            .age(23)
            .email("email")
            .username("John")
            .name("Jonny")
            .build();
    private final User user1 = User.builder()
            .id(1)
            .password("pass")
            .location("Somewhere")
            .bio("I love this")
            .age(23)
            .email("email")
            .username("Steve")
            .name("Steven")
            .build();

    private final User user2 = User.builder()
            .id(1)
            .password("pass")
            .location("Somewhere")
            .bio("I love this")
            .age(23)
            .email("email")
            .username("John")
            .name("Jonny")
            .build();
    @Test
    void getUsers() {

        when(userRepo.findAll()).thenReturn(List.of(userentity,userentity2));

        List<User> result = userManager.getUsers();



        List<User> initial = new ArrayList<>();
        initial.add(user1);
        initial.add(user2);

        assertEquals(result, initial);
        verify(userRepo).findAll();
    }

    @Test
    void createUser() {
        when(userRepo.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity savedUserEntity = invocation.getArgument(0);
            // Assert the properties of the saved user entity
            assertEquals("Steven", savedUserEntity.getName());
            assertEquals("Steve", savedUserEntity.getUsername());
            assertEquals("email", savedUserEntity.getEmail());
            assertEquals(23, savedUserEntity.getAge());
            assertEquals("Somewhere", savedUserEntity.getLocation());
            assertEquals("I love this", savedUserEntity.getBio());
            // Assert the encoded password
            assertTrue(passwordEncoder.matches("pass", savedUserEntity.getPassword()));
            return savedUserEntity;
        });

        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");

        // Mocking the matches operation for password verification
        when(passwordEncoder.matches("pass", "encodedPass")).thenReturn(true);


        // Calling the method under test
        User savedUser = userManager.createUser(user1);

        // Verifying that the save method was called with the correct arguments
        verify(userRepo).save(any(UserEntity.class));

        verify(passwordEncoder).encode("pass");

        // Verifying that the matches method was called with the correct arguments
        verify(passwordEncoder).matches("pass", "encodedPass");

        assertNotNull(savedUser);
        assertEquals("Steven", savedUser.getName());
        assertEquals("Steve", savedUser.getUsername());
        assertEquals("email", savedUser.getEmail());
        assertEquals(23, savedUser.getAge());
        assertEquals("Somewhere", savedUser.getLocation());
        assertEquals("I love this", savedUser.getBio());
        // Adjust the assertion based on your actual password verification logic
        assertEquals("encodedPass", savedUser.getPassword());
    }

    @Test
    void deleteUser() {
        int userId = 1;
        doNothing().when(userRepo).deleteById(userId);

        userManager.deleteUser(userId);

        verify(userRepo).deleteById(userId);
    }

    @Test
    void getUser() {
        long userId = 1;
        when(userRepo.findById(userId)).thenReturn(Optional.of(userentity));

        User result = userManager.getUser((int) userId);

        verify(userRepo).findById(userId);

        assertNotNull(result);

        assertEquals("Steven", result.getName());
        assertEquals("Steve", result.getUsername());
        assertEquals("email", result.getEmail());
        assertEquals(23, result.getAge());
        assertEquals("Somewhere", result.getLocation());
        assertEquals("I love this", result.getBio());
        assertEquals("pass", result.getPassword());
    }
}