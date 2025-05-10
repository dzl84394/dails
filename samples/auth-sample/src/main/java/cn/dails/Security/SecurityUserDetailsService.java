package cn.dails.Security;

import cn.dails.dao.SysPermissionDao;
import cn.dails.dao.SysRoleDao;
import cn.dails.dao.SysUserDao;
import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.dao.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    private final SysUserDao userMapper;
    private final SysPermissionDao permissionMapper;
    private final SysRoleDao roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户
        SysUserEntity user = userMapper.selectOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 2. 查询用户角色
        List<SysRoleEntity> roleList= roleMapper.selectRolesByUserId(user.getId());
//        Set<String> roles = roleList
//                .stream()
//                .map(SysRoleEntity::getRoleCode)
//                .collect(Collectors.toSet());

        // 3. 查询用户权限
        List<SysPermissionEntity> permissions = permissionMapper.selectPermissionsByUserId(user.getId());

        // 4. 合并角色和权限到GrantedAuthority
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // 添加角色，格式为 ROLE_XXX
//        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));

        // 添加权限，格式为 路径:HTTP方法
//        permissions.forEach(permission ->
//                authorities.add(new SimpleGrantedAuthority(
//                        permission.getResource() + ":" + permission.getHttpMethod()
//                ))
//        );

        // 5. 返回自定义的UserDetails
        return new SecurityUserDetails(user, roleList, permissions);
    }
}