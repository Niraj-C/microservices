package com.nitech.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loans",description = "Schema to hold Loan information")
@Data
public class LoansDto {
    @NotEmpty(message = "Mobile Number can not be null")
    @Pattern(regexp = "(^$|[0-9]){10}",message = "mobile number must be 10 digits")
    @Schema(description = "Mobile number of customer ", example = "9999999999")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]){12}",message = "Loan number must have 12 digits")
    @Schema(description = "Loan Number of customer ", example = "12121287789034")
    private String loanNumber;

    @NotEmpty(message = "Loan Type can not be empty")
    @Schema(description = "Loan type of customer ",example = "Home loan")
    private String loanType;

    @Positive(message = "total amount of loan is greater than zero")
    @Schema(description = "Total Loan Amount ",example = "1000000")
    private int totalLoan;

    @PositiveOrZero(message = "Total amount paid should be greater then or equals to zero")
    @Schema(description = "Total amount paid ", example = "100000")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equals or greater than zero")
    @Schema(description = "Total outstanding amount against a loan ", example = "900000")
    private int outstandingAmount;

}
