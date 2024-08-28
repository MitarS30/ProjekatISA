package com.example.final_project.createuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {

    private String username;
    private String password;
    private String roles; // Dodato polje za rolu
}
