package com.app.controller;


import com.app.provider.ApplicationContextProvider;
import com.app.entity.LoginUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Controller
public class MainController {
    @FXML
    private TreeView<String> navTree;  // 左侧多级导航栏
    @FXML
    private VBox contentArea;  // 右侧内容显示区域
    @FXML
    private Label contentLabel;  // 显示当前选择的内容


    //private LoginUser loginUser = LoginUser.getInstance();
    @Autowired
    private LoginUser loginUser; // 使用@Autowired注入LoginUser

    private static final Map<String, String> PAGE_PERMISSIONS = new HashMap<String, String>() {{
        put("人员信息", "用户管理");
        put("权限管理", "用户管理");
        put("我的", "用户管理");
        put("添加用户", "用户管理");
    }};


    @FXML
    public void initialize() {
        // 处理导航栏点击事件
        navTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            contentArea.getChildren().clear(); // 清空右侧内容区域

            if (newValue != null && newValue.getValue() != null) {
                String selectedItem = newValue.getValue();
                // 更新当前选择的内容 Label
                contentLabel.setText("当前显示: " + selectedItem);

                // 权限校验并加载页面
                handlePageNavigation(selectedItem);
            }
        });

        // 默认选择根节点
        navTree.getSelectionModel().select(navTree.getRoot());
    }

    private void handlePageNavigation(String selectedItem) {
        // 检查权限
        String requiredPermission = PAGE_PERMISSIONS.getOrDefault(selectedItem, "");
        if (!requiredPermission.isEmpty() && !hasPermission(requiredPermission)) {
            showAccessDenied();
            return;
        }

        // 加载页面
        switch (selectedItem) {
            case "人员信息":
                loadUserInfoPage();
                break;
            case "权限管理":
                loadUserAuthorityPage();
                break;
            case "我的":
                loadUserProfile();
                break;
            case "添加用户":
                loadAddUserPage();
                break;
            case "物品入库":
                loadStoragePage();
                break;
            case "物品出库":
                loadOutPage();
                break;
            default:
                contentArea.getChildren().clear();
                break;
        }
    }



    private boolean hasPermission(String requiredPermission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof LoginUser)) {
            return false;
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(requiredPermission));
    }


    private void showAccessDenied() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accessDenied.fxml"));
            VBox accessDeniedPane = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(accessDeniedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void loadAddUserPage() {
        try {
            // 使用FXMLLoader加载userInfo.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addUser.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            BorderPane AddUserPane = loader.load();
            // 获取userInfo.fxml的控制器
            AddUserController addUserController = loader.getController();
            // 将userInfo.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(AddUserPane); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadUserProfile() {
        try {
            // 使用FXMLLoader加载userInfo.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userProfile.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            VBox userInfoPane = loader.load();
            // 获取userInfo.fxml的控制器
            UserProfileController userProfileController = loader.getController();
            // 将userInfo.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(userInfoPane); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadUserAuthorityPage() {
        try {
            // 使用FXMLLoader加载userInfo.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userAuthorityManagement.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            BorderPane Authorities = loader.load();
            // 获取userInfo.fxml的控制器
            AuthorityManagementController authorityManagementController = loader.getController();
            // 将userInfo.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(Authorities); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadStoragePage() {
        try {
            // 使用FXMLLoader加载storage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/storage.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            AnchorPane storagePane = loader.load(); // 如果根节点是AnchorPane
            // 获取StorageController
            StorageController storageController = loader.getController();
            // 将storage.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(storagePane); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadOutPage() {
        try {
            // 使用FXMLLoader加载storage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/out.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            // 加载根节点
            AnchorPane outPane = loader.load(); // 如果根节点是AnchorPane
            // 获取StorageController
            OutController outController = loader.getController();
            // 将storage.fxml的根节点添加到contentArea中
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(outPane); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
