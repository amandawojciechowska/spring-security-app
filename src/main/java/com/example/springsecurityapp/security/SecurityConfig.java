package com.example.springsecurityapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.user1.password}")
    private String adminPassword;

    @Value("${spring.security.user1.name}")
    private String userName1;

    @Value("${spring.security.user2.password}")
    private String userPassword;

    @Value("${spring.security.user2.name}")
    private String userName2;

    @Value("${spring.security.user3.password}")
    private String userPassword3;

    @Value("${spring.security.user3.name}")
    private String userName3;


    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // User Creation
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // InMemoryUserDetailsManager
        UserDetails admin = User.withUsername(userName1)
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername(userName2)
                .password(encoder.encode(userPassword))
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername(userName3)
                .password(encoder.encode(userPassword3))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user, user2);
    }

    // Configuring HttpSecurity
    //TODO DELETED ACCESS TO H2 CONSOLE
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector);

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console()).disable())
                .httpBasic(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(mvcRequestMatcher.pattern("/auth/info")).permitAll()
                        .requestMatchers(mvcRequestMatcher.pattern("/producers")).permitAll()
                        .requestMatchers(mvcRequestMatcher.pattern(HttpMethod.GET, "/products/**")).permitAll()
                        .requestMatchers(mvcRequestMatcher.pattern(HttpMethod.POST, "/products/create")).hasRole("ADMIN")
                        .requestMatchers(mvcRequestMatcher.pattern("/products/**")).permitAll()
                        .requestMatchers(mvcRequestMatcher.pattern("/cart/**")).hasRole("USER")
                        .requestMatchers(mvcRequestMatcher.pattern("/auth/user/**")).hasRole("USER")
                        .requestMatchers(mvcRequestMatcher.pattern("/auth/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

}
