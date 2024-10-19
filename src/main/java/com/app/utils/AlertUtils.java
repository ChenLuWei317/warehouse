package com.app.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    public static void error(String title,String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType.ERROR);
        alert.setTitle("登录错误");
        alert.setHeaderText(null);
        alert.setContentText("账号密码不能为空!");
        alert.showAndWait();
    }
}
