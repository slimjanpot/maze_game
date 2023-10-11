package nl.fontysS3_project.domain;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String username;
    private String hashedPassword;

}
