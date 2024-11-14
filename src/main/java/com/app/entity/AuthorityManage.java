package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName 软工2202_09_05_29权限管理
 */
@TableName(value ="软工2202_09_05_29权限管理")
@Data
public class AuthorityManage implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer 权限代码;

    /**
     * 
     */
    private String 人员代码;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AuthorityManage other = (AuthorityManage) that;
        return (this.get权限代码() == null ? other.get权限代码() == null : this.get权限代码().equals(other.get权限代码()))
            && (this.get人员代码() == null ? other.get人员代码() == null : this.get人员代码().equals(other.get人员代码()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get权限代码() == null) ? 0 : get权限代码().hashCode());
        result = prime * result + ((get人员代码() == null) ? 0 : get人员代码().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 权限代码=").append(权限代码);
        sb.append(", 人员代码=").append(人员代码);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    public AuthorityManage() {
        // 在这里可以进行一些默认的初始化操作
    }
    public AuthorityManage(Integer 权限代码, String 人员代码) {
        this.权限代码 = 权限代码;
        this.人员代码 = 人员代码;
    }
    public AuthorityManage(String 人员代码) {
        this.人员代码 = 人员代码;
    }

    public AuthorityManage(Integer 权限代码) {
        this.权限代码 = 权限代码;
    }
}