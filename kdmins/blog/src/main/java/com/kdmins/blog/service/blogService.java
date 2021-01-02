package com.kdmins.blog.service;

import com.kdmins.blog.pojo.Artitle;
import com.kdmins.blog.pojo.artitleGroup;
import com.kdmins.blog.pojo.artitleLabel;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.*;

@Service
public class blogService {
    @Autowired
    com.kdmins.blog.mapper.BlogMapper BlogMapper;
    @Autowired
    com.kdmins.common.util.fileUtil fileUtil;
    public List<Artitle> getGroupEssayByEssayId(String id) {
        return BlogMapper.getGroupEssayByEssayId(id);
    }
    /*获取推荐*/
    @Cacheable(value = "getIsRecommend")
    public List<Artitle> getIsRecommend() {

        return  BlogMapper.getIsRecommend();
    }
    @Cacheable(value = "getAllArtitleGroup")
    public List<artitleGroup> getAllArtitleGroup() {

        return BlogMapper.getAllArtitleGroup();
    }
    public Object imagUpload( MultipartFile file) throws IOException {

        HashMap result=new HashMap();
        result.put("errno",0);

        String path = URLDecoder.decode(ResourceUtils.getURL("classpath:static/blog/imgs").getPath(), "utf-8");
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        this.fileUtil.createFile(inputStream, path, fileName);
        ArrayList data = new ArrayList();
        data.add("/blog/imgs/"+fileName);
        result.put("data",data);
        return result;
    }

    public ArrayList<Object> getEssayGroup() {
        ArrayList<Object> essayGroup = new ArrayList<>();
        List<Map> group=BlogMapper.getArtitlrInfo();
        for(Map artitleGroup:group){
            if(Integer.parseInt(artitleGroup.get("num").toString())>=4){
                com.kdmins.blog.pojo.artitleGroup artitleGroupa=new artitleGroup();
                artitleGroupa.setArtitleGroupName(artitleGroup.get("name").toString());
                artitleGroupa.setNum(Integer.parseInt(artitleGroup.get("num").toString()));
                artitleGroupa.setId(artitleGroup.get("id").toString());
                artitleGroupa.setArtitle(BlogMapper.getArtitleInfoByGroupId(artitleGroupa.getId()));
                essayGroup.add(artitleGroupa);
            }
        }

        return  essayGroup;
    }



    public List<artitleLabel> essayLabel() {

        return BlogMapper.getEssayLabel();
    }





    public List<Map> getArtitleInfoByFlId(String id) {
        return BlogMapper.getArtitleInfoByFlId(id);
    }


    public List<Map> getArtitleInfo() {
        return BlogMapper.getArtitlrInfo();
    }


    public   List<Artitle> getArtitleInfoByGroupId( String groupId) {
        List<Artitle> artiles = this.BlogMapper.getArtitleInfoByGroupId(groupId);
        return artiles;
    }



    public int deleteArtitleById(String id) {
        int num= this.BlogMapper.deleteArtitleById(id);
        return  num;
    }
    @Cacheable(value = "artitleContextById" ,key = "#id")
    public Artitle artitleContextById( String id) {
        Artitle artile = this.BlogMapper.getArtitleInfoById(id);
        if (artile.getText() == null) {
            artile.setText("");
        }

        return  artile;
    }
    @CacheEvict(value = "artitleContextById" ,key="#Artitle.id")
    public int updateArtitleInfo( Artitle Artitle) {
        if(StringUtils.isBlank(Artitle.getId())){
            int num = this.BlogMapper.insertArtitleInfo(Artitle);
            return  num;
        }
        int num = this.BlogMapper.updateArtitleInfo(Artitle);
        return  num;
    }

    public int updateArtitleText(String id, String text) {
        return  BlogMapper.updateArtitleText(id, text);
    }


}
