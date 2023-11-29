package nl.fontysS3_project.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "age")
    private int age;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "location")
    private String location;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "bio")
    private String bio;

    @Column(name = "password")
    @Length(max = 50)
    private String password;

    @Column(name = "permission")
    private int permission;
}
