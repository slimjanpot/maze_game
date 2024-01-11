package nl.fontysS3_project.controllers.Request_Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontysS3_project.domain.MapScore;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllScoresResponse {
    private List<MapScore> scores;
}
