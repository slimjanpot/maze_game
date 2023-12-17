package nl.fontysS3_project.configuration.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    String getPermission();
}
