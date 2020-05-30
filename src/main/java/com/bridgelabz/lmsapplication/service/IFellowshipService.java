package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.model.FellowshipModel;

public interface IFellowshipService {
    public boolean fellowshipCandidatesData();

    public int fellowshipCandidateCount();

    public boolean jobOfferMail();

    public FellowshipModel personalInfo(Long id, PersonalInfoDto personalInfoDto);
}
