package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.QualificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<QualificationModel,Long> {
}
