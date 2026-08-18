package com.nitech.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold the successful response"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String satatusCode;


    @Schema(
            description = "Status massage int response"
    )
    private String statusMsg;
}
