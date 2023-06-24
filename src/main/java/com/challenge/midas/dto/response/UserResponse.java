package com.challenge.midas.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String surname;
    private String fullName;
    private String email;
    private String role;
    private String creationDate;
    private String modificationDate;
    private String deleted;
}
