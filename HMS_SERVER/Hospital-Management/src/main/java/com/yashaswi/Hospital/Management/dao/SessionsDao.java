package com.yashaswi.Hospital.Management.dao;

import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionsDao extends JpaRepository<Sessions, String> {

}
