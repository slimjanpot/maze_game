package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.MapScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapScoreRepository extends JpaRepository<MapScoreEntity, Long> {

    MapScoreEntity findByUser(int id);
}
