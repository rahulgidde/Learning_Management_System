package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.exception.UserException;
import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.model.QualificationModel;
import com.bridgelabz.lmsapplication.repository.BankRepository;
import com.bridgelabz.lmsapplication.repository.CandidateRepository;
import com.bridgelabz.lmsapplication.repository.FellowshipRepository;
import com.bridgelabz.lmsapplication.repository.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class FellowshipServiceImpl implements IFellowshipService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private FellowshipRepository fellowshipRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    //METHOD FOR COPY HIRED CANDIDATE TABLE DATA TO FELLOWSHIP CANDIDATE TABLE
    @Override
    public boolean fellowshipCandidatesData() {
        List list = candidateRepository.findAll();
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            FellowshipModel fellowshipModel = mapper.map(iterator.next(), FellowshipModel.class);
            if (fellowshipModel.getCandidateStatus().equals("Accept"))
                fellowshipRepository.save(fellowshipModel);
        }
        return true;
    }

    //METHOD FOR GET FELLOWSHIP CANDIDATE COUNT
    @Override
    public int fellowshipCandidateCount() {
        return fellowshipRepository.findAll().size();
    }

    //METHOD FOR SEND JOB OFFER MAIL
    @Override
    public boolean jobOfferMail() {
        List list = fellowshipRepository.findAll();
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Optional<FellowshipModel> fellowshipModel = Optional.ofNullable((FellowshipModel) listIterator.next())
                    .map(fellowshipModel1 -> {
                        try {
                            MimeMessage message = javaMailSender.createMimeMessage();
                            MimeMessageHelper helper;
                            helper = new MimeMessageHelper(message, true);
                            helper.setSubject("Job Offer Letter");
                            helper.setTo(fellowshipModel1.getEmailId());
                            helper.setText("You are selected for BridgeLabz fellowship program", true);
                            javaMailSender.send(message);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
        }
        return true;
    }

    //METHOD FOR UPDATE CANDIDATE BANK INFORMATION
    @Override
    public BankDetailsModel bankDetails(BankDetailsDto bankDetailsDto) {
        BankDetailsModel bankDetailsModel = mapper.map(bankDetailsDto, BankDetailsModel.class);
        return bankRepository.save(bankDetailsModel);
    }

    //METHOD FOR UPDATE EDUCATIONAL DETAILS
    @Override
    public QualificationModel educationalInfo(QualificationDto qualificationDto) {
        QualificationModel qualificationModel = mapper.map(qualificationDto, QualificationModel.class);
        return qualificationRepository.save(qualificationModel);
    }

    //METHOD FOR UPDATE PERSONAL INFORMATION
    @Override
    public FellowshipModel personalInfo(Long id, PersonalInfoDto personalInfoDto) {
        FellowshipModel fellowshipModel1 = fellowshipRepository.findById(id).map(fellowshipModel -> {
            fellowshipModel.setBirthDate(personalInfoDto.getBirthDate());
            fellowshipModel.setVerifyBirthDate(personalInfoDto.getVerifyBirthDate());
            fellowshipModel.setParentName(personalInfoDto.getParentName());
            fellowshipModel.setParentOccupation(personalInfoDto.getParentOccupation());
            fellowshipModel.setParentMobileNumber(personalInfoDto.getParentMobileNumber());
            fellowshipModel.setParentAnnualSalary(personalInfoDto.getParentAnnualSalary());
            fellowshipModel.setLocalAddress(personalInfoDto.getLocalAddress());
            fellowshipModel.setPermanentAddress(personalInfoDto.getPermanentAddress());
            fellowshipModel.setPhotoPath(personalInfoDto.getPhotoPath());
            return fellowshipModel;
        }).map(fellowshipRepository::save)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "Candidate Not Found"));
        return fellowshipModel1;
    }
}
