package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.business.exception.InvalidCredentialsException;
import nl.fontysS3_project.configuration.security.token.AccessTokenEncoder;
import nl.fontysS3_project.configuration.security.token.impl.AccessTokenImpl;
import nl.fontysS3_project.controllers.Request_Response.LoginRequest;
import nl.fontysS3_project.controllers.Request_Response.LoginResponse;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginUseCaseImplTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AccessTokenEncoder accessTokenEncoder;
    private LoginUseCaseImpl loginUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        accessTokenEncoder = mock(AccessTokenEncoder.class);

        loginUseCase = new LoginUseCaseImpl(userRepository, passwordEncoder, accessTokenEncoder);
    }

    @Test
    void testSuccessfulLogin() {
        String username = "testUser";
        String password = "testPassword";

        LoginRequest loginRequest = new LoginRequest(username, password);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername(username);
        userEntity.setPassword("encodedPassword");
        userEntity.setPermission(0);

        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, userEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(new AccessTokenImpl(username, (long)userEntity.getId(), "NORMAL"))).thenReturn("Something");

        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getAccessToken());
    }

    @Test
    void testInvalidCredentials() {
        String username = "testUser";
        String password = "testPassword";

        LoginRequest loginRequest = new LoginRequest(username, password);

        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("encodedPassword");
        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, userEntity.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
    }
}