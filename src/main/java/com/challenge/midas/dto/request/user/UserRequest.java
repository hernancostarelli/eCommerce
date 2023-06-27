package com.challenge.midas.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

        @Size(min = 3, max = 50, message = "EL NOMBRE DEL USUARIO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
        @JsonProperty("name")
        private String name;

        @Size(min = 3, max = 50, message = "EL APELLIDO DEL USUARIO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
        @JsonProperty("surname")
        private String surname;

        @Email(message = "EL CORREO ELECTRÓNICO DEL USUARIO DEBE TENER UN FORMATO CORRECTO")
        @JsonProperty("email")
        private String email;

        @Size(min = 6, message = "LA CONTRASEÑA DEL USUARIO DEBE TENER UN MÍNIMO DE 6 CARACTERES")
        @JsonProperty("password")
        private String password;

        @Size(min = 6, message = "LA CONFIRMACIÓN DE LA CONTRASEÑA DEL USUARIO DEBE TENER UN MÍNIMO DE 6 CARACTERES")
        @JsonProperty("confirmPassword")
        private String confirmPassword;
}