package com.assembble.gc.controller;


import com.assembble.gc.mapper.AccountMapper;
import com.assembble.gc.service.JwtService;
import com.assembble.gc.util.Springsecuritypassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
@Slf4j
public class AccountController {

        @Autowired
        private AccountMapper accountMapper;

        @Autowired
        private JwtService jwtService;



        //로그인시의 id와 pwd를 가져온뒤 security와 비교하여 존재하면 토큰에 넣어준뒤 토큰을 브라우저상의 쿠키에 담아주도록 합니다.
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody Map<String, String> params, HttpServletResponse res){
        String id = params.get("id");
        String pwd = params.get("pwd");
        Map<String, String> Map = new HashMap<>();
        Map.put("id",id);
        Map.put("pwd",pwd);

       Map<String,String> dto=  accountMapper.findByMemId(Map);
        if (dto.get("id") != null && !dto.get("id").equals("")) {
            Springsecuritypassword encoder = new Springsecuritypassword();
            //사용자의 로그인시도시의 패스워드와 데이터베이스상의 암호화된 패스워드를 인코더로 비교
            if (encoder.matches(params.get("pwd"), dto.get("pwd"))) {
                String idToken = dto.get("id");
                String token = jwtService.getToken("id", idToken);
                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                res.addCookie(cookie);
            log.info("clear : " + token);
                log.info("clear : " + token);

                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }else{
            log.error("user none!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.error("user UNAUTHORIZED!!");
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/account/create")
    public ResponseEntity create(@RequestBody Map<String, String> params) {
        String id = params.get("id");
        String pwd = params.get("pwd");
        Map<String, String> dto = new HashMap<>();
        dto.put("id",id);
        Map<String,String> Dto = accountMapper.findByMemId(dto);

        //아이디가 미존재 즉 신규회원 ok
        if (Dto == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Map<String,Object> member = new HashMap<>();
            member.put("id",params.get("id"));
            member.put("pwd",passwordEncoder.encode(params.get("pwd")).trim());
            accountMapper.createUser(member);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }



}
