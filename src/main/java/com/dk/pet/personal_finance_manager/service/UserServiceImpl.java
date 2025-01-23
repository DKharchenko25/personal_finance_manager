package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.User;
import com.dk.pet.personal_finance_manager.exception.ResourceNotFoundException;
import com.dk.pet.personal_finance_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s not found", id)));
    }
}
