package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.CreateUser;
import nl.fontysS3_project.domain.*;
import nl.fontysS3_project.persistence.entity.UserEntity;
import nl.fontysS3_project.persistence.UserRepository;

@AllArgsConstructor
public class CreateUserImpl implements CreateUser {

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request){

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {

        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .hashedPassword(request.getPassword())
                .build();
        return userRepository.save(newUser);
    }
}
