package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.UserScore;

public interface UserScoreManager {
    UserScore getScore(int userid);
}
