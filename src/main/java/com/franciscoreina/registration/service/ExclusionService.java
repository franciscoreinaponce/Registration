package com.franciscoreina.registration.service;

/**
 * Service to offer validation of a user against an exclusion list.
 *
 * @author gamesys
 */
public interface ExclusionService {
    /**
     * Validates a user against an exclusion list using their date of
     * birth and social security number as identifier.
     *
     * @param dateOfBirth the user's date of birth in ISO 8601 format
     * @param ssn         the user's social security number (United States)
     * @return true if the user is not excluded
     */
    boolean validate(String dateOfBirth, String ssn);

}
