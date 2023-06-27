package com.challenge.midas.dto.request.user;

import lombok.Data;

@Data
public class UserRequestModifyPassword {
        private String oldPassword;
        private String newPassword;
        private String confirmPassword;
}