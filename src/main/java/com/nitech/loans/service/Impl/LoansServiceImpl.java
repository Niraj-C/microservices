package com.nitech.loans.service.Impl;

import com.nitech.loans.constant.LoansConstant;
import com.nitech.loans.dto.LoansDto;
import com.nitech.loans.entity.Loans;
import com.nitech.loans.exception.ResourceNotFoundException;
import com.nitech.loans.mapper.LoansMapper;
import com.nitech.loans.repositery.LoansRepository;
import com.nitech.loans.service.ILoansService;
import com.nitech.loans.exception.LoanAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans>optionalLoans=loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){

            throw new LoanAlreadyExistsException("The loan is already registered with given mobile number "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }



    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan= new Loans();
        long randomLoanNumber=100000000000L+new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber){
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loans","mobileNumber ",mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto){
        Loans loans=loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","LoanNumber",loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber){
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan ","mobileNumber ", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoan_id());
        return true;

    }


}
