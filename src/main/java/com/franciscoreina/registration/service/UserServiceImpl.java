package com.franciscoreina.registration.service;

import com.franciscoreina.registration.dto.UserRequestDTO;
import com.franciscoreina.registration.dto.ResponseDTO;
import com.franciscoreina.registration.model.User;
import com.franciscoreina.registration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.franciscoreina.registration.constants.Messages.ERROR_USER_EXCLUDED;
import static com.franciscoreina.registration.constants.Messages.USER_REGISTERED;

@Service
public class UserServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ExclusionService exclusionService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Register an user if not previously registered and is not in the exclusion list
     *
     * @param userRequestDTO DTO with user information to register
     * @return UserResponseDTO with a success message if the user is registered, otherwise with an error message
     */
    public ResponseDTO register(final UserRequestDTO userRequestDTO) {
        LOGGER.info("+++ Service entry +++");

        if (!exclusionService.validate(userRequestDTO.getDateOfBirth(), userRequestDTO.getSsn())) {
            throw new UnsupportedOperationException(ERROR_USER_EXCLUDED);
        }

        userRepository.register(User.from(userRequestDTO));
        return ResponseDTO.builder().description(USER_REGISTERED).build();
    }

}
