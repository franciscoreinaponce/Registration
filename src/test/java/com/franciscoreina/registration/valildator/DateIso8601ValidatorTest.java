package com.franciscoreina.registration.valildator;

import com.franciscoreina.registration.validator.DateIso8601Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * The purpose of this class is to increase the coverage of the date validator by doing a quick check
 * without having to run the Spring context.
 * <p>
 * The tests of the different test cases can be found in the class UserControllerParamIntegrationTest
 */
@ExtendWith(MockitoExtension.class)
public class DateIso8601ValidatorTest {

    @InjectMocks
    private DateIso8601Validator dateIso8601Validator;

    @Test
    public void isValid_checkValidDateFormat_true() {
        // Given
        String date = "2000-01-01";

        // When
        boolean result = dateIso8601Validator.isValid(date, null);

        // Then
        assertThat(result, is(true));
    }

    @Test
    public void isValid_checkInvalidDateFormat_false() {
        // Given
        String date = "20000101";

        // When
        boolean result = dateIso8601Validator.isValid(date, null);

        // Then
        assertThat(result, is(false));
    }

}
