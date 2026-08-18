package com.nitech.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of the Bank Account"
    )
    @NotEmpty(message = "Account number can not be Null or Empty")
   //@Pattern(regexp = "(^$[0-9]{10})",message = "Account Number Must be 10 Digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of the Bank Account",example = "Saving"
    )
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(description = "Branch Address of the Bank Account")
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;

}
