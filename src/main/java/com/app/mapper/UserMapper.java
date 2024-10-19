package com.app.mapper;


import com.app.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29人员表】的数据库操作Mapper
* @createDate 2024-10-17 19:41:27
* @Entity warehouse.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




