package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.model.HiredCandidateModel;

import java.util.List;

public interface IHiredCandidateService {
    void loadHiredCandidateSheet(String filePath);

    List getHiredCandidatesList();

    HiredCandidateModel getHiredCandidatesProfile(Long candidateId);

    void sendEmail(EmailDto emailDto);

    HiredCandidateModel updateStatus(Long id, String status);
}
