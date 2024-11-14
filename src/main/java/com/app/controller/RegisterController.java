package com.app.controller;
import com.app.entity.User;
import com.app.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
@Component
public class RegisterController {
    @FXML
    private VBox mainVbox; // VBox 控件
    @FXML
    private TextField Name; // 姓名输入

    @FXML
    private ComboBox<String> Gender; // 性别选择

    @FXML
    private DatePicker BirthDate; // 出生日期选择

    @FXML
    private TextField Hometown; // 籍贯输入

    @FXML
    private TextField Address; // 家庭地址输入

    @FXML
    private TextField PhoneNumber; // 联系电话输入

    @FXML
    private TextField Username; // 身份证号

    @FXML
    private PasswordField Password; // 密码

    @Resource
    private UserService userService;


    @FXML
    public void RegistButtonOnClick() {
        // 获取用户输入的信息
        String name = Name.getText();
        String gender = Gender.getValue();
        LocalDate birthDate = BirthDate.getValue();
        String hometown = Hometown.getText();
        String address = Address.getText();
        String phoneNumber = PhoneNumber.getText();
        String username = Username.getText();
        String password = Password.getText();

        // 输入校验（例如是否为空）
        if (name.isEmpty() || gender == null || birthDate == null || hometown.isEmpty() ||
                address.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            System.out.println("请填写所有字段！");
            return; // 可以提示用户错误信息，并阻止窗口关闭
        }

        // 处理数据（可以存储到数据库或传递到其他组件）
        User addUser = new User();
        addUser.set姓名(name);
        addUser.set性别(gender);
        addUser.set出生日期(birthDate);
        addUser.set家庭地址(address);
        addUser.set籍贯(hometown);
        addUser.set联系电话(phoneNumber);
        addUser.set身份证号(username);
        addUser.set密码(password);
        userService.save(addUser);



    }
}
