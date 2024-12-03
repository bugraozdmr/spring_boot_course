package com.grant.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails grant = User.builder()
                .username("grant")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails polat = User.builder()
                .username("polat")
                .password("{noop}test123")
                .roles("ADMIN","EMPLOYEE","MANAGER")
                .build();

        // noop ile bcrypt engellendi -- şifrelenmedi şifreler

        return new InMemoryUserDetailsManager(john,grant,polat);
    }
    */

    // add support for JDBC ... no more hardcoded users :-)

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // spring oto bulur bunları db'de duzgun adlandırdığımız için - authorities altında -- ROLE_EMPLOYEE tarzı
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        // bu sekilde istedigimiz yerden userları ve rollerini okumasını sağladık direkt authorities felan tanımlamadık

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP Basic Authentication

        http.httpBasic(Customizer.withDefaults());

        // disable CSRF (cross site request forgery) -- in general not required REST API's use POST PUT DELETE AND/OR PATCH

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

/*
* postman : basic auth'u seç // test123
*
* delete işleminde id bulamazsa admin dahi olsa forbidden diyor
*
* şifreli passowrlard: fun123
*
* custom tables
* */