package nl.fontysS3_project.configuration.db;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.entity.UserEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private  UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            userRepository.save(UserEntity.builder()
                    .id(1)
                    .name("Mike")
                    .username("Mikel")
                    .email("Mike@gmail.com")
                    .age(14)
                    .bio("I love coding")
                    .location("Where am I")
                    .hashedPassword("Miky")
                    .build());

            userRepository.save(UserEntity.builder().id(2).name("Bob").username("Boby").hashedPassword("Boby").build());
            userRepository.save(UserEntity.builder().id(3).name("Rose").username("Rosa").hashedPassword("Rose").build());
        }
    }
}
