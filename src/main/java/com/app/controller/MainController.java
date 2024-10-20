package com.app.controller;


import com.app.provider.ApplicationContextProvider;
import com.app.provider.SceneProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Component
@Controller
public class MainController {
    @FXML
    private TreeView<String> navTree;  // 左侧多级导航栏
    @FXML
    private VBox contentArea;  // 右侧内容显示区域
    @FXML
    private Label contentLabel;  // 显示当前选择的内容

    @FXML
    public void initialize() {
        // 处理导航栏点击事件
        navTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            contentArea.getChildren().clear(); // 清空右侧内容区域

            if (newValue != null && newValue.getValue() != null) {
                String selectedItem = newValue.getValue();
                // 更新当前选择的内容 Label
                contentLabel.setText("当前显示: " + selectedItem);
                contentArea.getChildren().add(contentLabel); // 将内容标签添加到内容区域

                // 根据选中的项展示不同内容
                switch (selectedItem) {
                    case "添加用户":

                        break;
                    case "修改用户":

                        break;
                    case "人员信息":
                        loadUserInfoPage();
                        break;
                    case "物品入库":

                        break;
                    case "物品出库":

                        break;
                    case "出库请求":

                        break;
                    case "入库请求":

                        break;
                    case "出入库记录":

                        break;
                    case "物品信息":

                        break;
                    case "查询":

                        break;
                    case "进出仓流量":

                        break;
                    case "物料统计":

                        break;
                    case "进出仓打印":

                        break;
                    case "账本":

                        break;
                    default:
                        // 处理未知的选项，保持 contentArea 清空状态
                        break;
                }
            }
        });

        // 默认选择根节点
        navTree.getSelectionModel().select(navTree.getRoot());
    }

    private void loadUserInfoPage() {
        try {
            // 使用FXMLLoader加载userInfo.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userInfo.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            VBox userInfoPane = loader.load();
            // 获取userInfo.fxml的控制器
            UserInfoController userInfoController = loader.getController();
            // 将userInfo.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(userInfoPane); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//
}
