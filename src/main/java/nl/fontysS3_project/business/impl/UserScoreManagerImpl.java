package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.domain.UserScore;
import nl.fontysS3_project.persistence.UserScoreRepository;
import nl.fontysS3_project.persistence.entity.UserScoreEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserScoreManagerImpl {

    public final UserScoreRepository userScoreRepository;

    public UserScore getScore(int userid){
        UserScoreEntity urse = userScoreRepository.findByUser_id(userid);

        return convertuserScore(urse);
    }

    public UserScore convertuserScore(UserScoreEntity ent){
        return UserScore.builder()
                .id(ent.getId())
                .attempts(ent.getAttempts())
                .result(ent.getResult())
                .time(ent.getTime())
                .user(UserConverter.convert(ent.getUser()))
                .build();
    }
}
