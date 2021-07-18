package com.franciscoreina.registration.repository;

import com.franciscoreina.registration.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExclusionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusionRepository.class);

    /**
     * Retrieve a list of excluded users
     *
     * @return List of users
     */
    public List<User> getExcludedUsers() {
        LOGGER.info("+++ Repository entry +++");

        return List.of(
                User.builder()
                        .username("adaLovelace")
                        .password("Analytical3ngineRulz")
                        .dateOfBirth("1815-12-10")
                        .ssn("85385075")
                        .build(),
                User.builder()
                        .username("alanTuring")
                        .password("eniGmA123")
                        .dateOfBirth("1912-06-23")
                        .ssn("123456789")
                        .build(),
                User.builder()
                        .username("konradZuse")
                        .password("zeD1")
                        .dateOfBirth("1910-06-22")
                        .ssn("987654321")
                        .build()
        );
    }

}
