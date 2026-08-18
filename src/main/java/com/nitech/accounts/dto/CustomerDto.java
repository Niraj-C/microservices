package com.nitech.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Rahul Rathor"
    )
    @NotEmpty(message = "Name can not be a Null or Empty")
    @Size(min = 5,max = 30, message = "The Length of the customer Name is in Between 5 to 30")
   // @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "No special characters allowed")
   // @Pattern(regexp ="^[a-zA-Z0-9]*$", message = "No Special character allowed")
    //@Pattern(regexp = "^[a-z A-Z]$", message = "No Special character allowed")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only alphanumeric characters are allowed")
    private String name;

    @Schema(
            description = "Email of the customer", example = "Rahulrathor@gmail.com"
    )
    @NotEmpty(message="Email not to be Null or Empty")
    @Email(message = "Email address should be a valid value")
    @Size(min = 6,message = "Enter at list 6 letter in email")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9876543210"
    )
   // @Pattern(regexp ="(^$[0-9]{11})",message = "Mobile number Must be 10 digits")
   @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the Customer"
    )
    private AccountsDto accountsDto;
}
