package com.rasthrabhasha.auth.jwt;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/ping",
                    "/getStudent",
                    "/getAllStudents",
                    "/getStudentById",
                    "/students",
                    "/addStudent",
                    "/exams/**",
                    "/getRegions",
                    "/regions",
                    "/addregion",
                    "/getAllExamCentres",
                    "/exam-centres",
                    "/addExamCentre",
                    "/getAllSchools",
                    "/schools",
                    "/addSchool",
                    "/getStudentProfile",
                    "/getAllStudentProfiles",
                    "/studentProfiles",
                    "/studentProfiles/me",
                    "/studentprofile/**",
                    "/addStudentProfile",
                    "/getStudentProfileById",

                    "/exam-results/**",
                    "/getExamResult",
                    "/getAllResults",
                    "/getStudentResults",
                    "/summary",
                    "/counts/**",
                    "/files/**",
                    "/admin/**",
                    "/exam-officer/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
