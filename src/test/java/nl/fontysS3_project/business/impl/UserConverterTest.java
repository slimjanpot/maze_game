package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void convert() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .name("John Doe")
                .username("john_doe")
                .email("john.doe@example.com")
                .age(25)
                .bio("I love coding")
                .location("City")
                .password("securePassword")
                .build();

        User convertedUser = UserConverter.convert(userEntity);

        assertNotNull(convertedUser);
        assertEquals(1L, convertedUser.getId());
        assertEquals("John Doe", convertedUser.getName());
        assertEquals("john_doe", convertedUser.getUsername());
        assertEquals("john.doe@example.com", convertedUser.getEmail());
        assertEquals(25, convertedUser.getAge());
        assertEquals("I love coding", convertedUser.getBio());
        assertEquals("City", convertedUser.getLocation());
        assertEquals("securePassword", convertedUser.getPassword());
    }

    @Test
    void toUserEntitiy() {
        User user = User.builder()
                .id(1)
                .name("John Doe")
                .username("john_doe")
                .email("john.doe@example.com")
                .age(25)
                .bio("I love coding")
                .location("City")
                .password("securePassword")
                .build();

        UserEntity convertedUserEntity = UserConverter.toUserEntitiy(user);

        assertNotNull(convertedUserEntity);
        assertEquals(1L, convertedUserEntity.getId());
        assertEquals("John Doe", convertedUserEntity.getName());
        assertEquals("john_doe", convertedUserEntity.getUsername());
        assertEquals("john.doe@example.com", convertedUserEntity.getEmail());
        assertEquals(25, convertedUserEntity.getAge());
        assertEquals("I love coding", convertedUserEntity.getBio());
        assertEquals("City", convertedUserEntity.getLocation());
        assertEquals("securePassword", convertedUserEntity.getPassword());
    }

}