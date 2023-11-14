package com.assembble.gc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//단상삼상 설정정보 전송 0x40
@Getter
@Setter
@ToString
public class TripleControlDto
{
    private Integer id_1;
    private Integer id_2;
    private Integer device_num;
    private Date measure_time;
    private Float breaker_vol;
    private Date last_send;

}
