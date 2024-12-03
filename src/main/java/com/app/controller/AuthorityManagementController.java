package com.app.controller;

import com.app.controller.dialog.AuthorityEditDialog;
import com.app.entity.Authority;
import com.app.entity.LoginUser;
import com.app.entity.User;
import com.app.service.AuthorityManageService;
import com.app.service.AuthorityService;
import com.app.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorityManagementController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<LoginUser> userTableView; // 使用LoginUser

    @FXML
    private TableColumn<LoginUser, String> userIdColumn;

    @FXML
    private TableColumn<LoginUser, String> userNameColumn;

    @FXML
    private TableColumn<LoginUser, String> userGenderColumn;

    @FXML
    private TableColumn<LoginUser, String> userAuthoritiesColumn;

    @FXML
    private Pagination userPagination;

    @FXML
    private ListView<String> authorityListView;

    @FXML
    private Label statusLabel;

    @Resource
    private UserService userService;
    @Resource
    private AuthorityService authorityService;

    private ObservableList<LoginUser> userData;

    @Resource
    private AuthorityManageService authorityManageService;

    private final int rowsPerPage = 10;

    @FXML
    private void initialize() {
        // 初始化表列
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurrentUser().get人员代码()));
        userNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurrentUser().get姓名()));
        userGenderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurrentUser().get性别()));

//        userAuthoritiesColumn.setCellValueFactory(cellData -> {
//            List<String> authorities = cellData.getValue().getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority) // 获取权限名称
//                    .collect(Collectors.toList());
//            return new SimpleStringProperty(String.join(", ", authorities));
//        });

        // 加载初始用户数据
        loadUserData(0);
        // 用户选择监听
        userTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadUserAuthorities(newSelection.getCurrentUser().get人员代码());
            }
        });
    }

    private void loadUserData(int pageIndex) {
        long totalUsers = userService.count();
        int pageCount = (int) Math.ceil((double) totalUsers / rowsPerPage);
        userPagination.setPageCount(pageCount);
        userPagination.setCurrentPageIndex(pageIndex);

        int offset = pageIndex * rowsPerPage;
        List<User> users = userService.list(new QueryWrapper<User>().last("LIMIT " + offset + "," + rowsPerPage));
        // 将 User 转换为 LoginUser
        List<LoginUser> loginUsers = users.stream()
                .map(user -> {
                    List<String> permissions = authorityService.getPermissionsByUserId(user.get人员代码()).stream()
                            .map(Authority::get权限名称)
                            .collect(Collectors.toList());
                    return new LoginUser(user, permissions);
                })
                .collect(Collectors.toList());

        userData = FXCollections.observableArrayList(loginUsers);
        userTableView.setItems(userData);
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadUserData(0);
            statusLabel.setText("已显示所有用户，请选择一个用户查看或编辑其权限");
            return;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("姓名", keyword).or().like("人员代码", keyword);
        List<User> users = userService.list(queryWrapper);

        // 转换为 LoginUser 对象
        List<LoginUser> loginUsers = users.stream()
                .map(user -> {
                    List<String> permissions = authorityService.getPermissionsByUserId(user.get人员代码()).stream()
                            .map(Authority::get权限名称)
                            .collect(Collectors.toList());
                    return new LoginUser(user, permissions);
                })
                .collect(Collectors.toList());

        userData = FXCollections.observableArrayList(loginUsers);
        userTableView.setItems(userData);
        userPagination.setPageCount(1);
        statusLabel.setText("搜索结果显示。");
    }

    @FXML
    private void handleEditAuthorities() {
        LoginUser selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "请先选择一个用户再编辑权限。", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // 弹出编辑权限对话框
        AuthorityEditDialog dialog = new AuthorityEditDialog(selectedUser.getCurrentUser(), authorityService, authorityManageService);
        dialog.showAndWait();

        // 刷新权限列表
        loadUserAuthorities(selectedUser.getCurrentUser().get人员代码());
        statusLabel.setText("权限已更新。");
    }

    private void loadUserAuthorities(String userId) {
        List<Authority> permissions = authorityService.getPermissionsByUserId(userId);
        List<String> permissionNames = permissions.stream()
                .map(Authority::get权限名称)
                .collect(Collectors.toList());
        authorityListView.setItems(FXCollections.observableArrayList(permissionNames));
        statusLabel.setText("已加载用户权限。");
    }
}