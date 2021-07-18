package com.franciscoreina.registration.integration;

import com.franciscoreina.registration.RegistrationApplication;
import com.franciscoreina.registration.dto.UserRequestDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(classes = RegistrationApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerParamIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @ParameterizedTest
    @CsvSource({
            "username123, Pass123, 2000-01-01, 000000000, true",    // Green path, valid inputs
            "username123, Pass123, 2020-02-29, 000000000, true",    // Green path, valid inputs (leap year)
            "username123, Pass123, 2000-01-01, '', true",           // Green path, valid inputs (ssn empty)
            "username123!, Pass123, 2000-01-01, 000000000, false",  // Red path, username with symbols
            "user name, Pass123, 2000-01-01, 000000000, false",     // Red path, username with space
            "username, Pw1, 2000-01-01, 000000000, false",          // Red path, password less than 4 characters
            "username, pass123, 2000-01-01, 000000000, false",      // Red path, password without upper case characters
            "username, PASS123, 2000-01-01, 000000000, false",      // Red path, password without lower case characters
            "username, Password, 2000-01-01, 000000000, false",     // Red path, password without numbers
            "username, Pass123, 2021-02-29, 000000000, false",      // Red path, date of birth wrong leap year
            "username, Pass123, 2000-20-01, 000000000, false",      // Red path, date of birth wrong month
            "username, Pass123, 2000-01-40, 000000000, false",      // Red path, date of birth wrong day
            "username, Pass123, 20000101, 000000000, false",        // Red path, date of birth wrong format
            "username, Pass123, 200-01-01, 000000000, false",       // Red path, date of birth wrong year format
            "username, Pass123, 2000-0-01, 000000000, false",       // Red path, date of birth wrong month format
            "username, Pass123, 2000-01-0, 000000000, false",       // Red path, date of birth wrong day format
    })
    public void validateInputs(String username, String password, String dateOfBirth, String ssn, boolean valid) {
        // Given
        final var userRequestDTO = UserRequestDTO.builder()
                .username(username)
                .password(password)
                .dateOfBirth(dateOfBirth)
                .ssn(ssn)
                .build();

        final String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), valid ? is(HttpStatus.OK) : is(HttpStatus.BAD_REQUEST));
    }

}
