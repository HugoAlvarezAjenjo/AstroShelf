package com.hugoalvarezajenjo.astroshelf.configuration;

import com.hugoalvarezajenjo.astroshelf.types.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/books**").hasRole(Role.LIBRARIAN.toString());
                    authorizeRequests.requestMatchers("/h2-console/**").permitAll();
                    authorizeRequests.anyRequest().permitAll();
                })
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();
                    form.loginProcessingUrl("/login");
                    form.defaultSuccessUrl("/books", true);
                    form.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF protection for the H2 console
                .headers(headers -> headers.frameOptions().disable());
        ;
        return http.build();
    }
}
