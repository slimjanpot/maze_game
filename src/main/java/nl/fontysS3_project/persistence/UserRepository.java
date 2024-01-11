package nl.fontysS3_project.persistence;

import jakarta.transaction.Transactional;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    void deleteById(int userId);
    long count();

    UserEntity findByUsername(String username);
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.username = :#{#updatedEntity.username}, e.email = :#{#updatedEntity.email}, e.name = :#{#updatedEntity.name}, e.age = :#{#updatedEntity.age}, e.bio = :#{#updatedEntity.bio}, e.location = :#{#updatedEntity.location} WHERE e.id = :userId")
    void updateUserById(@Param("userId") Long userId, @Param("updatedEntity") UserEntity updatedEntity);

}
