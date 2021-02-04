package com.deepak.kumbhar.jwt.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String userName;
    private String password;
}
