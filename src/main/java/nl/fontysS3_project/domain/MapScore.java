package nl.fontysS3_project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapScore {
    private int id;
    private int map;
    private float time;
    private User user;
}
