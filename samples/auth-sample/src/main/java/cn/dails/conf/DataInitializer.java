//package cn.dails.conf;
//
//import cn.dails.dao.*;
//import cn.dails.dao.entity.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//    private final SysUserDao userMapper;
//    private final SysRoleDao roleMapper;
//    private final SysPermissionDao permissionMapper;
//    private final PasswordEncoder passwordEncoder;
//    private final SysUserRoleRelationDao userRoleMapper;
//    private final SysRolePermissionRelationDao rolePermissionMapper;
//
//    @Override
//    public void run(String... args) {
//        // 初始化角色
//        SysRoleEntity adminRole = new SysRoleEntity();
//        adminRole.setRoleCode("ADMIN");
//        adminRole.setRoleName("管理员");
//        roleMapper.insert(adminRole);
//
//        SysRoleEntity userRole = new SysRoleEntity();
//        userRole.setRoleCode("USER");
//        userRole.setRoleName("普通用户");
//        roleMapper.insert(userRole);
//
//        // 初始化权限
//        SysPermissionEntity userReadPerm = createPermission("查询用户", "USER_READ", "/api/users", "GET", "api");
//        SysPermissionEntity userWritePerm = createPermission("创建用户", "USER_WRITE", "/api/users", "POST", "api");
//
//        // 初始化管理员用户
//        SysUserEntity admin = new SysUserEntity();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("123"));
//        admin.setEnabled(true);
//        userMapper.insert(admin);
//
//        // 分配角色
//        userRoleMapper.insert(new SysUserRoleRelationEntity(admin.getId(), adminRole.getId()));
//
//        // 分配权限
//        rolePermissionMapper.insert(new SysRolePermissionRelationEntity(adminRole.getId(), userReadPerm.getId()));
//        rolePermissionMapper.insert(new SysRolePermissionRelationEntity(adminRole.getId(), userWritePerm.getId()));
//    }
//
//    private SysPermissionEntity createPermission(String name, String code, String resource, String method, String mtype) {
//        SysPermissionEntity perm = new SysPermissionEntity();
//        perm.setPermissionName(name);
//        perm.setPermissionCode(code);
//        perm.setResource(resource);
//        perm.setHttpMethod(method);
//        perm.setMtype(mtype);
//        permissionMapper.insert(perm);
//        return perm;
//    }
//}