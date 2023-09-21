package nl.fontysS3_project.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;

public class FakeUserRepositoryImpl implements UserRepository {
    private static int NEXT_ID = 1;
    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() == 0) {
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
        }
        return user;
    }
}
