package com.franciscoreina.registration.service;

import com.franciscoreina.registration.model.User;
import com.franciscoreina.registration.repository.ExclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExclusionServiceImpl implements ExclusionService {

    @Autowired
    private ExclusionRepository exclusionRepository;

    @Override
    public boolean validate(final String dateOfBirth, final String ssn) {
        final List<User> excludedUsers = exclusionRepository.getExcludedUsers();

        return excludedUsers.stream()
                .noneMatch(user -> user.getDateOfBirth().equals(dateOfBirth) && user.getSsn().equals(ssn));
    }

}
