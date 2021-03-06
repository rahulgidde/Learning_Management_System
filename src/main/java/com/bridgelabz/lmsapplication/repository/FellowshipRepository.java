package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.FellowshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FellowshipRepository extends JpaRepository<FellowshipModel, Long> {
}