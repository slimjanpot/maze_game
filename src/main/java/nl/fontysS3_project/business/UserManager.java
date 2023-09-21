package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.CreateUserRequest;
import nl.fontysS3_project.domain.CreateUserResponse;
import nl.fontysS3_project.domain.User;

import java.util.Optional;

public interface UserManager {
    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUser(int userId);

    Optional<User> getUser(int userId);
}
