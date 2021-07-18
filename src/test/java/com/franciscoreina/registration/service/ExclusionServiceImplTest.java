package com.franciscoreina.registration.service;

import com.franciscoreina.registration.model.User;
import com.franciscoreina.registration.repository.ExclusionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExclusionServiceImplTest {

    @InjectMocks
    private ExclusionServiceImpl exclusionService;

    @Mock
    private ExclusionRepository exclusionRepository;

    private List<User> createExclusionUserList() {
        return List.of(
                User.builder()
                        .username("adaLovelace")
                        .password("Analytical3ngineRulz")
                        .dateOfBirth("1815-12-10")
                        .ssn("85385075")
                        .build()
        );
    }

    /**
     * Method to Test: validate
     * What is the Scenario: Given a dateOfBirth and a ssn from an excluded user,
     * check if the user is in the list of the exclusion
     * What is the Result: User is excluded, therefore, it is not valid
     */
    @Test
    public void validate_checkIfTheUserIsExcluded_userExcluded() {
        // Given
        given(exclusionRepository.getExcludedUsers()).willReturn(createExclusionUserList());

        // When
        boolean isUserValid = exclusionService.validate("1815-12-10", "85385075");

        // Then
        assertThat(isUserValid, is(Boolean.FALSE));
    }

    /**
     * Method to Test: validate
     * What is the Scenario: Given a dateOfBirth and a ssn from a non excluded user,
     * check if the user is in the list of the exclusion
     * What is the Result: User is not excluded, therefore, it is valid
     */
    @Test
    public void validate_checkIfTheUserIsExcluded_userNotExcluded() {
        // Given
        given(exclusionRepository.getExcludedUsers()).willReturn(createExclusionUserList());

        // When
        boolean isUserValid = exclusionService.validate("1815-12-10", "0000000");

        // Then
        assertThat(isUserValid, is(Boolean.TRUE));
    }

}
