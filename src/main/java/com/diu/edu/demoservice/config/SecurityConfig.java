package com.diu.edu.demoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
//                            .requestMatchers(
//                                    new RegexRequestMatcher("/api/institute", "GET"),
//                                    new RegexRequestMatcher("/api/institute/active", "GET"),
//                                    new RegexRequestMatcher("/api/institute/find", "GET")
//                            ).hasRole("institute_read")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return httpSecurity.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }

    private static class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
            List<String> role = realmAccess.get("roles").stream().toList();
            if (!role.isEmpty()){
                grantedAuthorities = role.stream()
                        .map(roleName -> "ROLE_" + roleName) // Prefix to map to a Spring Security "role"
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
            return grantedAuthorities;
        }
    }

}
