package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.repository.CandidateRepository;
import com.bridgelabz.lmsapplication.repository.FellowshipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

@Service
public class FellowshipServiceImpl implements IFellowshipService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    FellowshipRepository fellowshipRepository;

    @Autowired
    ModelMapper mapper;

    //METHOD FOR COPY HIRED CANDIDATE TABLE DATA TO FELLOWSHIP CANDIDATE TABLE
    @Override
    public void fellowshipCandidatesData() {
        List list = candidateRepository.findAll();
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            FellowshipModel fellowshipModel = mapper.map(iterator.next(), FellowshipModel.class);
            if (fellowshipModel.getCandidateStatus().equals("Accept"))
                fellowshipRepository.save(fellowshipModel);
        }
    }

    //METHOD FOR GET FELLOWSHIP CANDIDATE COUNT
    @Override
    public int FellowshipCandidateCount() {
        List list = fellowshipRepository.findAll();
        return list.size();
    }
}
