package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.EducationalInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationalInfoModel,Long> {
}
