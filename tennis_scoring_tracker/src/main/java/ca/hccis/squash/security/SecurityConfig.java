package ca.hccis.squash.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> {
                    // 1. SECURE: Lock down your APIs
                    auth.requestMatchers("/apisecure/**").authenticated();
                    // 2. PUBLIC: Allow your home page and everything else
                    auth.anyRequest().permitAll();
                })
                // Tells Spring to check for the custom jwtDecoder bean defined below
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Use Google's published JWKS endpoint (includes 'www' and correct path)
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("https://www.googleapis.com/oauth2/v3/certs").build();

        // 1. Validate token timing (ensures it is not expired)
        OAuth2TokenValidator<Jwt> timestampValidator = new JwtTimestampValidator();

        // 2. Custom Issuer validation (Accepts Google's standard formats without doing network lookups)
        OAuth2TokenValidator<Jwt> issuerValidator = jwt -> {
            String issuer = jwt.getIssuer() != null ? jwt.getIssuer().toString() : "";
            // Google ID tokens commonly use these issuer values
            if ("https://accounts.google.com".equals(issuer) || "accounts.google.com".equals(issuer)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(new org.springframework.security.oauth2.core.OAuth2Error(
                    "invalid_issuer", "The token issuer is not Google", null
            ));
        };

        // Combine the validators together
        OAuth2TokenValidator<Jwt> combinedValidator = new DelegatingOAuth2TokenValidator<>(timestampValidator, issuerValidator);
        jwtDecoder.setJwtValidator(combinedValidator);

        return jwtDecoder;
    }
}
