package com.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    // bcrypt ya da noop -- sql scriptlerinde var -- 04 noop -- / -- 05 bcrypt fun123 -- SQL icinde

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // db'den ceker artik

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // custom tablodan cekmesi normalde users ve authorities çeker
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id,role from roles where user_id=?"
        );

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated() // Tüm istekleri kimlik doğrulama gerektiriyor
                )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage") // Özelleştirilmiş giriş sayfası
                                .loginProcessingUrl("/authenticateTheUser") // Giriş işlemi için URL
                                .permitAll() // Giriş sayfasına herkes erişebilir
                )
                .logout(logout ->
                        logout.permitAll() // Çıkış işlemleri de herkes tarafından erişilebilir
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));

        return http.build(); // Güvenlik yapılandırmasını oluştur
    }


    /* old hard-coded
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails jhon = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("Employee")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("Employee","MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("Employee","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jhon,mary,susan);
    }
    */
}
