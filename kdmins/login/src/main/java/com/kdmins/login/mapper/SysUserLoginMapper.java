
package com.kdmins.login.mapper;
import com.kdmins.login.pojo.TreeNode;
import com.kdmins.login.pojo.UserInfo;
import com.kdmins.login.pojo.department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface SysUserLoginMapper {
    UserInfo getUserInfo(@Param("userCount") String userCount);
    List<TreeNode> getTopMenu(@Param("userCount") String userCount);
    List<TreeNode> getLeftMenu(@Param("userCount") String userCount,@Param("topMenuId") Integer topMenuId);
    List<department>  getDepartment();
}
