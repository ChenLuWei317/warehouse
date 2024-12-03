package com.app.service.impl;

import com.app.mapper.UserMapper;
import com.app.entity.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29人员表】的数据库操作Service实现
* @createDate 2024-10-17 19:41:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




