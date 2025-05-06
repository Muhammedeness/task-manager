package com.eteration_project.eteration_project.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class KeycloakConfig {

   /* @Autowired
    private CustomJwtAuthConverter customJwtAuthConverter;*/



    @Bean
    public SecurityFilterChain initSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable());

        http.authorizeHttpRequests(authorize -> {authorize
                .requestMatchers(("/api/project/get-admin-access")).permitAll()
                .requestMatchers("/api/user/list").permitAll()
                .requestMatchers("/api/user/get-user").permitAll()
                .anyRequest().authenticated(); });

        http.oauth2ResourceServer(t-> {
            t.jwt(Customizer.withDefaults()); // default ayarları sadece  authN yapar
            //t.opaqueToken(Customizer.withDefaults()); //opque token ya da jwt issuer uri ile de ççalışabilir
            //t.jwt(configurer -> configurer.jwtAuthenticationConverter(customJwtAuthConverter));
            //customAuthconverter ile rolleri convert edeceksek with.default çalışmaz
            //bunu bir jwtAuthenticationConverter ile yapabiliriz
        });
        http.sessionManagement( t-> {
            t.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }

}
