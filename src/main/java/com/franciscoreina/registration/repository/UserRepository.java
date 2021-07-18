package com.franciscoreina.registration.repository;

import com.franciscoreina.registration.exception.DuplicateUserException;
import com.franciscoreina.registration.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.franciscoreina.registration.constants.Messages.USER_ALREADY_REGISTERED;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final List<User> mockUserList = Collections.synchronizedList(new ArrayList<>());

    /**
     * Register an user if not previously registered
     *
     * @param user to register
     * @return the registered user
     */
    public User register(final User user) {
        LOGGER.info("+++ Repository entry +++");

        if (mockUserList.contains(user)) {
            LOGGER.warn("+++ User '{}' has already registered +++", user.getUsername());
            throw new DuplicateUserException(USER_ALREADY_REGISTERED);
        }

        LOGGER.info("+++ User '{}' created +++", user.getUsername());
        mockUserList.add(user);
        return user;
    }

}
