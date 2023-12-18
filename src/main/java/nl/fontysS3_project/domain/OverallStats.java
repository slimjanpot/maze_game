package nl.fontysS3_project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverallStats {
    private float totaltimeplayed;
    private int totalattempts;
    private int wins;
    private int losses;
}
