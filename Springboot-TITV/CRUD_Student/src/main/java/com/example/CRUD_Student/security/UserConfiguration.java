package com.example.CRUD_Student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {

    @Bean
    @Autowired
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("Select username, ps, status from accounts where username = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("Select username, role_codes as role from accounts where username = ?");
        return userDetailsManager;
    }


//    @Bean
//    @Autowired
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails hau = User.withUsername("hau").password("{noop}123").roles("teacher").build();
//        UserDetails hung=  User.withUsername("hung").password("{noop}123").roles("manager").build();
//        UserDetails huy = User.withUsername("huy").password("{noop}123").roles("admin").build();
//        return new InMemoryUserDetailsManager(hau,hung,huy);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
          configurer -> configurer
                  .requestMatchers(HttpMethod.GET, "/api/students/**").hasAnyRole("MANAGER", "TEACHER", "ADMIN")
                  .requestMatchers(HttpMethod.GET, "/auto-api/students/**").hasAnyRole("MANAGER", "TEACHER", "ADMIN")
                  .requestMatchers(HttpMethod.POST, "/auto-api/students").hasAnyRole("TEACHER","MANAGER", "ADMIN")
                  .requestMatchers(HttpMethod.PUT, "/auto-api/students/**").hasAnyRole("MANAGER",  "ADMIN")
                  .requestMatchers(HttpMethod.DELETE, "/auto-api/students/**").hasRole("ADMIN")

        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }
}
