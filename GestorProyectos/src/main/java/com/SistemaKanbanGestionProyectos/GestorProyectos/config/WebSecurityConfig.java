package com.SistemaKanbanGestionProyectos.GestorProyectos.config;

import com.SistemaKanbanGestionProyectos.GestorProyectos.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/login/authenticate1/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                        .requestMatchers("/https://kanban-production-d917.up.railway.app/**").permitAll()
                        .requestMatchers("/v1/login/authenticate1").permitAll()
                        .requestMatchers("api/v1/projectsForPage").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers("/**").hasRole("ADMIN")
                        .requestMatchers("/v1/listaTask").hasRole("USER")



                        .requestMatchers("/v1/login/authenticate1").permitAll()
                        .requestMatchers("api/v1/projectsForPage").permitAll()
                        .requestMatchers("api/v1/projectsForPage/{id}").permitAll()
                        .requestMatchers("api/v1/projectsForPage/{id}/tasks").permitAll()
                        .requestMatchers("api/v1/projectsForPage/{id}/tasks/{id}").permitAll()
                        .requestMatchers("api/v1/projectsForPage/{id}/tasks/{id}/subtasks").permitAll()
                        .requestMatchers("api/v1/projectsForPage/{id}/tasks/{id}/subtasks/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                        authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}



