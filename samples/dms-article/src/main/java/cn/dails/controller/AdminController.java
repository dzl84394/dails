package cn.dails.controller;

import cn.dails.dao.entity.Menu;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

// AdminController.java
@Controller
@RequestMapping("/admin")
public class AdminController {

    // 主面板
    @GetMapping("/index")
    public String adminPanel(Model model) {
        model.addAttribute("menus", getAdminMenus());
//        model.addAttribute("userInfo", getCurrentUser());
        return "admin";
    }

    // 获取菜单数据
    private List<Menu> getAdminMenus() {
        return  Arrays.asList(
                new Menu("dashboard", "仪表盘", "odometer", "/admin/dashboard", null),
                new Menu("user", "用户管理", "user", "/admin/user", Arrays.asList(
                        new Menu("userList", "用户列表", "", "/admin/user/list", null),
                        new Menu("role", "角色管理", "", "/admin/user/role", null)
                )),
                new Menu("system", "系统设置", "setting", "/admin/system", null)
        );
    }


}