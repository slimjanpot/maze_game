
import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.business.impl.UserManagerImpl;
import nl.fontysS3_project.controllers.Request_Response.CreateUserRequest;
import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.UserScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTesting {
    @Mock
    private UserRepository rp;
    @Test
    void creatingUserTest(){
        UserManager userManager = new UserManagerImpl(rp);
        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        User response = userManager.createUser(converttouser(us));
        assertTrue(response.getId() > 0);
    }
    private static User converttouser(CreateUserRequest request){
        return User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
    @Test
    void findingUserTest(){
        UserManager manage = new UserManagerImpl(rp);

        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        User response = manage.createUser(converttouser(us));

        Optional<User> nus = manage.getUser(response.getId());

        assertEquals("steve", nus.get().getName());
    }
    @Test
    void deletingUserTest(){
        UserManager manage = new UserManagerImpl(rp);

        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        CreateUserRequest us1 = new CreateUserRequest("steve", "steven", "password");

        int id = manage.createUser(converttouser(us)).getId();
        manage.createUser(converttouser(us1));

        manage.deleteUser(id);

        assertEquals(1, manage.getUsers().size());

    }
}
