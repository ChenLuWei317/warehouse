package com.app.service.impl;

import com.app.entity.Count;
import com.app.mapper.CountMapper;
import com.app.service.CountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29单号计数】的数据库操作Service实现
* @createDate 2024-10-17 19:41:27
*/
@Service
public class CountServiceImpl extends ServiceImpl<CountMapper, Count>
    implements CountService {

}




