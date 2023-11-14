package com.assembble.gc.mapper;

import com.assembble.gc.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AccountMapper {

    Map<String,Object> Login(Map<String, Object> map);

    Map<String,String> findByMemId(Map<String,String> map);

    void createUser(Map<String, Object> map);
}
