package ca.hccis.squash.security;

import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

@Component("securityHelper")
public class SecurityHelper {

    private static final Set<String> ALLOWED_EMAILS = Set.of(
        "bj.maclean@gmail.com",
        "developer@gmail.com"
    );

    public boolean hasAllowedEmail(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        return email != null && email.endsWith("gmail.com"); // Accept any Gmail address
        // return email != null && ALLOWED_EMAILS.contains(email);  //one of the allowed emails
    }
}