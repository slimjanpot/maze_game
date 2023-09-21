package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.CreateUserRequest;
import nl.fontysS3_project.domain.CreateUserResponse;
public interface CreateUser {
    CreateUserResponse createUser(CreateUserRequest request);
}
