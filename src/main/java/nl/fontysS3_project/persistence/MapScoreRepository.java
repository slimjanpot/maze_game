package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.MapScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MapScoreRepository extends JpaRepository<MapScoreEntity, Long> {
    MapScoreEntity findByUser_id(int id);

    @Query("SELECT SUM(e.time) FROM MapScoreEntity e WHERE e.user.id = ?1")
    float getTotalTimePlayed(Long userId);

    @Query("SELECT COUNT(e) FROM MapScoreEntity e WHERE e.user.id = ?1 AND e.result = 1")
    int countWins(Long userId);

    @Query("SELECT COUNT(e) FROM MapScoreEntity e WHERE e.user.id = ?1")
    int countTotalGamesPlayed(Long userId);

    @Query("SELECT AVG(e.time) FROM MapScoreEntity e WHERE e.user.id = ?1 AND e.map = ?2")
    float averageTimePlayed(Long userId, Long mapId);

    @Query("SELECT COUNT(e) FROM MapScoreEntity e WHERE e.user.id = ?1 AND e.map = ?2 AND e.result = 1")
    int countMapWins(Long userId, Long mapId);

    @Query("SELECT COUNT(e) FROM MapScoreEntity e WHERE e.user.id = ?1 AND e.map = ?2")
    int countTotalGamesPlayedMap(Long userId, Long mapId);

    @Query("DELETE FROM MapScoreEntity e WHERE e.user.id = ?1")
    void deleteUserfromMapScore(int userId);
}
