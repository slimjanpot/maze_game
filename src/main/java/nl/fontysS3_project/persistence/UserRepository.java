package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity user);

    void deleteById(int userId);

    boolean existsByUsername(String username);

    Optional<UserEntity> findById(int userId);
}
