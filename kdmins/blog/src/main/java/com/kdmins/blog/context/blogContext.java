package com.kdmins.blog.context;
import com.kdmins.blog.pojo.artitleGroup;
import com.kdmins.blog.pojo.artitleLabel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class blogContext {
    List<artitleGroup> essayGroup;

    public List<artitleGroup> getEssayGroup() {
        return essayGroup;
    }

    public List<artitleLabel> getEssayLabel() {
        return essayLabel;
    }

    List<artitleLabel> essayLabel;
    @Autowired
    com.kdmins.blog.mapper.BlogMapper BlogMapper;
    @PostConstruct
    void init(){
        this.essayLabel= BlogMapper.getEssayLabel();

    }
}
