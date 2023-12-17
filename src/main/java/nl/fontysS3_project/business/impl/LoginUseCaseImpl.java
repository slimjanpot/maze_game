package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.LoginUseCase;
import nl.fontysS3_project.business.exception.InvalidCredentialsException;
import nl.fontysS3_project.configuration.security.token.AccessTokenEncoder;
import nl.fontysS3_project.configuration.security.token.impl.AccessTokenImpl;
import nl.fontysS3_project.controllers.Request_Response.LoginRequest;
import nl.fontysS3_project.controllers.Request_Response.LoginResponse;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long studentId = (long) user.getId();
        String permission = mapPermissionToString(user.getPermission());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), studentId, permission));
    }
    private String mapPermissionToString(int permission) {
        return (permission == 0) ? "NORMAL" : "ADMIN";
    }

}
