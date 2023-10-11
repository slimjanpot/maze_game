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
    public List<User> getUsers() {
        List<UserEntity> results;
        results = userRepository.findAll();

        List<User> user = results
                .stream()
                .map(UserConverter::convert)
                .toList();

        return user;
    }

    @Override
    public User createUser(User request){

        UserEntity savedUser = saveNewUser(request);

        return UserConverter.convert(savedUser);
    }
    private UserEntity saveNewUser(User request) {

        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .hashedPassword(request.getHashedPassword())
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
