package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShippingAddressResponse {

    @JsonProperty("Id de la dirección de envío")
    private String id;

    @JsonProperty("Calle")
    private String street;

    @JsonProperty("Ciudad")
    private String city;

    @JsonProperty("Código postal")
    private String zipCode;

    @JsonProperty("País")
    private String country;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Fecha de modificación")
    private String modificationDate;

    @JsonProperty("Dirección de envío deshabilitada")
    private String deleted;
}