package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;

import java.util.List;

public interface IHiredCandidateService {
    boolean loadHiredCandidateSheet(String filePath);

    List getHiredCandidatesList();

    HiredCandidateModel getHiredCandidatesProfile(Long candidateId);

    boolean sendEmail(String emailId);

    HiredCandidateModel updateStatus(Long id, String status);
}
