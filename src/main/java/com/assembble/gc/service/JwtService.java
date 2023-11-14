package com.assembble.gc.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String getToken(String key, Object value);
    Claims getClaims(String token);
    boolean isValid(String token);
    int getId(String token);

}
