package com.app.service.impl;


import com.app.entity.AuthorityManage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.service.AuthorityManageService;
import com.app.mapper.AuthorityManageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Service实现
* @createDate 2024-10-23 22:21:12
*/
@Service
public class AuthorityManageServiceImpl extends ServiceImpl<AuthorityManageMapper, AuthorityManage>
    implements AuthorityManageService{
    @Resource
    private AuthorityManageMapper authorityManageMapper;

    @Override
    public void updateAuthorityManage(String userId, List<Integer> permissionIds) {
        // 删除现有权限
        authorityManageMapper.delete(new QueryWrapper<AuthorityManage>().eq("人员代码", userId));

        // 添加新权限
        for (Integer permissionId : permissionIds) {
            AuthorityManage up = new AuthorityManage();
            up.set人员代码(userId);
            up.set权限代码(permissionId);
            authorityManageMapper.insert(up);
        }
    }

}




