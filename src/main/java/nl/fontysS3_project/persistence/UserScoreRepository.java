package nl.fontysS3_project.persistence;

import nl.fontysS3_project.persistence.entity.UserScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScoreEntity, Long> {
    UserScoreEntity findByUser_id(int userid);
}
