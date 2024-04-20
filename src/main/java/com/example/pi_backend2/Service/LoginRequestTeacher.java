package com.example.pi_backend2.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestTeacher {
    private String userName;
    private String password;
//    private String CodeProf;
}
