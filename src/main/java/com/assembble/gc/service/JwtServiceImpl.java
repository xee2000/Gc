package com.assembble.gc.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private String secretkey = "asddasdasdasdabjasbkasdadasdasdasdasdcacasdadsadcabafascasfassdascasdasdascvsffffbdfvdbffbdfvwdvdbedfvdvdvwdvddcvsdvdvegbthnthedsscergnfddcqascj1!@!$!$!#@!#!@$!@%!$!@$!@#!@$!@#!@$asdasdasdasdasdd#!@#";
    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 5);
        byte[] secretBytekey = DatatypeConverter.parseBase64Binary(secretkey);
        Key signkey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String,Object> map = new HashMap<>();

        map.put(key,value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signkey, SignatureAlgorithm.HS256);



        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretBytekey = DatatypeConverter.parseBase64Binary(secretkey);
                Key signkey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());
                Claims clams = Jwts.parserBuilder().setSigningKey(signkey).build().parseClaimsJws(token).getBody();
                return clams;
            }catch(ExpiredJwtException e){

            }catch(JwtException e){

            }
        }
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public int getId(String token) {
        Claims claims = this.getClaims(token);

        if(claims != null){
            return Integer.parseInt(claims.get("id").toString());
        }
        return 0;
    }
}
