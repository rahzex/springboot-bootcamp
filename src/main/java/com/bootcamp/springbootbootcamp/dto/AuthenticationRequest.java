package com.bootcamp.springbootbootcamp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
}
