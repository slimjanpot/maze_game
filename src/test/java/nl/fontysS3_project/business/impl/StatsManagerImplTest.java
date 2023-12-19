package nl.fontysS3_project.business.impl;


import nl.fontysS3_project.business.StatsManager;
import nl.fontysS3_project.domain.MapStats;
import nl.fontysS3_project.domain.OverallStats;
import nl.fontysS3_project.persistence.MapScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatsManagerImplTest {

    private MapScoreRepository mapRepo;
    private StatsManager statsManager;

    @BeforeEach
    void setUp() {
        mapRepo = mock(MapScoreRepository.class);
        statsManager = new StatsManagerImpl(mapRepo);
    }

    @Test
    void getOverallStats() {
        long userId = 1;

        when(mapRepo.countTotalGamesPlayed(userId)).thenReturn(10);
        when(mapRepo.countWins(userId)).thenReturn(5);
        when(mapRepo.getTotalTimePlayed(userId)).thenReturn(100F);

        OverallStats result = statsManager.getOverallStats(userId);

        assertNotNull(result);
        assertEquals(100, result.getTotaltimeplayed());
        assertEquals(10, result.getTotalattempts());
        assertEquals(5, result.getWins());
        assertEquals(5, result.getLosses());
    }

    @Test
    void getMapStats() {
        long userId = 1;
        long mapId = 2;

        when(mapRepo.countTotalGamesPlayedMap(userId, mapId)).thenReturn(5);
        when(mapRepo.countMapWins(userId, mapId)).thenReturn(3);
        when(mapRepo.averageTimePlayed(userId, mapId)).thenReturn(50F);

        MapStats result = statsManager.getMapStats(userId, mapId);

        assertNotNull(result);
        assertEquals(50, result.getAveragetimeplayed());
        assertEquals(5, result.getTotalattempts());
        assertEquals(3, result.getWins());
        assertEquals(2, result.getLosses());
    }
}