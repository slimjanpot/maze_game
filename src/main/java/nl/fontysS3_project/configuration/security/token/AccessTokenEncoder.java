package nl.fontysS3_project.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
