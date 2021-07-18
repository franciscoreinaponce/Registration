package com.franciscoreina.registration.repository;

import com.franciscoreina.registration.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ExclusionRepositoryTest {

    @InjectMocks
    private ExclusionRepository exclusionRepository;

    private List<User> createExcludedUserList() {
        return List.of(
                User.builder()
                        .username("adaLovelace")
                        .password("Analytical3ngineRulz")
                        .dateOfBirth("1815-12-10")
                        .ssn("85385075")
                        .build(),
                User.builder()
                        .username("alanTuring")
                        .password("eniGmA123")
                        .dateOfBirth("1912-06-23")
                        .ssn("123456789")
                        .build(),
                User.builder()
                        .username("konradZuse")
                        .password("zeD1")
                        .dateOfBirth("1910-06-22")
                        .ssn("987654321")
                        .build()
        );
    }

    /**
     * Method to Test: getExclusionUsers
     * What is the Scenario: Retrieves and checks that the list is composed of the following users:
     * adaLovelace, alanTuring and konradZuse
     * What is the Result: List of excluded users
     */
    @Test
    public void getExclusionUsers_retrieveTheExcludedUsers_excludedUsersList() {
        // Given
        List<User> excludedUsers = createExcludedUserList();

        // When
        List<User> mockExcludedUsers = exclusionRepository.getExcludedUsers();

        // Then
        assertThat(mockExcludedUsers, notNullValue());
        assertThat(mockExcludedUsers.size(), is(3));
        assertThat(mockExcludedUsers, is(equalTo(excludedUsers)));
    }

}
