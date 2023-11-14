package com.assembble.gc.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Springsecuritypassword implements PasswordEncoder {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        // 패스워드를 암호화하여 반환
        return passwordEncoder.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 입력된 비밀번호와 암호화된 패스워드를 비교
        return passwordEncoder.matches(rawPassword.toString(), encodedPassword);
    }
}
