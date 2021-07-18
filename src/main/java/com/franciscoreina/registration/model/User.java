package com.franciscoreina.registration.model;

import com.franciscoreina.registration.dto.UserRequestDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String username;
    private String password;
    private String dateOfBirth;
    private String ssn;

    public static User from(final UserRequestDTO userRequestDTO) {
        return User.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .ssn(userRequestDTO.getSsn())
                .build();
    }

}
