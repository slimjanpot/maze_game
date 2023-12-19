package nl.fontysS3_project.business.impl;

import nl.fontysS3_project.business.MapScoreManager;
import nl.fontysS3_project.domain.MapScore;
import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.MapScoreRepository;
import nl.fontysS3_project.persistence.entity.MapScoreEntity;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MapScoreManagerImplTest {

    private MapScoreRepository mapRepo;

    private MapScoreManager mapScoreManager;

    @BeforeEach
    void setUp() {
        mapRepo = mock(MapScoreRepository.class);
        mapScoreManager = new MapScoreManagerImpl(mapRepo);
    }
    private final UserEntity userentity = UserEntity.builder()
            .id(1)
            .password("pass")
            .location("Somewhere")
            .bio("I love this")
            .age(23)
            .email("email")
            .username("Steve")
            .name("Steven")
            .permission(0)
            .build();

    private final MapScoreEntity mpent = MapScoreEntity.builder()
            .id(1)
            .result(1)
            .time(2.66F)
            .map(1)
            .user(userentity)
            .build();
    @Test
    void createMapScore() {
        MapScore request = MapScore.builder()
                .id(1)
                .map(1)
                .time(10.5f)
                .result(100)
                .user(new User())
                .build();

        when(mapRepo.save(any())).thenReturn(mpent);

        MapScore result = mapScoreManager.createMapScore(request);

        assertNotNull(result);
    }

    @Test
    void getMapScores() {
        when(mapRepo.findAll()).thenReturn(Collections.singletonList(mpent));

        List<MapScore> result = mapScoreManager.getMapScores();

        assertNotNull(result);
    }

    @Test
    void usersmapScore() {
        int userId = 1;

        when(mapRepo.findByUser_id(userId)).thenReturn(mpent);

        MapScore result = mapScoreManager.usersmapScore(userId);

        assertNotNull(result);
    }
}