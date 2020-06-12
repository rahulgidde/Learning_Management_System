package com.bridgelabz.lmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessageDto {
    private Date timestamp;
    private String message;
    private String details;
}
