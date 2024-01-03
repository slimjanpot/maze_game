package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.*;

import java.util.List;

public interface UserManager {
    List<User> getUsers();

    User createUser(User request);

    void deleteUser(int userId);

    User getUser(long userId);

    User updateUser(User user, int userId);
}
