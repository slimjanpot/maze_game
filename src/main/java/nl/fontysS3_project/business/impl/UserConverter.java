package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.entity.UserEntity;

final class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .bio(user.getBio())
                .location(user.getLocation())
                .password(user.getPassword())
                .build();
    }
}
