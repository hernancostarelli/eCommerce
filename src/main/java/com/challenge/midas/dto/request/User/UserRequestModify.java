package com.challenge.midas.dto.request.User;

import lombok.Data;

@Data
public class UserRequestModify {
        private String id;
        private String name;
        private String surname;
        private String email;
}