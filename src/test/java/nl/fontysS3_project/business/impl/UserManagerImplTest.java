package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {
    @Mock
    private UserRepository userRepo;
    @InjectMocks
    private UserManagerImpl userManager;
    @Test
    void getUsers() {
        UserEntity userentity = UserEntity.builder()
                .id(1)
                .password("pass")
                .location("Somewhere")
                .bio("I love this")
                .age(23)
                .email("email")
                .username("Steve")
                .name("Steven")
                .build();

        UserEntity userentity2 = UserEntity.builder()
                .id(1)
                .password("pass")
                .location("Somewhere")
                .bio("I love this")
                .age(23)
                .email("email")
                .username("John")
                .name("Jonny")
                .build();
        when(userRepo.findAll()).thenReturn(List.of(userentity,userentity2));

        List<User> result = userManager.getUsers();

        User user1 = User.builder()
                .id(1)
                .password("pass")
                .location("Somewhere")
                .bio("I love this")
                .age(23)
                .email("email")
                .username("Steve")
                .name("Steven")
                .build();

        User user2 = User.builder()
                .id(1)
                .password("pass")
                .location("Somewhere")
                .bio("I love this")
                .age(23)
                .email("email")
                .username("John")
                .name("Jonny")
                .build();

        List<User> initial = new ArrayList<>();
        initial.add(user1);
        initial.add(user2);

        assertEquals(result, initial);
        verify(userRepo).findAll();
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }
}