package com.kdmins.login.service;
import com.kdmins.login.mapper.SysUserLoginMapper;
import com.kdmins.login.pojo.department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class loginService {
    @Autowired
    SysUserLoginMapper SysUserLoginMapper;
    public List<department> getDepartmentTree(){
        department department=new  department();
        department.setId(0);
        department.setName("设置");

      return searchChildren(department,0,SysUserLoginMapper.getDepartment()).getChildren();
    }
    public  department  searchChildren(department tree,Integer pid,List<department> treeData){
        for(department menuTree:treeData){
            if(menuTree.getPId().equals(pid)){//说明有子节点就继续递归
                System.out.println(menuTree.getName());
                tree.getChildren().add(menuTree);
                searchChildren(menuTree,menuTree.getId(),treeData);
            }
        }
        return tree;
    }
}
