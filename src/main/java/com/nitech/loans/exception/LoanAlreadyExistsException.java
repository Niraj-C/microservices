package com.nitech.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException{

   // private LoanAlreadyExistsException loanAlreadyExistsException;
    public LoanAlreadyExistsException(String message){
        super(message);
    }

}
