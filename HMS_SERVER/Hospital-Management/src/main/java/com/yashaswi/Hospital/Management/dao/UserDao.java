package com.yashaswi.Hospital.Management.dao;

import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.enums.AccessorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    boolean existsUserByMailId(String email);

    User findByUserName(String username);

    boolean existsUserByUserName(String username);

}
