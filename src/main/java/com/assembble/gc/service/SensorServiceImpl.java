package com.assembble.gc.service;

import com.assembble.gc.dto.MbSensorDto;
import com.assembble.gc.mapper.SensorMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SensorServiceImpl implements  SensorService{

    @Autowired
    private SensorMapper sensorMapper;
    public ArrayList<MbSensorDto> getChartList(int deviceNumber){
        Map<String, Object> map = new HashMap<>();
        log.info(" device _number : " + deviceNumber);
        map.put("device_number",deviceNumber);
        ArrayList<MbSensorDto> List =  sensorMapper.mbEventchartInfo(map);
       return List;
    }
}
