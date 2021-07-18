package com.franciscoreina.registration.controller;

import com.franciscoreina.registration.dto.ResponseDTO;
import com.franciscoreina.registration.dto.UserRequestDTO;
import com.franciscoreina.registration.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.franciscoreina.registration.constants.Messages.ERROR_USER_EXCLUDED;
import static com.franciscoreina.registration.constants.Messages.USER_REGISTERED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * Scenarios covering the introduction of invalid parameters must be covered in integration tests.
 * This is because the CustomizedResponseEntityExceptionHandler class
 * is not executed unless the spring context is raised.
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userServiceMock;

    /**
     * Method to Test: register
     * What is the Scenario: Given a valid user,
     * a successful call is executed which receives and returns a valid DTO
     * What is the Result: Returns a valid UserResponseDTO with the expected description
     */
    @Test
    public void register_successfulServiceCall_validUserResponseDto() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("Pass123")
                .dateOfBirth("2000-01-01")
                .ssn("000000000")
                .build();
        ResponseDTO responseDTOService = ResponseDTO.builder().description(USER_REGISTERED).build();
        given(userServiceMock.register(userRequestDTO)).willReturn(responseDTOService);

        // When
        ResponseEntity<ResponseDTO> responseEntity = userController.register(userRequestDTO);
        ResponseDTO uerResponseDTO = responseEntity.getBody();

        // Then
        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(uerResponseDTO, notNullValue());
        assertThat(uerResponseDTO.getDescription(), is("User registered"));
    }

    /**
     * Method to Test: register
     * What is the Scenario: Given an excluded user, an exception is thrown during the validation
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
        given(userServiceMock.register(userRequestDTO))
                .willThrow(new UnsupportedOperationException(ERROR_USER_EXCLUDED));

        // When-Then
        assertThrows(UnsupportedOperationException.class, () -> userController.register(userRequestDTO));
    }

}
