package com.challenge.midas.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {

        @NotBlank(message = "EL NOMBRE DE USUARIO NO DEBE ESTAR VACÍO")
        private String email;

        @NotBlank(message = "DEBE INGRESAR UNA CONTRASEÑA")
        private String password;
}