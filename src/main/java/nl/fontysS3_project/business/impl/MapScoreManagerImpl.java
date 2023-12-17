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
    public MapScore createMapScore(MapScore request){
        System.out.println("In manager: "+request);
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

    @Override
    public void saveMapScores(MapScore mp){
        mapRepo.save(convertmap(mp));
    }

    private MapScoreEntity convertmap(MapScore ms){
        return MapScoreEntity.builder()
                .map(ms.getMap())
                .time(ms.getTime())
                .user(UserConverter.toUserEntitiy(ms.getUser()))
                .build();
    }
    private MapScore convertentity(MapScoreEntity mapent){
        return MapScore.builder()
                .id(mapent.getId())
                .map(mapent.getMap())
                .time(mapent.getTime())
                .user(UserConverter.convert(mapent.getUser()))
                .build();
    }

}
