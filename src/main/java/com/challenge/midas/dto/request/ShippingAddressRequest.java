package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ShippingAddressRequest {

    @Size(min = 3, max = 50, message = "EL NOMBRE DE LA CALLE DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @JsonProperty("street")
    private String street;

    @Size(min = 3, max = 50, message = "EL NOMBRE DE LA CIUDAD DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @JsonProperty("city")
    private String city;

    @Size(min = 3, max = 15, message = "EL CÓDIGO POSTAL DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 15")
    @JsonProperty("zipCode")
    private String zipCode;

    @Size(min = 3, max = 15, message = "EL NOMBRE DEL PAÍS DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 15")
    @JsonProperty("country")
    private String country;
}