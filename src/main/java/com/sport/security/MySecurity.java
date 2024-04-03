package com.sport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MySecurity {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, status FROM users WHERE username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select u.username, r.code from users u " +
                "inner join user_role ur on u.id = ur.user_id " +
                "inner join roles r on ur.role_id = r.id where u.username=?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer-> configurer
                        .requestMatchers("/shop/**").hasAnyRole("ADMIN", "CUSTOMER", "SELLER")
                        .requestMatchers("/seller/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
        ).formLogin(
                form->form.loginPage("/shop/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        ).logout(
                logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/shop/login")
                        .permitAll()
        ).exceptionHandling(
                configurer->configurer.accessDeniedPage("/shop/showPage403")
        );

        return http.build();
    }

}