package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.MapScore;

import java.util.List;

public interface MapScoreManager {
    List<MapScore> getMapScores();

    MapScore usersmapScore(int id);

    void saveMapScores(MapScore mp);
}
