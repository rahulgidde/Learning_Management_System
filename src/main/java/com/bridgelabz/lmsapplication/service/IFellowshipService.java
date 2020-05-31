package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.model.QualificationModel;

public interface IFellowshipService {
    public boolean fellowshipCandidatesData();

    public int fellowshipCandidateCount();

    public boolean jobOfferMail();

    public FellowshipModel personalInfo(Long id, PersonalInfoDto personalInfoDto);

    public BankDetailsModel bankDetails(BankDetailsDto bankDetailsDto);

    public QualificationModel educationalInfo(QualificationDto qualificationDto);
}
