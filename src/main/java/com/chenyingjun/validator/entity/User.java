package com.chenyingjun.validator.entity;

import com.chenyingjun.validator.validator.NotXss;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 测试用户信息实体
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
public class User {
    private long id;

    @NotNull(message = "{password.null}")
    private String password;

    @NotNull(message = "{user.name.null}")
    private String username;

    /** 性别 male：男  female：女 */
    @NotXss
//    @Length(min = 4, max = 6)
    private String sex;

    /** 时间 */
    @Pattern(regexp = "([1-2]{1}[0-9]{3})((1[0-2]{1})|(0[1-9]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))",
            message = "{data.input}")
    private String dateStr;

    /** 当前页数 */
    @NotNull(message = "{data.input.null}")
    @Pattern(regexp = "[1-9]{1}[0-9]{0,10}", message = "{data.input}")
    private String pageNum;

    /** 每页记录数 */
    @NotNull(message = "{data.input.null}")
    @Pattern(regexp = "[1-9]{1}[0-9]{0,10}", message = "{data.input}")
    private String pageSize;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
