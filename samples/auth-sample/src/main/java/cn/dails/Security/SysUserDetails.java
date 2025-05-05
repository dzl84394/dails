package cn.dails.Security;

import cn.dails.dao.entity.SysUserEntity;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SysUserDetails implements UserDetails {
    private String username;
    private SysUserEntity sysUser;
    private Collection<? extends GrantedAuthority> authorities;
    private Set<String> roles; // 保留原始角色信息

    public SysUserDetails(SysUserEntity user, Set<String> roles, String username,
                          Collection<? extends GrantedAuthority> authorities) {
        this.sysUser = user;
        this.roles = roles;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return sysUser.isEnabled();
    }

    // 获取原始角色(不带ROLE_前缀)
    public Set<String> getOriginalRoles() {
        return roles;
    }
}