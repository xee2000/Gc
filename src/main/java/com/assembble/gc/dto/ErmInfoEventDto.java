package com.assembble.gc.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//접지저항 측정값 이벤트 데이터 전송								
		

@Getter
@Setter
@ToString
public class ErmInfoEventDto		

{
    private Integer id_1;
    private Integer id_2;
    private Integer device_number;
    private Date measure_time;
    private Integer rssi;
    private Integer site;
    private Float ground_registance;
    private int ground_registance_alarm;
    private Date update_time;
}