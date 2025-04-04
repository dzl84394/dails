package cn.dails.dao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private final String id;
    private final String name;
    private final String icon;
    private final String path;
    private final List<Menu> children;


    public Menu(String id, String name, String icon, String path) {
        this(id, name, icon, path, null);
    }

    public Menu(String id, String name, String icon, String path, List<Menu> children) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.children = children;
    }


}
