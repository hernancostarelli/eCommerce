package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty("Status Code")
    private int statusCode;

    @JsonProperty("Type Code")
    private String typeCode;

    @JsonProperty("Type Exception")
    private String typeException;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("More Info")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> moreInfo;

    public ErrorResponse(int statusCode, String typeException, String message, String moreInfo) {
        this.statusCode = statusCode;
        this.typeCode = typeException;
        this.typeException = typeException;
        this.message = message;
        this.moreInfo = List.of(moreInfo);
    }
}