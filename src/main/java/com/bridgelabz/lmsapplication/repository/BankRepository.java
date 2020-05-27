package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankDetailsModel, Long> {
}
