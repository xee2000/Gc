package com.assembble.gc.controller;


import com.assembble.gc.dto.MbSensorDto;
import com.assembble.gc.mapper.SensorMapper;
import com.assembble.gc.service.JwtService;
import com.assembble.gc.service.SensorService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/cesium")
@EnableCaching
@Slf4j
public class CesiumController {

        @Autowired
        private SensorMapper sensorMapper;

        @Autowired
        private SensorService sensorService;

        @Autowired
        private JwtService jwtService;

        /*
        * 23.1120 이정호 이상치 있는 당일날짜의 경우 한개만 출력하여서 존재시 sensor출력 아닐경우 null출력
        * */

    @GetMapping("/main")
    public String home(Model model){

        List<MbSensorDto> sensor = sensorMapper.getMbeventList();
        Map<String,Object> map = new HashMap<>();
        map.put("map",sensor);
        for(int i = 0; i<sensor.size(); i++){

           Date Uptime =  sensor.get(i).getUpdate_time();
            log.info("센서데이터 확인 : " +Uptime);

        }
        log.info("센서데이터 확인 : " +sensor);
        
        if(map == null || map.isEmpty()){
            model.addAttribute("map",map);
        }else{
            model.addAttribute("map", map);
        }


        return "cesium";
    }



    @GetMapping("/getAnomaly")
    public ResponseEntity menubar(String alarm, Model mav) {
        System.out.println(alarm);
        List<MbSensorDto> sensor1 = sensorMapper.getMbeventList();


        Map<String, Object> map = new HashMap<>();
        map.put("map", sensor1);
        log.info("sensor init : " + sensor1);
        if (sensor1.isEmpty() || sensor1.size() == 0) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        } else {
            for (MbSensorDto sensorData : sensor1) {
                if (!sensorData.getAlarm().equals(alarm)) {
                    log.info("2번 ::  if문까지 완료");
                    if (sensorData.getAlarm().equals("Danger")) {
                        log.info("3번 ::  if문 danger까지 완료");
                         return new ResponseEntity<>(map, HttpStatus.OK);
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/get/device_number")
    public ResponseEntity sensorInfo(@RequestBody Map<String, String> params){
        int device_number = Integer.parseInt(params.get("device_number"));
        String measure_time = params.get("measure_time");

            if(device_number == 0 || measure_time.isEmpty()){
                log.info("device_number 확인 : " + device_number  + "measure_time 확인 : " + measure_time);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                log.info("device_number 확인 : " + device_number);
                log.info("measure_time 확인 : " + measure_time);

                Map<String,Object> map = new HashMap<>();
                map.put("device_number", device_number);
                map.put("measure_time",measure_time);

                Map<String, Object> dto = sensorMapper.getsensorInfo(map);
                return new ResponseEntity<>(dto,HttpStatus.OK);
            }

    }

    @GetMapping("chart")
    public String chartpage(HttpServletResponse response, Model model) throws JsonIOException, IOException {

        return "chart";
    }



    //2023-11-29 이정호 미완성
    @GetMapping("getchart")
    @ResponseBody
    public List<MbSensorDto> chartinfo(HttpServletResponse response, Model model) throws JsonIOException, IOException {
        int device_number = 1;
        List<MbSensorDto> chartList = sensorService.getChartList(device_number);

        if (!chartList.isEmpty() || chartList.size() != 0) {
            return chartList; // Return the list directly
        } else {
            return null; // Return null if the list is empty
        }
    }



}
