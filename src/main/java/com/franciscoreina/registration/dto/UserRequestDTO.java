package com.franciscoreina.registration.dto;

import com.franciscoreina.registration.validator.constraint.DateIso8601Constraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

import static com.franciscoreina.registration.constants.Constants.ALPHANUMERIC_PATTERN;
import static com.franciscoreina.registration.constants.Constants.PASSWORD_PATTERN;

@Getter
@Builder
public class UserRequestDTO {

    @NonNull
    @Pattern(regexp = ALPHANUMERIC_PATTERN, flags = Pattern.Flag.UNICODE_CASE)
    private final String username;

    @NonNull
    @Pattern(regexp = PASSWORD_PATTERN, flags = Pattern.Flag.UNICODE_CASE)
    private final String password;

    @NonNull
    @DateIso8601Constraint
    private final String dateOfBirth;

    @NonNull
    private final String ssn;

}
