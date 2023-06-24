package com.challenge.midas.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {

        @NotBlank(message = "El nombre de usuario no debe estar vacío")
        private String email;
        @NotBlank(message = "La contraseña no debe estar vacía")
        private String password;
}