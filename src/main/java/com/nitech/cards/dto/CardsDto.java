package com.nitech.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Schema(
        name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(description="Mobile Number of Customer",example="9900990099")
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 12 digits")
    @Schema(description="Card Number of Customer",example="440044004400")
    private String cardNumber;

    @NotEmpty(message = "Card type can not be a null or empty")
    @Schema(description = "Type of the card",example = "CreditCard")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(description = "Total amount limit available against a card", example = "100000")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equals to ro greater than zero")
    @Schema(description = "Total amount used by customer", example = "1000")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal to or greater than zero")
    @Schema(description = "Total available amount against a card",example = "90000")
    private int availableAmount;

}
