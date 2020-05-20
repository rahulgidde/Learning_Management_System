package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;

import java.util.List;
import java.util.Optional;

public interface IHiredCandidateService {
    void loadHiredCandidateSheet(String filePath);

    List getHiredCandidatesList();

    Optional<HiredCandidateModel> getHiredCandidatesProfile(Long candidateId);
}
