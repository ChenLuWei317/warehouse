package com.app.controller;

import com.app.entity.User;
import com.app.provider.SceneProvider;
import com.app.service.UserService;
import com.app.utils.AlertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LoginController extends BaseController{
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    Button doLogin;
    @FXML
    Button toRegister;
    @FXML
    private VBox mainVBox1;
    @Resource
    UserService userService;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
// 创建淡入效果
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), mainVBox1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

// 创建从左侧平移效果
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), mainVBox1);
        translateTransition.setFromX(-500); // 调整为更大值以确保平移效果明显
        translateTransition.setToX(0); // 移动到原始位置
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);

// 同时播放淡入和平移动画
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);

// 设置初始透明度为0
        mainVBox1.setOpacity(0);

// 在界面加载完成后启动动画
        Platform.runLater(() -> parallelTransition.play());
    }

    @SneakyThrows
    @FXML
    private void toRegister() {
        SceneProvider.switchScene(toRegister,"/fxml/register.fxml");
    }

    @FXML
    public void doLogin() {
        String userName = Username.getText();
        String password = Password.getText();

        if(userName == null || userName.equals("") || password == null || password.equals("")){
            AlertUtils.error("账号密码错误","账号密码不能为空！");
        }else{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("人员代码",userName);
            queryWrapper.eq("密码",password);
            List<User> users = userService.list(queryWrapper);
            if(users == null || users.size() == 0 || users.size() > 1){
                AlertUtils.error("账号密码错误","用户不存在，请重新输入！");
            } else if (users.size() == 1) {
                SceneProvider.switchScene(doLogin, "/fxml/main.fxml");
            }
        }
    }
}
