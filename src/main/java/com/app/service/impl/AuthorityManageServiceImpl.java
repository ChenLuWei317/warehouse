package com.app.service.impl;


import com.app.entity.Authority;
import com.app.entity.AuthorityManage;
import com.app.mapper.AuthorityMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.service.AuthorityManageService;
import com.app.mapper.AuthorityManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Service实现
* @createDate 2024-10-23 22:21:12
*/
@Service
public class AuthorityManageServiceImpl extends ServiceImpl<AuthorityManageMapper, AuthorityManage>
    implements AuthorityManageService{

}




