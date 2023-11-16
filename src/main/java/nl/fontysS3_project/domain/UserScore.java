package nl.fontysS3_project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScore {
    private int id;
    private int time;
    private int attempts;
    private int result;
    private User user;
}
