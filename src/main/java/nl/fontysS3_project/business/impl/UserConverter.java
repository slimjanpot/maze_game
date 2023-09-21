package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.entity.UserEntity;

final class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity student) {
        return User.builder()
                .id(student.getId())
                .name(student.getName())
                .username(student.getUsername())
                .hashedPassword(student.getHashedPassword())
                .build();
    }
}