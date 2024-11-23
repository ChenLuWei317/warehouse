package com.app.service;

import com.app.entity.Authority;
import com.app.entity.User;
import com.app.mapper.AuthorityManageMapper;
import com.app.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class WMUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityManageMapper authorityManageMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectById(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // 查询用户的权限
        List<Authority> authorities = authorityManageMapper.getAuthorityByUserId(user.get人员代码());
        user.setAuthorities(authorities);

        return user;
    }
}