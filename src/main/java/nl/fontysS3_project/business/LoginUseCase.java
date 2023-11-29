package nl.fontysS3_project.business;

import nl.fontysS3_project.controllers.Request_Response.LoginRequest;
import nl.fontysS3_project.controllers.Request_Response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);

}
