package com.assembble.gc.service;

import com.assembble.gc.dto.MbSensorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface SensorService {
    ArrayList<MbSensorDto> getChartList(int deviceNumber);
}
