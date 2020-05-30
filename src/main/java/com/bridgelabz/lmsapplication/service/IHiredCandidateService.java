package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.model.HiredCandidateModel;

import java.util.List;

public interface IHiredCandidateService {
    boolean loadHiredCandidateSheet(String filePath);

    List getHiredCandidatesList();

    HiredCandidateModel getHiredCandidatesProfile(Long candidateId);

    boolean sendEmail(EmailDto emailDto);

    HiredCandidateModel updateStatus(Long id, String status);
}
