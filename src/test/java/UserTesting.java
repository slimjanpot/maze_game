
import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.business.impl.UserManagerImpl;
import nl.fontysS3_project.domain.CreateUserRequest;
import nl.fontysS3_project.domain.CreateUserResponse;
import nl.fontysS3_project.domain.User;
import nl.fontysS3_project.persistence.UserRepository;
import nl.fontysS3_project.persistence.impl.FakeUserRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTesting {
    @Test
    void creatingUserTest(){
        UserRepository rp = new FakeUserRepositoryImpl();
        UserManager userManager = new UserManagerImpl(rp);
        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        CreateUserResponse response = userManager.createUser(us);
        assertTrue(response.getUserId() > 0);
    }
    @Test
    void findingUserTest(){
        UserRepository rp = new FakeUserRepositoryImpl();
        UserManager manage = new UserManagerImpl(rp);

        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        CreateUserResponse response = manage.createUser(us);

        Optional<User> nus = manage.getUser(response.getUserId());

        assertEquals("steve", nus.get().getName());
    }
    @Test
    void deletingUserTest(){
        UserRepository rp = new FakeUserRepositoryImpl();
        UserManager manage = new UserManagerImpl(rp);
        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        CreateUserRequest us1 = new CreateUserRequest("steve", "steven", "password");
        manage.createUser(us);
        manage.createUser(us1);

        manage.deleteUser(1);

        assertEquals(1,rp.count());

    }
}
