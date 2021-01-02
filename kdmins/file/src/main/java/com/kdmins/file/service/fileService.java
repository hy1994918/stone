package com.kdmins.file.service;

import com.kdmins.file.mapper.fileMapper;
import com.kdmins.file.pojo.fileGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kdmins.file.mapper.*;
import java.util.List;

@Service
public class fileService {
    @Autowired
    fileMapper fileMapper;
    public List<fileGroup> getFileGroup(){
        return fileMapper.getFileGroup();
    }
}
