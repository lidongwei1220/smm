package com.example.smm.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lidongwei
 * @date 2019-10-25
 **/

@Data
public class JwtToken implements Serializable {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
}
