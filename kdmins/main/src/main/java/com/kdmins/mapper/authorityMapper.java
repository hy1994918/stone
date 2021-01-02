package com.kdmins.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface authorityMapper {
  void getGroupInfoByUser(@Param("dbName") String dbName, ResultHandler resultHand);
  void getGrantFun(@Param("dbName") String dbName, ResultHandler resultHand);


}
