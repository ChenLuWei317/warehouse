package com.app.service.impl;

import com.app.entity.LoginUser;
import com.app.entity.User;
import com.app.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::get人员代码, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> permissionList = userMapper.getPermissionList(user.get人员代码());
        if (permissionList == null) {
            permissionList = new ArrayList<>(); // 如果 permissionList 为 null，初始化为空列表
        }
        return new LoginUser(user, permissionList);
    }

}
