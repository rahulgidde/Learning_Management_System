package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsRepository extends JpaRepository<UserDetail, Long> {
    UserDetail findByUsername(String username);
}
