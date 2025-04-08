package cn.dails.controller;

import cn.dails.dao.entity.Menu;
import cn.dails.dao.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "单体应用整合示例");
        model.addAttribute("serverMessage", "Hello from Spring Boot!");
        model.addAttribute("welcomeMessage", "欢迎使用管理后台");
        return "admin"; // 对应 src/main/resources/templates/index.html
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "user/login"; // 对应 src/main/resources/templates/index.html
    }

    // 新增用户列表页面路由
    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = Arrays.asList(
                new User(1, "张三", "zhangsan@example.com"),
                new User(2, "李四", "lisi@example.com")
        );

        // 调试输出
        System.out.println("Users数据: " + users);

        model.addAttribute("users", users);
        return "user-list";
    }
//    @GetMapping("/admin")
//    @GetMapping("/admin")
//    public String adminDashboard(Model model) {
//        // 模拟菜单数据
//        model.addAttribute("menus", Arrays.asList(
//                new Menu(1, "仪表盘", "el-icon-monitor", "/admin/dashboard"),
//                new Menu(2, "用户管理", "el-icon-user", "/admin/user",
//                        Arrays.asList(
//                                new Menu(21, "用户列表", "", "/admin/user/list"),
//                                new Menu(22, "角色管理", "", "/admin/user/role")
//                        )
//                ),
//                new Menu(3, "系统设置", "el-icon-setting", "/admin/settings")
//        ));
//        return "admin/index";
//    }




    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }
}