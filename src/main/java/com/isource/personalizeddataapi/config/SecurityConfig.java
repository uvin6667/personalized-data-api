package com.isource.personalizeddataapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails dbUser = User.builder()
                .username("dbUser")
                .password(encoder().encode("dbuser@123"))
                .roles("DBUSER")
                .build();

        UserDetails ecommerceUser = User.builder()
                .username("ecommerceUser")
                .password(encoder().encode("ecommerceuser@123"))
                .roles("ECOMMERCEUSER")
                .build();

        return new InMemoryUserDetailsManager(dbUser,ecommerceUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //to avoid post request authentication failing
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/products/save-product","products/save-ppl").hasAnyRole("DBUSER")
                        .requestMatchers("/products/get-products-ppl").hasAnyRole("DBUSER","ECOMMERCEUSER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
