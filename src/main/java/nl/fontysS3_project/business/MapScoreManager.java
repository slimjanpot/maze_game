package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.MapScore;

import java.util.List;

public interface MapScoreManager {
    MapScore createMapScore(MapScore request);

    List<MapScore> getMapScores();

    MapScore usersmapScore(int id);

    void deleteMapScore(int mapscoreId);

    List<MapScore> getScores(int userId);
}
