package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfiguration;
import com.bridgelabz.lmsapplication.dto.*;
import com.bridgelabz.lmsapplication.exception.UserException;
import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import com.bridgelabz.lmsapplication.model.DocumentModel;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.model.QualificationModel;
import com.bridgelabz.lmsapplication.repository.*;
import com.bridgelabz.lmsapplication.util.IRabbitMQ;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class FellowshipServiceImpl implements IFellowshipService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private FellowshipRepository fellowshipRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @Autowired
    private EmailDto emailDto;

    /**
     * METHOD FOR COPY HIRED CANDIDATE TABLE DATA TO FELLOWSHIP CANDIDATE TABLE
     *
     * @return true
     */
    @Override
    public boolean fellowshipCandidatesData() {
        List list = candidateRepository.findAll();
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            FellowshipModel fellowshipModel = mapper.map(iterator.next(), FellowshipModel.class);
            if (fellowshipModel.getCandidateStatus().equals(ApplicationConfiguration.getMessageAccessor()
                    .getMessage("115")))
                fellowshipRepository.save(fellowshipModel);
        }
        return true;
    }

    /**
     * METHOD FOR GET FELLOWSHIP CANDIDATE COUNT
     *
     * @return candidate count
     */
    @Override
    public int fellowshipCandidateCount() {
        return fellowshipRepository.findAll().size();
    }

    /**
     * METHOD FOR SEND JOB OFFER MAIL
     *
     * @return true
     */
    @Override
    public boolean jobOfferMail() {
        List list = fellowshipRepository.findAll();
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Optional<FellowshipModel> fellowshipModel = Optional.ofNullable((FellowshipModel) listIterator.next())
                    .map(fellowshipModel1 -> {
                        emailDto.setEmailId(fellowshipModel1.getEmailId());
                        emailDto.setSubject("Job Offer Letter");
                        emailDto.setBody("You are selected for BridgeLabz fellowship program");
                        rabbitMQ.sendMessageToQueue(emailDto);
                        return null;
                    });
        }
        return true;
    }

    /**
     * METHOD FOR UPDATE CANDIDATE BANK INFORMATION
     *
     * @param bankDetailsDto
     * @return BankDetailsModel
     */
    @Override
    public BankDetailsModel bankDetails(BankDetailsDto bankDetailsDto) {
        BankDetailsModel bankDetailsModel = mapper.map(bankDetailsDto, BankDetailsModel.class);
        return bankRepository.save(bankDetailsModel);
    }

    /**
     * METHOD FOR UPDATE EDUCATIONAL DETAILS
     *
     * @param qualificationDto
     * @return QualificationModel
     */
    @Override
    public QualificationModel educationalInfo(QualificationDto qualificationDto) {
        QualificationModel qualificationModel = mapper.map(qualificationDto, QualificationModel.class);
        return qualificationRepository.save(qualificationModel);
    }

    /**
     * METHOD FOR UPDATE PERSONAL INFORMATION
     *
     * @param id
     * @param personalInfoDto
     * @return FellowshipModel
     */
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

    /**
     * METHOD FOR UPLOAD CANDIDATE DOCUMENT
     *
     * @param file
     * @param documentDto
     * @return true
     */
    @Override
    public String uploadFile(MultipartFile file, String documentDto) {
        try {
            DocumentDto document = new ObjectMapper().readValue(documentDto, DocumentDto.class);
            if (file.isEmpty())
                throw new UserException(UserException.exceptionType.FiLE_NOT_FOUND, "Failed is empty file");
            Map<Object, Object> parameters = new HashMap<>();
            parameters.put("public_id", "CandidateDocuments/" + document.getId() + "/" + file.getOriginalFilename());
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, parameters);
            String documentPath = uploadResult.get("url").toString();
            document.setDocumentPath(documentPath);
            DocumentModel documentModel = mapper.map(document, DocumentModel.class);
            documentRepository.save(documentModel);
            return documentPath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * METHOD FOR WRITE FILE
     *
     * @param file
     * @return file
     * @throws IOException
     */
    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}
