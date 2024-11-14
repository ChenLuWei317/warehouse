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

import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Service实现
* @createDate 2024-10-17 19:41:27
*/
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService {
    @Autowired
    private AuthorityManageMapper authorityManageMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public IPage<AuthorityManage> page(int current, int size, String keyword) {
        Page<AuthorityManage> page = new Page<>(current, size);
        QueryWrapper<AuthorityManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("人员代码", keyword).or().like("权限代码", keyword);
        return authorityManageMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void grantAuthority(String userCode, Integer authorityId) {
        AuthorityManage authorityManage = new AuthorityManage();
        authorityManage.set人员代码(userCode);
        authorityManage.set权限代码(authorityId);
        authorityManageMapper.insert(authorityManage);
    }

    @Override
    public void revokeAuthority(String userCode, Integer authorityId) {
        QueryWrapper<AuthorityManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("人员代码", userCode).eq("权限代码", authorityId);
        authorityManageMapper.delete(queryWrapper);
    }

    @Override
    public List<Authority> listAllAuthorities() {
        return authorityMapper.selectList(null);
    }
}




