package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName 软工2202_09_05_29权限管理
 */
@TableName(value ="软工2202_09_05_29权限管理")
@Data
public class Authority implements Serializable {
    /**
     * 
     */
    @TableId
    private String 人员代码;

    /**
     * 
     */
    private Integer 人员档案管理;

    /**
     * 
     */
    private Integer 物料档案管理;

    /**
     * 
     */
    private Integer 进出仓管理;

    /**
     * 
     */
    private Integer 权限管理;

    /**
     * 
     */
    private Integer 统计打印;

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
        Authority other = (Authority) that;
        return (this.get人员代码() == null ? other.get人员代码() == null : this.get人员代码().equals(other.get人员代码()))
            && (this.get人员档案管理() == null ? other.get人员档案管理() == null : this.get人员档案管理().equals(other.get人员档案管理()))
            && (this.get物料档案管理() == null ? other.get物料档案管理() == null : this.get物料档案管理().equals(other.get物料档案管理()))
            && (this.get进出仓管理() == null ? other.get进出仓管理() == null : this.get进出仓管理().equals(other.get进出仓管理()))
            && (this.get权限管理() == null ? other.get权限管理() == null : this.get权限管理().equals(other.get权限管理()))
            && (this.get统计打印() == null ? other.get统计打印() == null : this.get统计打印().equals(other.get统计打印()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get人员代码() == null) ? 0 : get人员代码().hashCode());
        result = prime * result + ((get人员档案管理() == null) ? 0 : get人员档案管理().hashCode());
        result = prime * result + ((get物料档案管理() == null) ? 0 : get物料档案管理().hashCode());
        result = prime * result + ((get进出仓管理() == null) ? 0 : get进出仓管理().hashCode());
        result = prime * result + ((get权限管理() == null) ? 0 : get权限管理().hashCode());
        result = prime * result + ((get统计打印() == null) ? 0 : get统计打印().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 人员代码=").append(人员代码);
        sb.append(", 人员档案管理=").append(人员档案管理);
        sb.append(", 物料档案管理=").append(物料档案管理);
        sb.append(", 进出仓管理=").append(进出仓管理);
        sb.append(", 权限管理=").append(权限管理);
        sb.append(", 统计打印=").append(统计打印);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}