package nl.fontysS3_project.configuration.security.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.fontysS3_project.configuration.security.token.AccessToken;
import nl.fontysS3_project.configuration.security.token.AccessTokenDecoder;
import nl.fontysS3_project.configuration.security.token.exception.InvalidAccessTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {

    private static final String PERMISSION_PREFIX = "ROLE_";

    @Autowired
    private AccessTokenDecoder accessTokenDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String accessTokenString = requestTokenHeader.substring(7);

        try {
            AccessToken accessToken = accessTokenDecoder.decode(accessTokenString);
            setupSpringSecurityContext(accessToken);
            System.out.println("Request: " + request);
            chain.doFilter(request, response);
        } catch (InvalidAccessTokenException e) {
            logger.error("Error validating access token", e);
            sendAuthenticationError(response);
        }
    }

    private void sendAuthenticationError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.flushBuffer();
    }

    private void setupSpringSecurityContext(AccessToken accessToken) {
        System.out.println("AccessToken Permission: " + accessToken.getPermission());
        List<String> authorities = mapPermissionsToAuthorities(accessToken.getPermission());

        UserDetails userDetails = new User(accessToken.getSubject(), "",
                authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList());

        System.out.println("Authorities: " + userDetails.getAuthorities());
        System.out.println("Password: " + userDetails.getPassword());
        System.out.println("Username: " + userDetails.getUsername());
        System.out.println("enabled: " + userDetails.isEnabled());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(accessToken);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    private List<String> mapPermissionsToAuthorities(String permission) {
        List<String> authorities = new ArrayList<>();

        if ("NORMAL".equals(permission)) {
            authorities.add(PERMISSION_PREFIX + "NORMAL");
        } else {
            authorities.add(PERMISSION_PREFIX + "ADMIN");
        }

        return authorities;
    }

}