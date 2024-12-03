/*
package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class LoginUser implements UserDetails {
    private static final LoginUser instance = new LoginUser();
    private User user;
    private List<String> permissionList;

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123321");
        System.out.println(encode);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 确保 permissionList 不为 null
        if (permissionList == null) {
            permissionList = new ArrayList<>(); // 如果为 null，初始化为空列表
        }
        return permissionList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return user.get密码();
    }

    @Override
    public String getUsername() {
        return user.get人员代码();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private LoginUser() {}

    public static LoginUser getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User currentUser) {
        this.user = currentUser;
    }
}
*/

package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 确保 permissionList 不为 null
        if (permissionList == null) {
            permissionList = new ArrayList<>(); // 如果为 null，初始化为空列表
        }
        return permissionList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.get密码();
    }

    @Override
    public String getUsername() {
        return user.get人员代码();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public LoginUser() {}

    public void setCurrentUser(User currentUser) {
        this.user = currentUser;
    }

    public User getCurrentUser(){
        return this.user;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }
}
