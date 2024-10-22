package com.app.controller;

import com.app.entity.LoginUser;
import com.app.entity.User;
import com.app.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class UserProfileController {
    @Resource
    private UserService userService;

    @FXML
    private Label userIdLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField userSexField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField idNumberField;
    @FXML
    private TextField birthPlaceField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField userTelField;
    @FXML
    private TextField remarksField;

    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = LoginUser.getInstance().getCurrentUser();
        userIdLabel.setText(currentUser.get人员代码());
        userNameField.setText(currentUser.get姓名());
        userSexField.setText(currentUser.get性别());
        birthDateField.setText(currentUser.get出生日期().toString());
        idNumberField.setText(currentUser.get身份证号());
        birthPlaceField.setText(currentUser.get籍贯());
        addressField.setText(currentUser.get家庭地址());
        userTelField.setText(currentUser.get联系电话());
        remarksField.setText(currentUser.get备注());

        disableEditing();
    }

    @FXML
    private void edit() {
        enableEditing();
    }

    @FXML
    private void save() {
        currentUser.set姓名(userNameField.getText());
        currentUser.set性别(userSexField.getText());
        currentUser.set出生日期(LocalDate.parse(birthDateField.getText()));
        currentUser.set身份证号(idNumberField.getText());
        currentUser.set籍贯(birthPlaceField.getText());
        currentUser.set家庭地址(addressField.getText());
        currentUser.set联系电话(userTelField.getText());
        currentUser.set备注(remarksField.getText());

        userService.updateById(currentUser);

        disableEditing();
    }

    @FXML
    private void cancel() {
        initialize();
        disableEditing();
    }

    private void enableEditing() {
        userNameField.setEditable(true);
        userSexField.setEditable(true);
        birthDateField.setEditable(true);
        idNumberField.setEditable(true);
        birthPlaceField.setEditable(true);
        addressField.setEditable(true);
        userTelField.setEditable(true);
        remarksField.setEditable(true);

        editButton.setVisible(false);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    private void disableEditing() {
        userNameField.setEditable(false);
        userSexField.setEditable(false);
        birthDateField.setEditable(false);
        idNumberField.setEditable(false);
        birthPlaceField.setEditable(false);
        addressField.setEditable(false);
        userTelField.setEditable(false);
        remarksField.setEditable(false);

        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
    }
}