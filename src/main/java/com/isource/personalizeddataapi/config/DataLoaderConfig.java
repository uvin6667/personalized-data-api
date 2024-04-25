package com.isource.personalizeddataapi.config;

import com.isource.personalizeddataapi.entity.Authority;
import com.isource.personalizeddataapi.entity.User;
import com.isource.personalizeddataapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoaderConfig {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if users already exist to prevent duplicate insertion
            if (userRepository.count() == 0) {
                User ecommerceuser = new User();
                ecommerceuser.setUsername("ecommerceuser");
                ecommerceuser.setPassword(passwordEncoder.encode("ecommerceuser@123"));
                ecommerceuser.setEnabled(true);

                Authority ecommerceuserauthority = new Authority();
                ecommerceuserauthority.setAuthority("ROLE_ECOMMERCEUSER");
                ecommerceuserauthority.setUser(ecommerceuser);

                ecommerceuser.getAuthorities().add(ecommerceuserauthority);

                User dbuser = new User();
                dbuser.setUsername("dbuser");
                dbuser.setPassword(passwordEncoder.encode("dbuser@123"));
                dbuser.setEnabled(true);

                Authority dbuserauthority = new Authority();
                dbuserauthority.setAuthority("ROLE_DBUSER");
                dbuserauthority.setUser(dbuser);

                Authority dbuseracommerceauthority = new Authority();
                dbuseracommerceauthority.setAuthority("ROLE_ECOMMERCEUSER");
                dbuseracommerceauthority.setUser(dbuser);

                dbuser.getAuthorities().add(dbuserauthority);
                dbuser.getAuthorities().add(dbuseracommerceauthority);

                userRepository.save(ecommerceuser);  // Saving the user should cascade and save the authority too
                userRepository.save(dbuser);  // Saving the user should cascade and save the authority too

            }
        };
    }
}
