package cn.dails.dao.entity;

import lombok.Data;

@Data
public class User {
    private final int id;
    private final String name;
    private final String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getter 方法必须存在（重要！）
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}