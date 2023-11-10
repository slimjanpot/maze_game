package nl.fontysS3_project.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_score")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserScoreEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "time_played")
    private int time;

    @NotNull
    @Column(name = "attempts")
    private int attempts;

    @NotNull
    @Column(name = "result")
    private int result;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
