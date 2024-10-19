package com.app.controller;


import com.app.entity.User;
import com.app.provider.SceneProvider;
import com.app.service.UserService;
import com.app.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserInfoController {
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String>
            userIdColumn,
            userNameColumn,
            passwordColumn,
            userSexColumn,
            userBirthPlaceColumn,
            userAddressColumn,
            userTelColumn,
            actionsColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Pagination pagination;

    private ObservableList<User> data;

    @Resource
    UserService userService;

    @FXML
    private void initialize() {
        // 初始化表列
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("人员代码"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("姓名"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("密码"));
        userSexColumn.setCellValueFactory(new PropertyValueFactory<>("性别"));
        userBirthPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("籍贯"));
        userAddressColumn.setCellValueFactory(new PropertyValueFactory<>("家庭地址"));
        userTelColumn.setCellValueFactory(new PropertyValueFactory<>("联系电话"));


        actionsColumn.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                return new TableCell<User, String>() {
                    private final Button editButton = new Button("编辑");
                    private final Button deleteButton = new Button("删除");
                    private final HBox actionButtons = new HBox(editButton, deleteButton);

                    {
                        editButton.setOnAction(e -> {
                            User editUser = getTableView().getItems().get(getIndex());

                            Stage stage = new Stage();
                            GridPane root = new GridPane();
                            root.setAlignment(Pos.CENTER);
                            root.setHgap(10); // 水平间距
                            root.setVgap(10); // 垂直间距

                            // 第一行：人员代码
                            root.add(new Label("人员代码"), 0, 0);
                            TextField userIdFiled = new TextField();
                            userIdFiled.setText(editUser.get人员代码());
                            root.add(userIdFiled, 1, 0);

                            // 第二行：用户名
                            root.add(new Label("用户名"), 0, 1);
                            TextField userNameFiled = new TextField();
                            userNameFiled.setText(editUser.get姓名());
                            root.add(userNameFiled, 1, 1);

                            // 第三行：密码
                            root.add(new Label("密码"), 0, 2);
                            TextField passwordFiled = new TextField();
                            passwordFiled.setText(editUser.get密码());
                            root.add(passwordFiled, 1, 2);

                            // 第四行：性别
                            root.add(new Label("性别"), 0, 3);
                            TextField sexFiled = new TextField();
                            sexFiled.setText(editUser.get性别());
                            root.add(sexFiled, 1, 3);

                            // 第五行：籍贯
                            root.add(new Label("籍贯"), 0, 4);
                            TextField birthplaceFiled = new TextField();
                            birthplaceFiled.setText(editUser.get籍贯());
                            root.add(birthplaceFiled, 1, 4);

                            // 第六行：家庭地址
                            root.add(new Label("家庭地址"), 0, 5);
                            TextField addressFiled = new TextField();
                            addressFiled.setText(editUser.get家庭地址());
                            root.add(addressFiled, 1, 5);

                            // 第七行：联系电话
                            root.add(new Label("联系电话"), 0, 6);
                            TextField telFiled = new TextField();
                            telFiled.setText(editUser.get联系电话());
                            root.add(telFiled, 1, 6);

                            // 按钮放在第八行
                            Button update = new Button("更新");
                            Button cancel = new Button("取消");
                            HBox buttonBox = new HBox(10); // 按钮之间的间距为10
                            buttonBox.setAlignment(Pos.CENTER);
                            buttonBox.getChildren().addAll(update, cancel);
                            root.add(buttonBox, 0, 7, 2, 1); // 跨越两列

                            update.setOnAction(eu -> {
                                editUser.set人员代码(userIdFiled.getText());
                                editUser.set姓名(userNameFiled.getText());
                                editUser.set密码(passwordFiled.getText());
                                editUser.set性别(sexFiled.getText());
                                editUser.set籍贯(birthplaceFiled.getText());
                                editUser.set家庭地址(addressFiled.getText());
                                editUser.set联系电话(telFiled.getText());
                                userService.updateById(editUser);
                                data.set(getIndex(), editUser);
                                stage.close();
                            });

                            cancel.setOnAction(ec -> stage.close());

                            Scene scene = new Scene(root, 300, 200);
                            stage.setScene(scene);

                            stage.show();

                        });

                        deleteButton.setOnAction(e -> {
                            User delUser = getTableView().getItems().get(getIndex());
                            data.remove(delUser);
                            userService.removeById(delUser.get人员代码());
                        });

                        actionButtons.setSpacing(10);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(actionButtons);
                        }
                    }
                };
            }
        });

        // 设置分页
        pagination.setPageCount(Integer.parseInt(userService.list().size() / rowsPerPage + 1 + ""));
        pagination.setCurrentPageIndex(0);

        pagination.setPageFactory(this::createPage);

    }

    private final int rowsPerPage = 10; // 每页显示的行数

    private TableView<User> createPage(int currentPage) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> iPage = new Page<>(currentPage,rowsPerPage);
        IPage<User> page = this.userService.page(iPage,queryWrapper);
        data = FXCollections.observableArrayList(page.getRecords());
        tableView.setItems(data);

        return tableView;
    }

    @FXML
    private void handleClose() {
        Platform.exit();
    }

    @FXML
    private void search(){
        String userName = searchField.getText();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("姓名",userName);
        IPage<User> iPage = new Page<>(0,rowsPerPage);
        IPage<User> page = this.userService.page(iPage,queryWrapper);
        data = FXCollections.observableArrayList(page.getRecords());
        tableView.setItems(data);

    }

    @FXML
    HBox menu;

    @FXML
    public void goToBarChart() {
        SceneProvider.switchScene(menu,"/fxml/barchart.fxml");
    }

}
