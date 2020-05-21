package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.UserDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailModel, Long> {
    UserDetailModel findByUsername(String username);

    UserDetailModel findByEmail(String email);
}
