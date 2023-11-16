package nl.fontysS3_project.business.impl;

import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    @Override
    public User createUser(User request){

        UserEntity savedUser = saveNewUser(request);

        return UserConverter.convert(savedUser);
    }
    private UserEntity saveNewUser(User user) {

        UserEntity newUser = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .bio(user.getBio())
                .location(user.getLocation())
                .password(user.getPassword())
                .build();
        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void deleteUser(int userId){
        this.userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User getUser(long userId) {
        Optional<UserEntity> optionalentity = userRepository.findById(userId);
        return optionalentity.map(UserConverter::convert).orElse(null);
    }
}
