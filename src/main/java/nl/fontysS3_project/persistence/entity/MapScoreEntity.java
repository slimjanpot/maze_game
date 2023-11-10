package nl.fontysS3_project.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "map_score")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapScoreEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "map")
    private int map;

    @NotNull
    @Column(name = "attempts")
    private int attempts;

    @NotNull
    @Column(name = "time")
    private int time;

    @NotNull
    @Column(name = "result")
    private int result;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
