package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;


import java.util.List;

public interface HiredCandidateService {
    List loadHiredCandidateSheet();

    List getHiredCandidatesList();

    HiredCandidateModel getHiredCandidatesProfile(String candidateName);
}
