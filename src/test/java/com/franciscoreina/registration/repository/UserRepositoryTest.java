package com.franciscoreina.registration.repository;

import com.franciscoreina.registration.exception.DuplicateUserException;
import com.franciscoreina.registration.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;

    private List<User> checkMockUserListStatus() {
        try {
            Field field = UserRepository.class.getDeclaredField("mockUserList");
            field.setAccessible(true);
            return (List<User>) field.get(userRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to Test: register
     * What is the Scenario: Register a new user
     * What is the Result: The user has been registered
     */
    @Test
    public void register_registerANewUser_userRegistered() {
        // Given
        List<User> mockUserList = checkMockUserListStatus();
        assert mockUserList != null;
        User user = User.builder()
                .username("username")
                .password("password")
                .dateOfBirth("2000-01-01")
                .ssn("000000000").build();

        // When
        int userListSizeBeforeServiceCall = mockUserList.size();
        User responseUser = userRepository.register(user);
        int userListSizeAfterServiceCall = mockUserList.size();

        // Then
        assertThat(mockUserList, notNullValue());
        assertThat(userListSizeBeforeServiceCall, is(0));
        assertThat(userListSizeAfterServiceCall, is(1));
        assertThat(responseUser, is(equalTo(user)));
    }

    /**
     * Method to Test: register
     * What is the Scenario: Register an existing user
     * What is the Result: The user has not been registered again
     */
    @Test
    public void register_registerAnExistingUser_userNotRegistered() {
        // Given
        List<User> mockUserList = checkMockUserListStatus();
        assert mockUserList != null;
        User user = User.builder()
                .username("username")
                .password("password")
                .dateOfBirth("2000-01-01")
                .ssn("000000000").build();
        mockUserList.add(user);

        // When-Then
        assertThat(mockUserList, notNullValue());
        assertThat(mockUserList.size(), is(1));
        assertThrows(DuplicateUserException.class, () ->userRepository.register(user));
    }

}
