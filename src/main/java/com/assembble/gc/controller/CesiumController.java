package com.assembble.gc.controller;


import com.assembble.gc.dto.MbSensorDto;
import com.assembble.gc.mapper.SensorMapper;
import com.assembble.gc.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cesium")
@EnableCaching
public class CesiumController {

        @Autowired
        private SensorMapper sensorMapper;

        @Autowired
        private JwtService jwtService;

        /*
        * 23.1120 이정호 이상치 있는 당일날짜의 경우 한개만 출력하여서 존재시 sensor출력 아닐경우 null출력
        * */

    @GetMapping("/main")
    public String home(){

        return "cesium";
    }

    @GetMapping("/getAnomaly")
    public void menubar(Model model){
        List<MbSensorDto> sensor = sensorMapper.getMbeventList();
        System.out.println("값 확인 :" + sensor);
        Map<String,Object> map = new HashMap<>();
        map.put("map",sensor);
        if(map == null || map.isEmpty()){
         model.addAttribute("map",null);
     }else{
         model.addAttribute("map", map);
     }
    }




}
