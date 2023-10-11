package nl.fontysS3_project.controllers.Request_Response;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String name;
    @NonNull
    private String username;
    @NonNull
    private String password;
}
