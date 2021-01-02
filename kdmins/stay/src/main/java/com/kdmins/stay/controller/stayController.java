package com.kdmins.stay.controller;

import com.kdmins.common.base.BaseResult;
import com.kdmins.stay.pojo.OutInfo;
import com.kdmins.stay.pojo.saveData;
import com.kdmins.stay.pojo.stayManager;
import com.kdmins.stay.service.stoneService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping({"stay"})
@CrossOrigin
@Api(
        tags = {"stay相关接口"}
)
public class stayController {
    @Autowired
    com.kdmins.stay.service.stoneService stoneService;
    @GetMapping({"getStayInfo"})
    @ResponseBody
    public BaseResult getGroupEssayByEssayId() {
        return BaseResult.success("查询成功",stoneService.getStayInfo());
    }
    @PutMapping({"updateStayInfo"})
    @ResponseBody
    public BaseResult updateStayInfo(@RequestBody saveData saveData) {
        List<stayManager> stayManagers = saveData.getSaveInData();
        for(stayManager stayManager :stayManagers){
            if(StringUtils.isEmpty(stayManager.getId())){
               stoneService.insertStayInfo(stayManager);
            }else{
                stoneService.updateStayInfo(stayManager);
            }
        }
        stoneService.getStayInfoDate();
        List<OutInfo> outManagers = saveData.getSaveOutData();
        for(OutInfo outManager :outManagers){
            if(StringUtils.isEmpty(outManager.getId())){
                stoneService.insertOutInfo(outManager);
            }else{
                stoneService.updateOutInfo(outManager);
            }
        }




        return BaseResult.success("查询成功",null);
    }
    @GetMapping({"getOutInfo"})
    @ResponseBody
    public BaseResult getOutInfo() {

        return BaseResult.success("查询成功",stoneService.getOutInfo());
    }

    @DeleteMapping({"deleteStayInfo"})
    @ResponseBody
    public BaseResult deleteStayInfo(@RequestParam Integer id) {

        return BaseResult.success("查询成功",stoneService.deleteStayInfo(id));
    }


    @DeleteMapping({"deleteOutInfo"})
    @ResponseBody
    public BaseResult deleteOutInfo(@RequestParam Integer id) {

        return BaseResult.success("查询成功",stoneService.deleteOutInfo(id));
    }

    @GetMapping({"getStayInfoDate"})
    @ResponseBody
    public BaseResult getStayInfoDate() {
        return BaseResult.success("查询成功",stoneService.getStayInfoDate());
    }

}
