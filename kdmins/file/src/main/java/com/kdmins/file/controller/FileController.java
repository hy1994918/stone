package com.kdmins.file.controller;
import com.kdmins.common.base.BaseResult;
import com.kdmins.file.service.fileService;
import io.swagger.annotations.Api;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping({"file"})
@CrossOrigin
@Api(
        tags = {"文件管理相关接口"}
)
public class FileController {
    @Autowired
    fileService fileService;
    @GetMapping({"fileGroup"})
    public BaseResult getIsRecommend() {
        StringBuilder StringBuilder;
        SqlSessionFactoryBean SqlSessionFactoryBean;
        return BaseResult.success("查询成功", fileService.getFileGroup());
    }
}
