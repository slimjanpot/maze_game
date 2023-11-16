package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.*;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    List<User> getUsers();

    User createUser(User request);

    void deleteUser(int userId);

    User getUser(long userId);
}
