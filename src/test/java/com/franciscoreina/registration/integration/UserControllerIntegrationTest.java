package com.franciscoreina.registration.integration;

import com.franciscoreina.registration.RegistrationApplication;
import com.franciscoreina.registration.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
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
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register a new user
     * What is the Result: Returns HTTP Status 200 and the user is registered
     */
    @Test
    public void userRegisterV1_registerNewUser_responseHttp200() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("Pass123")
                .dateOfBirth("2000-01-01")
                .ssn("000000000").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("{\"description\":\"User registered\"}"));
    }

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register an existing user
     * What is the Result: Returns HTTP Status 200 but the user is not registered since it already exists
     */
    @Test
    public void userRegisterV1_registerExistingUser_responseHttp200() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username2")
                .password("Pass1234")
                .dateOfBirth("2001-01-01")
                .ssn("000000001").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        testRestTemplate.postForEntity(url, userRequestDTO, String.class);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));
        assertThat(response.getBody(), is("{\"description\":\"User has already registered\"}"));
    }

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register a new user with invalid username
     * What is the Result: Returns HTTP Status 400 specifying that the username is invalid
     */
    @Test
    public void userRegisterV1_registerUserWithInvalidUsername_responseHttp400() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("user name")
                .password("Pass123")
                .dateOfBirth("2000-01-01")
                .ssn("000000000").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), is("{\"fieldErrors\":[{\"description\":\"ERROR: Invalid username\"}]}"));
    }

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register a new user with invalid password
     * What is the Result: Returns HTTP Status 400 specifying that the password is invalid
     */
    @Test
    public void userRegisterV1_registerUserWithInvalidPassword_responseHttp400() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("pass123")
                .dateOfBirth("2000-01-01")
                .ssn("000000000").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), is("{\"fieldErrors\":[{\"description\":\"ERROR: Invalid password\"}]}"));
    }

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register a new user with invalid date of birth
     * What is the Result: Returns HTTP Status 400 specifying that the password is invalid
     */
    @Test
    public void userRegisterV1_registerUserWithInvalidDateOfBirth_responseHttp400() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("Pass123")
                .dateOfBirth("200-01-01")
                .ssn("000000000").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), is("{\"fieldErrors\":[{\"description\":\"ERROR: Invalid dateOfBirth\"}]}"));
    }

    /**
     * API to Test: user/register/v1
     * What is the Scenario: Register an user which is excluded
     * What is the Result: Returns HTTP Status 403 specifying that the user is in the exclusion list
     */
    @Test
    public void userRegisterV1_registerExcludedUser_responseHttp403() {
        // Given
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("adaLovelace")
                .password("Analytical3ngineRulz")
                .dateOfBirth("1815-12-10")
                .ssn("85385075").build();
        String url = "http://localhost:" + port + "/user/register/v1/";

        // When
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userRequestDTO, String.class);

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
        assertThat(response.getBody(), is("{\"description\":\"ERROR: User is in the exclusion list\"}"));
    }

}
