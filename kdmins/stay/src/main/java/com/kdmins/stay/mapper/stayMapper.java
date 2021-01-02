package com.kdmins.stay.mapper;

import com.kdmins.stay.pojo.OutInfo;
import com.kdmins.stay.pojo.stayInfoDate;
import com.kdmins.stay.pojo.stayManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface stayMapper {
  List<stayInfoDate> getStayInfo();

  List<OutInfo> getOutInfo();
  int insertStayInfo(stayManager stayManager);
  int updateStayInfo(stayManager stayManager);
  int deleteStayInfo(@Param("id") Integer id);
  int deleteOutInfo(@Param("id") Integer id);

  int insertOutInfo(OutInfo stayManager);
  int updateOutInfo(OutInfo stayManager);



}
