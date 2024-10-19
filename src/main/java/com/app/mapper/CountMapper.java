package com.app.mapper;

import com.app.entity.Count;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29单号计数】的数据库操作Mapper
* @createDate 2024-10-17 19:41:27
* @Entity warehouse.domain.Count
*/
@Mapper
public interface CountMapper extends BaseMapper<Count> {

}




