package com.seyed.flight_reservation.repository;

import com.seyed.flight_reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String emailId);
    /*we have only option of searching by id in jpaRepo, if we want to add more find by methods we can define method here and in controller
    we will create object of user Interface of class type (User) which is implementing this, then will use the method.

    REMEMBER TO USE METHODS FOLLOWED WITH "ByEmail" or "ByPhone" ...LIKE THESE ONLY. "byEmail won't work either"
    IF YOU NAME METHOD AS getData(String emailId), this won't get data!
    USE METHOD NAME WITH PROPER ENGLISH MEANING
     */

}