package com.user.manager.repositories;

import com.user.manager.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    List<User> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate);
}
