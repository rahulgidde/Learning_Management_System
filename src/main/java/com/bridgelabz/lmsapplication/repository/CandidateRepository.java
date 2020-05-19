package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<HiredCandidateModel, Long> {
    HiredCandidateModel findByFirst_name(String candidateName);
}
