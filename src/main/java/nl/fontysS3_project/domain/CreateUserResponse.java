package nl.fontysS3_project.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private int userId;
}
