package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;

public interface IFellowshipService {
    public void fellowshipCandidatesData();

    public int FellowshipCandidateCount();

    public void jobOfferMail();

    public void personalInfo(Long id, PersonalInfoDto personalInfoDto);
}
