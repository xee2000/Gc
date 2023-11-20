package com.assembble.gc.controller;


import com.assembble.gc.mapper.SensorMapper;
import com.assembble.gc.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/main")
    public String home(Model model){


        return "cesium";
    }


}
