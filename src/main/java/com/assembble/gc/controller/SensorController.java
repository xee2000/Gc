package com.assembble.gc.controller;


import com.assembble.gc.mapper.AccountMapper;
import com.assembble.gc.mapper.SensorMapper;
import com.assembble.gc.service.JwtService;
import com.assembble.gc.util.Springsecuritypassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensor")
@EnableCaching
public class SensorController {

        @Autowired
        private SensorMapper sensorMapper;

        @Autowired
        private JwtService jwtService;

    @GetMapping("/list")
    @Cacheable
    public ResponseEntity main(
            @CookieValue(value = "token", required = false) String token) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            List<Map<String, Object>> ermList = sensorMapper.getErmList();
            if (!ermList.isEmpty() || ermList.size() != 0) {
                return new ResponseEntity<>(ermList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

}
