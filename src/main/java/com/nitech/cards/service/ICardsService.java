package com.nitech.cards.service;

import com.nitech.cards.dto.CardsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public interface ICardsService {
/*
    @Param mobileNumber - Mobile Number of the Customer
*/
    void createCard(String mobileNumber);

//    CardsDto fetchCards(@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Card Details based on a given mobileNumber
     */

        CardsDto fetchCards(String mobileNumber);
    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
        boolean updateCard(@Valid CardsDto cardsDto);

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);
}
