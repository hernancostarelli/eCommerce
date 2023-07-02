package com.challenge.midas.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserRequestModifyPassword {

        @Size(min = 6, message = "LA ANTIGUA CONTRASEÑA DEL USUARIO DEBE TENER UN MÍNIMO DE 6 CARACTERES")
        @JsonProperty("oldPassword")
        private String oldPassword;

        @Size(min = 6, message = "LA NUEVA CONTRASEÑA DEL USUARIO DEBE TENER UN MÍNIMO DE 6 CARACTERES")
        @JsonProperty("newPassword")
        private String newPassword;

        @Size(min = 6, message = "LA CONFIRMACIÓN DE LA CONTRASEÑA DEL USUARIO DEBE TENER UN MÍNIMO DE 6 CARACTERES")
        @JsonProperty("confirmPassword")
        private String confirmPassword;
}