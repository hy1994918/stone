package com.kdmins.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface StorageMapper {

    //获取存储过程的参数
    public List<LinkedHashMap<String, Object>> queryStorage(@Param("storageName")String storageName);

    //获取任意表名里列名和类型(查询某一个字段的类型)
    public LinkedHashMap getTablesParamAndType(@Param("tableName")String tableName,@Param("map") Map map);

    //获取任意表名的所有列名和类型
    public List<LinkedHashMap<String, Object>>  getTablesAllParamAndType(@Param("tableName")String tableName);
}
