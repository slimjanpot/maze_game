package nl.fontysS3_project.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @Column(name = "time")
    private float time;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity user;
}
