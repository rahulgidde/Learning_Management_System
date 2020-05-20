package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;

import java.util.List;

public interface IHiredCandidateService {
    void loadHiredCandidateSheet();

    List getHiredCandidatesList();

    HiredCandidateModel getHiredCandidatesProfile(String candidateName);
}
