package ca.hccis.squash.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/apisecure/v1")
public class TestSecureRestController {

    @GetMapping("/user-profile")
    @PreAuthorize("@securityHelper.hasAllowedEmail(authentication.principal)")
    public Map<String, Object> getUserProfile(@AuthenticationPrincipal Jwt jwt) {
        // Pull standard user traits out of the verified claims payload
        String googleId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");
        String picture = jwt.getClaimAsString("picture");

        return Map.of(
                "status", "Authenticated via Google!",
                "googleId", googleId,
                "email", email,
                "name", name,
                "avatar", picture
        );
    }
}
