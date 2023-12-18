package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.domain.UserScore;
import nl.fontysS3_project.persistence.UserScoreRepository;
import nl.fontysS3_project.persistence.entity.UserScoreEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserScoreManagerImpl implements nl.fontysS3_project.business.UserScoreManager {

    public final UserScoreRepository userScoreRepository;

    @Override
    public UserScore getScore(int userid){
        UserScoreEntity urse = userScoreRepository.findByUser_id(userid);

        return convertuserScore(urse);
    }

    public void saveUserScore(UserScore userScore){userScoreRepository.save(convertTOEntity(userScore));}

    private UserScore convertuserScore(UserScoreEntity ent){

        return UserScore.builder()
                .id(ent.getId())
                .time(ent.getTime())
                .user(UserConverter.convert(ent.getUser()))
                .build();
    }
    private UserScoreEntity convertTOEntity(UserScore ent){
        return UserScoreEntity.builder()
                .time(ent.getTime())
                .user(UserConverter.toUserEntitiy(ent.getUser()))
                .build();
    }
}
