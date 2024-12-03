package com.app.service.impl;

import com.app.entity.Authority;
import com.app.entity.AuthorityManage;
import com.app.mapper.AuthorityManageMapper;
import com.app.mapper.AuthorityMapper;
import com.app.service.AuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Service实现
* @createDate 2024-10-17 19:41:27
*/
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService {

    @Resource
    private AuthorityManageMapper authorityManageMapper;
    @Override
    public List<Authority> getPermissionsByUserId(String userId) {
        return authorityManageMapper.getAuthorityByUserId(userId);
    }
}




