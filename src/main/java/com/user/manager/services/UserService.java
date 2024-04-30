package com.user.manager.services;

import com.user.manager.entities.User;
import com.user.manager.populator.UserPopulator;
import com.user.manager.repositories.UserRepository;
import com.user.manager.validation.UserValidation;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidation userValidator;

    @Autowired
    private UserPopulator userPopulator;

    public User createUser(User user) {
        userValidator.validateUser(user);
        return userRepository.save(user);
    }

    public User updateUser(ObjectId userId, User updatedUser) {
        userValidator.validateUser(updatedUser);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            User result = userPopulator.populateUser(user, updatedUser);
            return userRepository.save(result);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    public User updateUserFields(ObjectId userId, User updatedUser) {
        userValidator.validateUser(updatedUser);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            BeanUtils.copyProperties(updatedUser, user, "id");
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    public void deleteUser(ObjectId userId) {
        userRepository.deleteById(userId);
    }

    public List<User> searchUsersByDateOfBirth(LocalDate fromDate, LocalDate toDate) {
        return userRepository.findByDateOfBirthBetween(fromDate, toDate);
    }
}