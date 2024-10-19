package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName 软工2202_09_05_29单号计数
 */
@TableName(value ="软工2202_09_05_29单号计数")
@Data
public class Count implements Serializable {
    /**
     * 
     */
    @TableId
    private Date 日期;

    /**
     * 
     */
    private Integer 进仓计数;

    /**
     * 
     */
    private Integer 出仓计数;

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
        Count other = (Count) that;
        return (this.get日期() == null ? other.get日期() == null : this.get日期().equals(other.get日期()))
            && (this.get进仓计数() == null ? other.get进仓计数() == null : this.get进仓计数().equals(other.get进仓计数()))
            && (this.get出仓计数() == null ? other.get出仓计数() == null : this.get出仓计数().equals(other.get出仓计数()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get日期() == null) ? 0 : get日期().hashCode());
        result = prime * result + ((get进仓计数() == null) ? 0 : get进仓计数().hashCode());
        result = prime * result + ((get出仓计数() == null) ? 0 : get出仓计数().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 日期=").append(日期);
        sb.append(", 进仓计数=").append(进仓计数);
        sb.append(", 出仓计数=").append(出仓计数);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}