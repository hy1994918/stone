package com.kdmins.quart.mapper;

import com.kdmins.common.pojo.quartzGroup;
import com.kdmins.common.pojo.quartzTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface quartzMapper {
    List<quartzTask> allTaskInfo();
    List <quartzGroup>   getQuartzGroup();
}
