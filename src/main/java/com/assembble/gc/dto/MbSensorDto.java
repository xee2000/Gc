package com.assembble.gc.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
//메인보드 센서
@Getter
@Setter
@ToString
public class MbSensorDto
{
    
    private Integer device_number;
    //측정시간
    private Date measure_time;
    private Date update_time;
    //신호세기
    private Integer rssi;
    //설치장소
    private Integer site;
    //초음파 FFT magnitude값(지진의 규모를 나타내는 척도)
    private Integer ultrasonic_status;
    private Float ultrasonic_value;
    private Integer hfct_status;
    private Integer hfct_value;
    //연기센서 상태
    private Integer smoke_status;
    //연기센서 측정값
    private Integer smoke_value;
    private Float current_r;
    private Float current_s;
    private Float current_t;
    private Float current_n;
    private Float acc_x_peak1;
    private Float acc_y_peak1;
    private Float acc_z_peak1;
    private Float acc_x_fft1;
    private Float acc_y_fft1;
    private Float acc_z_fft1;
    private Float temperature1;
    private Float acc_x_peak2;
    private Float acc_y_peak2;
    private Float acc_z_peak2;
    private Float acc_x_fft2;
    private Float acc_y_fft2;
    private Float acc_z_fft2;
    private Float temperature2;
    private Float acc_x_peak3;
    private Float acc_y_peak3;
    private Float acc_z_peak3;
    private Float acc_x_fft3;
    private Float acc_y_fft3;
    private Float acc_z_fft3;
    private Float temperature3;
    private String alarm;
}
