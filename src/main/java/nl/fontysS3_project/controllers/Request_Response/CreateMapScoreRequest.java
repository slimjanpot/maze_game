package nl.fontysS3_project.controllers.Request_Response;

import lombok.*;
import nl.fontysS3_project.domain.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMapScoreRequest {
    private int map;
    private float time;
    private int userid;
}
