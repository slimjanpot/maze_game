package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;

import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.domain.*;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers() {
        List<UserEntity> results;
        results = userRepository.findAll();

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> user = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setStudents(user);

        return response;
    }

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
    @Override
    public void deleteUser(int userId){
        this.userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId).map(UserConverter::convert);
    }
}
