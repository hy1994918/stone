package com.kdmins.blog.mapper;
import com.kdmins.blog.pojo.Artitle;
import com.kdmins.blog.pojo.artitleGroup;
import java.util.List;
import java.util.Map;

import com.kdmins.blog.pojo.artitleLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Mapper
public interface BlogMapper {
    List<artitleGroup> getAllArtitleGroup();
    List<Map> getAllArtitleTree();

    List<Artitle> getIsRecommend();
    Artitle getArtitleInfoById(@Param("id") String id);
   /* SqlSessionFactoryBean ；*/
    List<Artitle> getArtitleInfoByGroupId(@Param("groupId") String groupId);
    List<Artitle> getAllArtitle();

    int updateArtitleInfo(Artitle Artitle);
    int insertArtitleInfo(Artitle Artitle);

    int updateArtitleText(@Param("text") String text, @Param("id") String id);
    int deleteArtitleById(@Param("id") String id);
    List<Map> getArtitlrInfo();

    List<Map> getArtitleInfoByFlId(@Param("flId") String flId);
    List<artitleLabel> getEssayLabel();
    List<Artitle> getGroupEssayByEssayId(@Param("id") String id);
/*
    List getArtitleTest();
*/

}

