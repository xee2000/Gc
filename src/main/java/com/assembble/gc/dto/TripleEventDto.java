package com.assembble.gc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//3상 분전반 주기 데이터 전송								
				
@Getter
@Setter
@ToString
public class TripleEventDto
{
    private Integer id_1;
    private Integer id_2;
    private Integer scp_id;
    private Date measure_time;
    private Date update_time;
    private Integer rssi;
    private Integer site;
    private Float a_sumpower;
    private Float a_power;
    private Float a_voltage;
    private Float a_current;
    private Float a_factor;
    private Float a_frequency;
    private int a_overcurrent_alarm;
    private Float b_sumpower;
    private Float b_power;
    private Float b_voltage;
    private Float b_current;
    private Float b_factor;
    private Float b_frequency;
    private int b_overcurrent_alarm;
    private Float c_sumpower;
    private Float c_power;
    private Float c_voltage;
    private Float c_current;
    private Float c_factor;
    private Float c_frequency;
    private int c_overcurrent_alarm;
    private int current_unbalanced_alarm;

}