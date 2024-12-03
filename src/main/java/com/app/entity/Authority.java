package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

/**
 * 
 * @TableName 软工2202_09_05_29权限管理
 */
@TableName(value ="软工2202_09_05_29权限表")
@Data
public class Authority implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer 权限代码;

    private String 权限名称;

    private String 备注;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Authority(Integer 权限代码, String 权限名称) {
        this.权限代码 = 权限代码;
        this.权限名称 = 权限名称;
    }

    public Authority(String 权限名称) {
        this.权限名称 = 权限名称;
    }

    public Integer get权限代码() {
        return 权限代码;
    }

    public void set权限代码(Integer 权限代码) {
        this.权限代码 = 权限代码;
    }

    public String get权限名称() {
        return 权限名称;
    }

    public void set权限名称(String 权限名称) {
        this.权限名称 = 权限名称;
    }
    // 添加一个接受 Integer, String, String 的构造函数
    public Authority(Integer id, String name, String description) {
        this.权限代码 = id;
        this.权限名称 = name;
        this.备注 = description;
    }


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
        Authority other = (Authority) that;
        return (this.get权限代码() == null ? other.get权限代码() == null : this.get权限代码().equals(other.get权限代码()))
            && (this.get权限名称() == null ? other.get权限名称() == null : this.get权限名称().equals(other.get权限名称()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get权限代码() == null) ? 0 : get权限代码().hashCode());
        result = prime * result + ((get权限名称() == null) ? 0 : get权限名称().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 权限代码=").append(权限代码);
        sb.append(", 权限名称=").append(权限名称);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public SimpleStringProperty 权限代码Property() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(权限代码.toString());
        return usernamefx;
    }
    public SimpleStringProperty 权限名称Property() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(权限名称);
        return usernamefx;
    }


}