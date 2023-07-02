package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponse {

    @JsonProperty("Id del Cliente")
    private String id;

    @JsonProperty("Nombre del Cliente")
    private String name;

    @JsonProperty("Apellido del Cliente")
    private String surname;

    @JsonProperty("Nombre Completo del Cliente")
    private String fullName;

    @JsonProperty("Email del Cliente")
    private String email;

    @JsonProperty("Rol del Cliente")
    private String role;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Última modificación")
    private String modificationDate;

    @JsonProperty("Cliente deshabilitado")
    private String deleted;
}