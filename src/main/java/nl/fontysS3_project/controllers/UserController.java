package nl.fontysS3_project.controllers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {
    public class CreateUserRequest {
        private String username;
        private String clearTextPassword;
    }
    public class CreateUserResponse {
        private int id;
        private String username;
    }


}
