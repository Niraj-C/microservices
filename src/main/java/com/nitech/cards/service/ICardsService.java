package com.nitech.cards.service;

import com.nitech.cards.dto.CardsDto;
import jakarta.validation.constraints.Pattern;

public interface ICardsService {
/*
    @Param mobileNumber - Mobile Number of the Customer
*/
    void createCard(String mobileNumber);


    CardsDto fetchCards(@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber);
}
