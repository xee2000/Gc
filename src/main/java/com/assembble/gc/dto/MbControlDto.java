package com.assembble.gc.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//메인보드 설정
@Getter
@Setter
@ToString
public class MbControlDto
{
    private Integer device_number;
    //제어주기
    private Integer control_period;
    //주기보고 주기
    private Integer status_period;
    private Integer event_period;

    //초음파 임계값
    private Float ultrasonic;
    //?상 전류임계값
    private Float current_r;
    private Float current_s;
    private Float current_t;
    private Float current_n;
    private Integer smoke;
    private Integer hfct;
    private Integer reset;
    //최근 전송시각
    private Date latest_send;
}
