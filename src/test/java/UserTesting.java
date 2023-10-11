
import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.business.impl.UserManagerImpl;
import nl.fontysS3_project.controllers.Request_Response.CreateUserRequest;
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
        User response = userManager.createUser(converttouser(us));
        assertTrue(response.getId() > 0);
    }
    private static User converttouser(CreateUserRequest request){
        return User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .hashedPassword(request.getPassword())
                .build();
    }
    @Test
    void findingUserTest(){
        UserRepository rp = new FakeUserRepositoryImpl();
        UserManager manage = new UserManagerImpl(rp);

        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        User response = manage.createUser(converttouser(us));

        Optional<User> nus = manage.getUser(response.getId());

        assertEquals("steve", nus.get().getName());
    }
    @Test
    void deletingUserTest(){
        UserRepository rp = new FakeUserRepositoryImpl();
        UserManager manage = new UserManagerImpl(rp);

        CreateUserRequest us = new CreateUserRequest("steve", "steven", "password");
        CreateUserRequest us1 = new CreateUserRequest("steve", "steven", "password");

        int id = manage.createUser(converttouser(us)).getId();
        manage.createUser(converttouser(us1));

        manage.deleteUser(id);

        assertEquals(1, manage.getUsers().size());

    }
}
