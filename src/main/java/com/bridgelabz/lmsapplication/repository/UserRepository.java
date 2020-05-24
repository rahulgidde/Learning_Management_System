package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.UserDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetailModel, Long> {
    Optional<UserDetailModel> findByUsername(String username);

    Optional<UserDetailModel> findByEmail(String email);
}
