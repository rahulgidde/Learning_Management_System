package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<HiredCandidateModel, Long> {
    Optional<HiredCandidateModel> findByEmailId(String emailId);
}
