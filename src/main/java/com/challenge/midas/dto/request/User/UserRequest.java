package com.challenge.midas.dto.request.User;

import lombok.Data;

@Data
public class UserRequest {
        private String name;
        private String surname;
        private String email;
        private String password;
        private String confirmPassword;
}