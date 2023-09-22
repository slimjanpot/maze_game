package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.*;

import java.util.Optional;

public interface UserManager {
    GetAllUsersResponse getUsers();

    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUser(int userId);

    Optional<User> getUser(int userId);
}
