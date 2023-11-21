package com.assembble.gc.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//분전반 주기 데이터 전송								
		

@Getter
@Setter
@ToString
public class CbSensorDto
{
    private Float id1;
    private Float id2;
    private Integer device_number;
    private Date measure_time;
    private Date update_time;
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
