package com.assembble.gc.mapper;

import com.assembble.gc.dto.ErmInfoEventDto;
import com.assembble.gc.dto.TripleControlDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SensorMapper {

    Map<String,Object> getTripleList();

    List<Map<String,Object>> getErmList();
}
