package com.nitech.cards.service.Impl;

import com.nitech.cards.constants.CardsConstants;
import com.nitech.cards.entity.Cards;
import com.nitech.cards.exception.CardAlreadyExistsException;
import com.nitech.cards.repository.CardsRepository;
import com.nitech.cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;

//@Service
//public class CardsServiceImpl implements ICardsService {
//    private CardsRepository cardsRepository;
//
//    @Override
//    public void createCard(String mobileNumber) {
//        Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
//        if (optionalCards.isPresent()){
//            throw new CardAlreadyExistsException("Card already registered with given mobile number"+mobileNumber);
//        }
//      //  cardsRepository.save(createNewCard(mobileNumber));
//          cardsRepository.save(createNewCard(mobileNumber));
//    }
//
//    private Cards createNewCard(String mobileNumber) {
//
//        Cards newCards= new Cards();
//        Long randomCardNumber= 100000000000L+new Random().nextInt(900000000);
//        newCards.setCardNumber(Long.toString(randomCardNumber));
//        newCards.setMobileNumber(mobileNumber);
//        newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
//        newCards.setCardType(CardsConstants.CREDIT_CARD);
//        newCards.setAmountUsed(0);
//        newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
//        return newCards;
//
//    }
//}

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> optionalCards =
                cardsRepository.findByMobileNumber(mobileNumber);

        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException(
                    "Card already registered with given mobile number "
                            + mobileNumber
            );
        }

        //cardsRepository.save(createNewCard(mobileNumber));
        cardsRepository.save((createNewCard(mobileNumber)));
    }

    private Cards createNewCard(String mobileNumber) {

        Cards newCards = new Cards();

        Long randomCardNumber =
                100000000000L + new Random().nextInt(900000000);

        newCards.setCardNumber(Long.toString(randomCardNumber));
        newCards.setMobileNumber(mobileNumber);
        newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCards.setCardType(CardsConstants.CREDIT_CARD);
        newCards.setAmountUsed(0);
        newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCards;
    }
}
