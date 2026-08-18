package com.nitech.accounts.controller;

import com.nitech.accounts.constants.AccountsConstants;
import com.nitech.accounts.dto.CustomerDto;
import com.nitech.accounts.dto.ErrorResponseDto;
import com.nitech.accounts.dto.ResponseDto;
import com.nitech.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST API's for Account in Easy Bank",
        description = "CRUD REST API's in Easy Bank to  CREATE, UPDATE, DELETE and FETCH"
)
@RestController
@RequestMapping(path = "/api",produces ={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Bank Account REST API",
            description = "REST API to create new Customer & Bank Account inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="201",
                    description ="HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Bank Account Details REST API",
            description = "REST API to Fetch Customer & Account Details based on a mobile number"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must have 10 digits") String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account and Customer Details REST API",
            description = "REST API to Update Customer & Bank Account Details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception failed"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto>updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated=iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));

        }
        else {

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account and Customer Details REST API",
            description = "REST API to delete Customer & Bank Account Details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception failed"
            ),
            @ApiResponse(
                    responseCode ="500",
                    description ="HTTP Status Internal Server Error",
                    content = @Content(
                           schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam   @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits") String  mobileNumber){
        boolean isDelete=iAccountsService.deleteAccount(mobileNumber);
        if (isDelete){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }

  /*  @GetMapping("sayhi")
    public String sayHi() {
        return "Hi... Sir how can I help you ?" ;
    }
*/
}
