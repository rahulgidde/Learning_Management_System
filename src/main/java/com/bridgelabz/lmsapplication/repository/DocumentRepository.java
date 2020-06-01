package com.bridgelabz.lmsapplication.repository;

import com.bridgelabz.lmsapplication.model.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {
}
