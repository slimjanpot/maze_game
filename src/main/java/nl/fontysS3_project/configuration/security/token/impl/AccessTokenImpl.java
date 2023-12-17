package nl.fontysS3_project.configuration.security.token.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.fontysS3_project.configuration.security.token.AccessToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Long userId;
    private final int permission;

    public AccessTokenImpl(String subject, Long userId, String permission) {
        this.subject = subject;
        this.userId = userId;
        this.permission = mapPermissionToInt(permission);
    }
    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public Long getUserId() {
        return userId;
    }
    @Override
    public String getPermission() {
        return (permission == 0) ? "NORMAL" : "ADMIN";
    }
    private int mapPermissionToInt(String permission) {
        return "ADMIN".equals(permission) ? 1 : 0;
    }

}
