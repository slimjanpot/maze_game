package nl.fontysS3_project.controllers.Request_Response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private int userId;
}
