package com.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName 软工2202_09_05_29物料表
 */
@TableName(value ="软工2202_09_05_29物料表")
@Data
public class Goods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer 物料代码;

    /**
     * 
     */
    private String 物料名称;

    /**
     * 
     */
    private String 规格型号;

    /**
     * 
     */
    private String 计量单位;

    /**
     * 
     */
    private Integer 库存数量;

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
        Goods other = (Goods) that;
        return (this.get物料代码() == null ? other.get物料代码() == null : this.get物料代码().equals(other.get物料代码()))
            && (this.get物料名称() == null ? other.get物料名称() == null : this.get物料名称().equals(other.get物料名称()))
            && (this.get规格型号() == null ? other.get规格型号() == null : this.get规格型号().equals(other.get规格型号()))
            && (this.get计量单位() == null ? other.get计量单位() == null : this.get计量单位().equals(other.get计量单位()))
            && (this.get库存数量() == null ? other.get库存数量() == null : this.get库存数量().equals(other.get库存数量()))
            && (this.get备注() == null ? other.get备注() == null : this.get备注().equals(other.get备注()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get物料代码() == null) ? 0 : get物料代码().hashCode());
        result = prime * result + ((get物料名称() == null) ? 0 : get物料名称().hashCode());
        result = prime * result + ((get规格型号() == null) ? 0 : get规格型号().hashCode());
        result = prime * result + ((get计量单位() == null) ? 0 : get计量单位().hashCode());
        result = prime * result + ((get库存数量() == null) ? 0 : get库存数量().hashCode());
        result = prime * result + ((get备注() == null) ? 0 : get备注().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.物料名称; // 返回单位字段的值
    }
}