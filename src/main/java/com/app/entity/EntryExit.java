package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName 软工2202_09_05_29进出仓表
 */
@TableName(value ="软工2202_09_05_29进出仓表")
@Data
public class EntryExit implements Serializable {
    /**
     * 
     */
    @TableId
    private String 单号;

    /**
     * 
     */
    //@TableId
    private Integer 物料代码;

    /**
     * 
     */
    private Object 类型;

    /**
     * 
     */
    private String 操作人员代码;

    /**
     * 
     */
    private Date 日期;

    /**
     * 
     */
    private Integer 数量;

    /**
     * 
     */
    private String 备注;

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
        EntryExit other = (EntryExit) that;
        return (this.get单号() == null ? other.get单号() == null : this.get单号().equals(other.get单号()))
            && (this.get物料代码() == null ? other.get物料代码() == null : this.get物料代码().equals(other.get物料代码()))
            && (this.get类型() == null ? other.get类型() == null : this.get类型().equals(other.get类型()))
            && (this.get操作人员代码() == null ? other.get操作人员代码() == null : this.get操作人员代码().equals(other.get操作人员代码()))
            && (this.get日期() == null ? other.get日期() == null : this.get日期().equals(other.get日期()))
            && (this.get数量() == null ? other.get数量() == null : this.get数量().equals(other.get数量()))
            && (this.get备注() == null ? other.get备注() == null : this.get备注().equals(other.get备注()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get单号() == null) ? 0 : get单号().hashCode());
        result = prime * result + ((get物料代码() == null) ? 0 : get物料代码().hashCode());
        result = prime * result + ((get类型() == null) ? 0 : get类型().hashCode());
        result = prime * result + ((get操作人员代码() == null) ? 0 : get操作人员代码().hashCode());
        result = prime * result + ((get日期() == null) ? 0 : get日期().hashCode());
        result = prime * result + ((get数量() == null) ? 0 : get数量().hashCode());
        result = prime * result + ((get备注() == null) ? 0 : get备注().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 单号=").append(单号);
        sb.append(", 物料代码=").append(物料代码);
        sb.append(", 类型=").append(类型);
        sb.append(", 操作人员代码=").append(操作人员代码);
        sb.append(", 日期=").append(日期);
        sb.append(", 数量=").append(数量);
        sb.append(", 备注=").append(备注);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}