package com.franciscoreina.registration.constants;

public interface Messages {

    // Successful
    String USER_REGISTERED = "User registered";
    String USER_ALREADY_REGISTERED = "User has already registered";

    // Error
    String ERROR_INVALID_USERNAME = "ERROR: Invalid username";
    String ERROR_INVALID_PASSWORD = "ERROR: Invalid password";
    String ERROR_INVALID_DATE = "ERROR: Invalid Date, it must be in ISO 8601 format";
    String ERROR_INVALID_DATE_OF_BIRTH = "ERROR: Invalid dateOfBirth";
    String ERROR_USER_EXCLUDED = "ERROR: User is in the exclusion list";

}
