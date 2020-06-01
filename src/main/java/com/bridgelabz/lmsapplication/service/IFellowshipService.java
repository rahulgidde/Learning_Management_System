package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.model.QualificationModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IFellowshipService {
    public boolean fellowshipCandidatesData();

    public int fellowshipCandidateCount();

    public boolean jobOfferMail();

    public FellowshipModel personalInfo(Long id, PersonalInfoDto personalInfoDto);

    public BankDetailsModel bankDetails(BankDetailsDto bankDetailsDto);

    public QualificationModel educationalInfo(QualificationDto qualificationDto);

    public File convertMultiPartToFile(MultipartFile file) throws IOException;

    String uploadFile(MultipartFile file, String id);
}
