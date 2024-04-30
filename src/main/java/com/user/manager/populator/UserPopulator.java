package com.user.manager.populator;

import com.user.manager.entities.User;

public class UserPopulator {

    public UserPopulator() {
    }

    public User populateUser(User user, User updatedUser) {

        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        user.setLastName(updatedUser.getLastName());
        user.setFirstName(updatedUser.getFirstName());
        user.setDateOfBirth(updatedUser.getDateOfBirth());
        user.setPhoneNumber(updatedUser.getPhoneNumber());

        return user;
    }
}
