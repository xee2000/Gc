package com.assembble.gc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//적외선 열화상 이벤트 데이터 전송								
			
@Getter
@Setter
@ToString
public class InfraredHeatEventDto		

{
    private Integer id_1;
    private Integer id_2;
    private Integer device_number;
    private Date measure_time;
    private Float info;
    private Integer alarm;
    private Date update_time;
    //신호세기
    private Integer rssi;
    //설치장소
    private Integer site;
    


}