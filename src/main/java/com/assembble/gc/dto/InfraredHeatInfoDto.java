package com.assembble.gc.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//적외선 열화상 정보								
				
@Getter
@Setter
@ToString
public class InfraredHeatInfoDto		

{
    private Integer device_number;
    private Date measure_time;
    private Float info;
    private Date update_time;
    //신호세기
    private Integer rssi;
    //설치장소
    private Integer site;


}