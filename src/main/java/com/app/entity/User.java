package com.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@Getter
@Setter
@TableName("软工2202_09_05_29人员表")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId("人员代码")
    private String 人员代码;

    private String 密码;

    private String 姓名;

    private String 性别;

    private LocalDate 出生日期;

    private String 身份证号;

    private String 籍贯;

    private String 家庭地址;

    private String 联系电话;

    private String 备注;

    public User() {
    }

    public User(String 人员代码, String 密码, String 姓名, String 性别, LocalDate 出生日期, String 身份证号, String 籍贯, String 家庭地址, String 联系电话, String 备注) {
        this.人员代码 = 人员代码;
        this.密码 = 密码;
        this.姓名 = 姓名;
        this.性别 = 性别;
        this.出生日期 = 出生日期;
        this.身份证号 = 身份证号;
        this.籍贯 = 籍贯;
        this.家庭地址 = 家庭地址;
        this.联系电话 = 联系电话;
        this.备注 = 备注;
    }

    public User(String id, String userName, String status, String sex, String address, String userTel) {
        this.人员代码 = id;
        this.姓名 = userName;
        this.籍贯 = status;
        this.性别 = sex;
        this.家庭地址 = address;
        this.联系电话 = userTel;
    }

    public String get人员代码() {
        return 人员代码;
    }

    public void set人员代码(String 人员代码) {
        this.人员代码 = 人员代码;
    }

    public String get密码() {
        return 密码;
    }

    public void set密码(String 密码) {
        this.密码 = 密码;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String get性别() {
        return 性别;
    }

    public void set性别(String 性别) {
        this.性别 = 性别;
    }

    public LocalDate get出生日期() {
        return 出生日期;
    }

    public void set出生日期(LocalDate 出生日期) {
        this.出生日期 = 出生日期;
    }

    public String get身份证号() {
        return 身份证号;
    }

    public void set身份证号(String 身份证号) {
        this.身份证号 = 身份证号;
    }

    public String get籍贯() {
        return 籍贯;
    }

    public void set籍贯(String 籍贯) {
        this.籍贯 = 籍贯;
    }

    public String get家庭地址() {
        return 家庭地址;
    }

    public void set家庭地址(String 家庭地址) {
        this.家庭地址 = 家庭地址;
    }

    public String get联系电话() {
        return 联系电话;
    }

    public void set联系电话(String 联系电话) {
        this.联系电话 = 联系电话;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    public SimpleStringProperty idProperty() {
        SimpleStringProperty idfx=new SimpleStringProperty(人员代码);
        return idfx;
    }
    public SimpleStringProperty birthPlaceProperty() {
        SimpleStringProperty statusfx=new SimpleStringProperty(籍贯);
        return statusfx;
    }
    public SimpleStringProperty userNameProperty() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(姓名);
        return usernamefx;
    }
    public SimpleStringProperty userSexProperty() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(性别);
        return usernamefx;
    }
    public SimpleStringProperty addressProperty() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(家庭地址);
        return usernamefx;
    }
    public SimpleStringProperty userTelProperty() {
        SimpleStringProperty usernamefx=new SimpleStringProperty(联系电话);
        return usernamefx;
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
        User other = (User) that;
        return (this.get人员代码() == null ? other.get人员代码() == null : this.get人员代码().equals(other.get人员代码()))
                && (this.get密码() == null ? other.get密码() == null : this.get密码().equals(other.get密码()))
                && (this.get姓名() == null ? other.get姓名() == null : this.get姓名().equals(other.get姓名()))
                && (this.get性别() == null ? other.get性别() == null : this.get性别().equals(other.get性别()))
                && (this.get出生日期() == null ? other.get出生日期() == null : this.get出生日期().equals(other.get出生日期()))
                && (this.get身份证号() == null ? other.get身份证号() == null : this.get身份证号().equals(other.get身份证号()))
                && (this.get籍贯() == null ? other.get籍贯() == null : this.get籍贯().equals(other.get籍贯()))
                && (this.get家庭地址() == null ? other.get家庭地址() == null : this.get家庭地址().equals(other.get家庭地址()))
                && (this.get联系电话() == null ? other.get联系电话() == null : this.get联系电话().equals(other.get联系电话()))
                && (this.get备注() == null ? other.get备注() == null : this.get备注().equals(other.get备注()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get人员代码() == null) ? 0 : get人员代码().hashCode());
        result = prime * result + ((get密码() == null) ? 0 : get密码().hashCode());
        result = prime * result + ((get姓名() == null) ? 0 : get姓名().hashCode());
        result = prime * result + ((get性别() == null) ? 0 : get性别().hashCode());
        result = prime * result + ((get出生日期() == null) ? 0 : get出生日期().hashCode());
        result = prime * result + ((get身份证号() == null) ? 0 : get身份证号().hashCode());
        result = prime * result + ((get籍贯() == null) ? 0 : get籍贯().hashCode());
        result = prime * result + ((get家庭地址() == null) ? 0 : get家庭地址().hashCode());
        result = prime * result + ((get联系电话() == null) ? 0 : get联系电话().hashCode());
        result = prime * result + ((get备注() == null) ? 0 : get备注().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 人员代码=").append(人员代码);
        sb.append(", 密码=").append(密码);
        sb.append(", 姓名=").append(姓名);
        sb.append(", 性别=").append(性别);
        sb.append(", 出生日期=").append(出生日期);
        sb.append(", 身份证号=").append(身份证号);
        sb.append(", 籍贯=").append(籍贯);
        sb.append(", 家庭地址=").append(家庭地址);
        sb.append(", 联系电话=").append(联系电话);
        sb.append(", 备注=").append(备注);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
