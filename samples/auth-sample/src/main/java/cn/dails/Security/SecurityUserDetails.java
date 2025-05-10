package cn.dails.Security;

import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.dao.entity.SysUserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class SecurityUserDetails implements UserDetails {
    private final Long userId;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final List<SysRoleEntity> roles;
    private final List<SysPermissionEntity> permissions;
    private transient Collection<? extends GrantedAuthority> authorities;

    public SecurityUserDetails(SysUserEntity user, List<SysRoleEntity> roles, List<SysPermissionEntity> permissions) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.roles = roles != null ? roles : Collections.emptyList();
        this.permissions = permissions != null ? permissions : Collections.emptyList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            Set<GrantedAuthority> authorities = new HashSet<>();

            // 添加角色
            roles.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()))
            );

            // 添加权限
            permissions.forEach(perm -> {
                if (perm.getResource() != null && perm.getHttpMethod() != null) {
                    authorities.add(new SimpleGrantedAuthority(
                            perm.getResource() + ":" + perm.getHttpMethod()
                    ));
                }
            });

            this.authorities = Collections.unmodifiableSet(authorities);
        }
        return this.authorities;
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

}