package nl.fontysS3_project.controllers;

import nl.fontysS3_project.controllers.Request_Response.CreateUserRequest;
import nl.fontysS3_project.controllers.Request_Response.CreateUserResponse;
import nl.fontysS3_project.domain.*;

final class ConverterUser {
    private ConverterUser(){}

    public static User converttouser(CreateUserRequest request){
        return User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .hashedPassword(request.getPassword())
                .build();
    }
    public static CreateUserResponse converttoresponse(User us){
        return CreateUserResponse.builder()
                .userId(us.getId())
                .build();
    }
}
