package com.kdmins.blog.controller;
import com.kdmins.blog.lucene.LuceneIndexer;
import com.kdmins.blog.mapper.BlogMapper;
import com.kdmins.blog.pojo.Artitle;
import com.kdmins.blog.pojo.artitleGroup;
import com.kdmins.blog.service.blogService;
import com.kdmins.common.base.BaseResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;
@Controller
@RequestMapping({"blog"})
@CrossOrigin
@Api(
        tags = {"博客相关接口"}
)
public class blogController {
    @Autowired
    blogService blogService;
    @GetMapping({"getGroupEssayByEssayId"})
    @ResponseBody
    public BaseResult getGroupEssayByEssayId(@RequestParam("id") String id) {
        return BaseResult.success("查询成功", this.blogService.getGroupEssayByEssayId(id));
    }
    @GetMapping({"getIsRecommend"})
    @ResponseBody
    public BaseResult getIsRecommend() {
        return BaseResult.success("查询成功", this.blogService.getIsRecommend());
    }
    @Cacheable(cacheNames = {"getAllArtitleGroup"})
    @GetMapping({"getAllArtitleGroup"})
    @ResponseBody
    public BaseResult getAllArtitleGroup() {
        return BaseResult.success("查询成功", this.blogService.getAllArtitleGroup());
    }
    @PostMapping({"imagUpload"})
    @ResponseBody
    public Object imagUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return blogService.imagUpload(file);
    }
    @GetMapping({"getEssayGroup"})
    @ResponseBody
    public BaseResult getEssayGroup() {
        return BaseResult.success("查询成功", blogService.getEssayGroup());
    }
    @GetMapping({"essayLabel"})
    @ResponseBody
    public BaseResult essayLabel() {
        return BaseResult.success("查询成功", this.blogService.essayLabel());
    }
    @RequestMapping(
            value = {"/getArtitleInfoByFlId"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getArtitleInfoByFlId(@RequestParam("flId") String id) {
        return BaseResult.success("查询成功", this.blogService.getArtitleInfoByFlId(id));
    }

    @RequestMapping(
            value = {"getArtitleInfo"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getArtitleInfo() {
        return BaseResult.success("查询成功", this.blogService.getArtitleInfo());
    }

    @ResponseBody
    @RequestMapping(
            value = {"getArtitleInfoByGroupId"},
            method = {RequestMethod.GET}
    )
    public BaseResult getArtitleInfoByGroupId(@RequestParam(value = "groupId",required = false) String groupId) {
        List<Artitle> artiles = this.blogService.getArtitleInfoByGroupId(groupId);
        return BaseResult.success("查询成功", artiles);
    }

    @ResponseBody
    @RequestMapping(
            value = {"updateArtitleInfo"},
            method = {RequestMethod.PUT}
    )
    public BaseResult updateArtitleInfo(@RequestBody Artitle Artitle) {

        return BaseResult.success("添加成功", blogService.updateArtitleInfo(Artitle));
    }
    @ResponseBody
    @RequestMapping(
            value = {"/deleteArtitleById"},
            method = {RequestMethod.DELETE}
    )
    public BaseResult deleteArtitleById(@RequestParam("id") String id) {
        int num= this.blogService.deleteArtitleById(id);
        return BaseResult.success("删除成功", num);
    }
    @ResponseBody
    @RequestMapping(
            value = {"/artitleContextById"},
            method = {RequestMethod.GET}
    )
    public BaseResult artitleContextById(@RequestParam("id") String id) {
        Artitle artile = this.blogService.artitleContextById(id);
        if (artile.getText() == null) {
            artile.setText("");
        }

        return BaseResult.success("查询成功", artile);
    }

    @RequestMapping(
            value = {"/updateArtitleText"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public BaseResult updateArtitleText(@RequestParam("id") String id, @RequestParam("text") String text) {
        return BaseResult.success("查询成功", this.blogService.updateArtitleText(id, text));
    }
   @Autowired
   LuceneIndexer luceneIndexer;

    @RequestMapping(
            value = {"searchBlog"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult searchBlog(@RequestParam("text") String text) {

        return BaseResult.success("查询成功", this.luceneIndexer.search(text));
    }




}
