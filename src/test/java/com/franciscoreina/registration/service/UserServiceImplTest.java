package com.franciscoreina.registration.service;

import com.franciscoreina.registration.dto.UserRequestDTO;
import com.franciscoreina.registration.dto.ResponseDTO;
import com.franciscoreina.registration.exception.DuplicateUserException;
import com.franciscoreina.registration.model.User;
import com.franciscoreina.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.franciscoreina.registration.constants.Messages.USER_ALREADY_REGISTERED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ExclusionServiceImpl exclusionServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    /**
     * Method to Test: register
     * What is the Scenario: Register a new user
     * What is the Result: Returns a UserResponseDTO with the expected description
     */
    @Test
    public void register_registerNewUser_validUserResponseDto() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("password")
                .dateOfBirth("2000-01-01")
                .ssn("000000000")
                .build();
        User user = User.from(userRequestDTO);
        given(exclusionServiceMock.validate("2000-01-01", "000000000")).willReturn(true);
        given(userRepositoryMock.register(user)).willReturn(user);

        // When
        ResponseDTO responseDTO = userService.register(userRequestDTO);

        // Then
        assertThat(responseDTO, notNullValue());
        assertThat(responseDTO.getDescription(), is("User registered"));
    }

    /**
     * Method to Test: register
     * What is the Scenario: Register a existing user
     * What is the Result: Returns a UserResponseDTO with the expected description
     */
    @Test
    public void register_registerExistingUser_validUserResponseDto() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("Pass123")
                .dateOfBirth("2000-01-01")
                .ssn("000000000")
                .build();
        User user = User.from(userRequestDTO);
        given(exclusionServiceMock.validate("2000-01-01", "000000000")).willReturn(true);
        given(userRepositoryMock.register(user)).willThrow(new DuplicateUserException(USER_ALREADY_REGISTERED));

        // When-Then
        assertThrows(DuplicateUserException.class, () -> userService.register(userRequestDTO));
    }

    /**
     * Method to Test: register
     * What is the Scenario: Register an existing user
     * What is the Result: UnsupportedOperationException thrown
     */
    @Test
    public void register_registerExcludedUser_unsupportedOperationException() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("password")
                .dateOfBirth("2000-01-01")
                .ssn("000000000")
                .build();
        given(exclusionServiceMock.validate("2000-01-01", "000000000")).willReturn(false);

        // When-Then
        assertThrows(UnsupportedOperationException.class, () -> userService.register(userRequestDTO));
    }

}
