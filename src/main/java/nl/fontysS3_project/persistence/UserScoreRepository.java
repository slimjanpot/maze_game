package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.UserScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScoreRepository extends JpaRepository<UserScoreEntity, Long> {
    UserScoreEntity findByUserid(int userid);
}
