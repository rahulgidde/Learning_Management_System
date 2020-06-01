package com.bridgelabz.lmsapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "candidate_documents")
@Table
public class DocumentModel {
    @Id
    long id;
    private Long candidateId;
    private String documentType;
    private String documentPath;
    private String status;
    private Date creatorStatus;
    private String creatorUser;

    public DocumentModel(String fileName, String contentType, byte[] bytes) {
    this.documentPath = fileName;
    this.documentType = contentType;
    this.data = bytes;
}
}
