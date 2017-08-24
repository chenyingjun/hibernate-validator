package com.chenyingjun.project.entity;

/**
 * 测试用户信息实体
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
public class User {
    private long id;

    private String password;

    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
