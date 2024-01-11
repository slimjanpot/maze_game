package nl.fontysS3_project.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.MapScoreManager;
import nl.fontysS3_project.domain.MapScore;
import nl.fontysS3_project.persistence.MapScoreRepository;
import nl.fontysS3_project.persistence.entity.MapScoreEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MapScoreManagerImpl implements MapScoreManager {

    private final MapScoreRepository mapRepo;

    @Override
    public MapScore createMapScore(MapScore request){
        return convertentity(mapRepo.save(convertmap(request)));
    }

    @Override
    public List<MapScore> getMapScores(){
        List<MapScoreEntity> result = mapRepo.findAll();

        List<MapScore> mapScores = result
                .stream()
                .map(this::convertentity)
                .toList();

        return mapScores;
    }

    @Override
    public MapScore usersmapScore(int id){
        MapScoreEntity mse = mapRepo.findByUser_id(id);
        return convertentity(mse);
    }

    @Transactional
    @Override
    public void deleteMapScore(int mapscoreId) {//this is deleting all users specific mapscores
        System.out.println(mapscoreId);
        mapRepo.deleteUserfromMapScore((long) mapscoreId);
    }

    @Override
    public List<MapScore> getScores(int userId) {
        List<MapScoreEntity> mapent = mapRepo.findAllByUserId((long) userId);
        return mapent.stream().map(this::convertentity).toList();
    }

    private MapScoreEntity convertmap(MapScore ms){
        return MapScoreEntity.builder()
                .map(ms.getMap())
                .time(ms.getTime())
                .result(ms.getResult())
                .user(UserConverter.toUserEntitiy(ms.getUser()))
                .build();
    }
    private MapScore convertentity(MapScoreEntity mapent){
        return MapScore.builder()
                .id(mapent.getId())
                .map(mapent.getMap())
                .time(mapent.getTime())
                .result(mapent.getResult())
                .user(UserConverter.convert(mapent.getUser()))
                .build();
    }

}
