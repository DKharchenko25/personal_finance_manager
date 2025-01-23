package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
