package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.model.QualificationModel;
import com.bridgelabz.lmsapplication.repository.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements IEducationService {
    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private ModelMapper mapper;

    //METHOD FOR UPDATE EDUCATIONAL DETAILS
    @Override
    public void educationalInfo(QualificationDto qualificationDto) {
        QualificationModel qualificationModel = mapper.map(qualificationDto, QualificationModel.class);
        qualificationRepository.save(qualificationModel);
    }
}
