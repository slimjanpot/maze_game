package nl.fontysS3_project.business.impl;

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
        MapScoreEntity mse = mapRepo.findByUser(id);
        return convertentity(mse);
    }

    @Override
    public void saveMapScores(MapScore mp){
        mapRepo.save(convertmap(mp));
    }

    private MapScoreEntity convertmap(MapScore ms){
        return MapScoreEntity.builder()
                .map(ms.getMap())
                .attempts(ms.getAttempts())
                .user(UserConverter.toUserEntitiy(ms.getUser()))
                .build();
    }
    private MapScore convertentity(MapScoreEntity mapent){
        return MapScore.builder()
                .id(mapent.getId())
                .map(mapent.getMap())
                .attempts(mapent.getAttempts())
                .time(mapent.getTime())
                .result(mapent.getResult())
                .user(UserConverter.convert(mapent.getUser()))
                .build();
    }

}
