package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.model.BankDetailsModel;
import com.bridgelabz.lmsapplication.repository.BankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements IBankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ModelMapper mapper;

    //METHOD FOR UPDATE CANDIDATE BANK INFORMATION
    @Override
    public void bankDetails(BankDetailsDto bankDetailsDto) {
        BankDetailsModel bankDetailsModel = mapper.map(bankDetailsDto, BankDetailsModel.class);
        bankRepository.save(bankDetailsModel);
    }
}


