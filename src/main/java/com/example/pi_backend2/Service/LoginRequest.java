package com.example.pi_backend2.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

        private String userName;
        private String password;


}
