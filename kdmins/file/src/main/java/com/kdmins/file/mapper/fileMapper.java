package com.kdmins.file.mapper;

import com.kdmins.file.pojo.fileGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface fileMapper {
    List<fileGroup> getFileGroup();
}
