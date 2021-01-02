package com.kdmins.stay.service;

import com.kdmins.stay.mapper.stayMapper;
import com.kdmins.stay.pojo.OutInfo;
import com.kdmins.stay.pojo.stayInfoDate;
import com.kdmins.stay.pojo.stayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stoneService {
    @Autowired
    stayMapper  stayMapper;
    public List<stayInfoDate> getStayInfo(){
        return  stayMapper.getStayInfo();
    }
    public List<OutInfo> getOutInfo(){
        return  stayMapper.getOutInfo();
    }

    public int updateStayInfo(stayManager stayManager){
        return  stayMapper.updateStayInfo(stayManager);
    }
    /**
    *@Description charu
    *@Param ssss
    *@Return ss
    *@Author Mr.Renss
    *@Date ss
    *@Time sss
    **/
    public int insertStayInfo(stayManager stayManager){
        return  stayMapper.insertStayInfo(stayManager);
    }
    public int deleteStayInfo(Integer id){
        return  stayMapper.deleteStayInfo(id);
    }
    public int deleteOutInfo(Integer id){
        return  stayMapper.deleteOutInfo(id);
    }

    public int updateOutInfo(OutInfo outManager){
        return  stayMapper.updateOutInfo(outManager);
    }
    public int insertOutInfo(OutInfo outManager){
        return  stayMapper.insertOutInfo(outManager);
    }
    /**
    *@Description 
    *@Param 
    *@Return 
    *@Author Mr.Ren
    *@Date 
    *@Time 
    **/
    public List<OutInfo> getStayInfoDate(){



        return  stayMapper.getOutInfo();
    }







}
