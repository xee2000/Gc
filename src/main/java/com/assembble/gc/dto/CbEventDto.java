package com.assembble.gc.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//분전반 주기 데이터 전송								
		

@Getter
@Setter
@ToString
public class CbEventDto
{
    private Integer id_1;
    private Integer id_2;
    private Integer device_number;
    private Date measure_time;
    private Date update_time;
    //신호세기
    private Integer rssi;
    //설치장소
    private Integer site;
    private Float sum_power;
    private Float power;
    private Float voltage;
    private Float current;
    private Float Igo;
    private Float Igr;
    private Float Igc;
    private int overcurrent_alarm;
    private int igo_alarm;
    private int igr_alarm;

}
