package com.nitech.cards.mapper;

import com.nitech.cards.dto.CardsDto;
import com.nitech.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards,CardsDto cardsDto){
            cardsDto.setCardNumber(cards.getCardNumber());
            cardsDto.setCardType(cards.getCardType());
            cardsDto.setMobileNumber(cards.getMobileNumber());
            cardsDto.setTotalLimit(cards.getTotalLimit());
            cardsDto.setAvailableAmount(cards.getAvailableAmount());
            cardsDto.setAmountUsed(cards.getAmountUsed());
            return cardsDto;

    }
    public static Cards mapToCards(CardsDto cardsDto,Cards cards){
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cards.getAvailableAmount());
        return cards;
    }

}
