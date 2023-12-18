package nl.fontysS3_project.business.impl;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.StatsManager;
import nl.fontysS3_project.domain.MapStats;
import nl.fontysS3_project.domain.OverallStats;
import nl.fontysS3_project.persistence.MapScoreRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsManagerImpl implements StatsManager {
    private final MapScoreRepository mapRepo;

    @Override
    public OverallStats getOverallStats(long userid){
        int attempts = mapRepo.countTotalGamesPlayed(userid);
        int wins = mapRepo.countWins(userid);
        return OverallStats.builder()
                .totaltimeplayed(mapRepo.getTotalTimePlayed(userid))
                .totalattempts(attempts)
                .wins(wins)
                .losses(attempts - wins)
                .build();
    }

    @Override
    public MapStats getMapStats(long userid, long mapid){
        int attempts = mapRepo.countTotalGamesPlayedMap(userid, mapid);
        int wins = mapRepo.countMapWins(userid, mapid);
        return MapStats.builder()
                .averagetimeplayed(mapRepo.averageTimePlayed(userid, mapid))
                .totalattempts(attempts)
                .wins(wins)
                .losses(attempts - wins)
                .build();
    }

}
