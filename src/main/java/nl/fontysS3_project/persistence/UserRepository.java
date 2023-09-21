package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.UserEntity;
public interface UserRepository {
    UserEntity save(UserEntity user);
    boolean existsByUsername(String username);
}
