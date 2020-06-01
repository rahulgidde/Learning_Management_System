package com.bridgelabz.lmsapplication.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "candidate_document")
@Table
public class DocumentModel {
    @Id
    private long id;
    private Long candidateId;
    private String documentType;
    private String documentPath;
    private String status;
    private LocalDateTime creatorStamp;
    private long creatorUser;

    public LocalDateTime getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(LocalDateTime creatorStamp) {
        this.creatorStamp = LocalDateTime.now();
    }

    public long getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(long creatorUser) {
        this.creatorUser = this.candidateId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateId", referencedColumnName = "id", insertable = false, updatable = false)
    private FellowshipModel fellowshipModel;
}
