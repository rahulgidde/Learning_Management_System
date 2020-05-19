package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiredCandidateImpl implements HiredCandidateService {

    @Autowired
    CandidateRepository repository;

    @Override
    public List getHiredCandidates() {
        return repository.findAll();
    }
}