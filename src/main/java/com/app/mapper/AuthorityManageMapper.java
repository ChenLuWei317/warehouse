package com.app.mapper;

import com.app.entity.Authority;
import com.app.entity.AuthorityManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Mapper
* @createDate 2024-10-23 22:21:12
* @Entity com.app.entity.AuthorityManage
*/
@Mapper
public interface AuthorityManageMapper extends BaseMapper<AuthorityManage> {
    @Select("SELECT * FROM 软工2202_09_05_29权限管理 WHERE 人员代码 = #{personnelCode}")
    List<Authority> findAuthoritiesByUserCode(@Param("personnelCode") String personnelCode);

}




