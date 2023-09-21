package nl.fontysS3_project.persistence.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private int id;
    private String name;
    private String username;
    private String hashedPassword;
}