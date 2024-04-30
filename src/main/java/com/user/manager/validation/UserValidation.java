package com.user.manager.validation;

import com.user.manager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

@Component
public class UserValidation {

    @Autowired
    private Validator validator;

    @Value("${user.age.min:18}")
    private int minAge;

    public void validateUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid user data");
        }

        LocalDate now = LocalDate.now();
        LocalDate minAgeDate = now.minusYears(minAge);
        if (user.getDateOfBirth().isAfter(minAgeDate)) {
            throw new IllegalArgumentException("User must be at least " + minAge + " years old");
        }
    }
}
