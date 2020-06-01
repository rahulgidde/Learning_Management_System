package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentDto {
    private long id;
    private Long candidateId;
    private String documentType;
    private String documentPath;
    private String status;
    private LocalDateTime creatorStamp;
    private long creatorUser;
}

