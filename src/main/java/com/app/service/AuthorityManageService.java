package com.app.service;

import com.app.entity.AuthorityManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ASUS
* @description 针对表【软工2202_09_05_29权限管理】的数据库操作Service
* @createDate 2024-10-23 22:21:12
*/
public interface AuthorityManageService extends IService<AuthorityManage> {
    void updateAuthorityManage(String userId, List<Integer> permissionIds);
}
