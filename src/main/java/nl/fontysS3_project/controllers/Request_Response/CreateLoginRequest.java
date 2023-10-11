package nl.fontysS3_project.controllers.Request_Response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoginRequest {
    @NonNull
    private String Username;
    @NonNull
    private String Password;
}
